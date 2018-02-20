/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.users;

import com.codemovers.scholar.engine.api.v1.abstracts.AbstractService;
import com.codemovers.scholar.engine.api.v1.accounts.entities.AuthenticationResponse;
import com.codemovers.scholar.engine.api.v1.users.entities._login;
import com.codemovers.scholar.engine.api.v1.roles.RolesService;
import com.codemovers.scholar.engine.api.v1.roles.entities.PermissionsResponse;
import com.codemovers.scholar.engine.api.v1.roles.entities.RoleResponse;
import com.codemovers.scholar.engine.api.v1.users.entities.UserResponse;
import com.codemovers.scholar.engine.api.v1.users.entities._User;
import com.codemovers.scholar.engine.db.controllers.UserRoleJpaController;
import com.codemovers.scholar.engine.db.controllers.UsersJpaController;
import com.codemovers.scholar.engine.db.entities.Permissions;
import com.codemovers.scholar.engine.db.entities.Roles;
import com.codemovers.scholar.engine.db.entities.SchoolData;
import com.codemovers.scholar.engine.db.entities.UserRole;
import com.codemovers.scholar.engine.db.entities.Users;
import static com.codemovers.scholar.engine.helper.Utilities.encryptPassword_md5;
import com.codemovers.scholar.engine.helper.exceptions.BadRequestException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Base64;
import java.util.Collection;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

/**
 *
 * @author MOver 11/19/2017
 */
public class UserService extends AbstractService<_User, UserResponse> implements UserServiceInterface {

    private static final Logger LOG = Logger.getLogger(UserService.class.getName());

    private final UsersJpaController controller;

    private static UserService service = null;

    public UserService() {
        controller = UsersJpaController.getInstance();
    }

    public static UserService getInstance() {
        if (service == null) {
            service = new UserService();
        }
        return service;
    }

    @Override
    public UserResponse create(SchoolData data, _User entity) throws Exception {
        Users USER = new Users();

        try {
            //todo: validate mandatories
            entity.validate();

            USER.setUsername(entity.getUsername());
            String encryptedPassword = encryptPassword_md5(entity.getPassword());
            USER.setPassword(encryptedPassword);
            USER.setStatus("ACTIVE");

            //get the role in the Database ::
            String[] rs = entity.getRoles();
            //   Set<Roles> roles = new HashMap<>();

            List<Roles> roleses = new ArrayList<>();

            if (rs != null) {

                for (String rolename : rs) {
                    Roles _role = RolesService.getInstance().getRoleByName(data, rolename);

                    if (_role != null) {
                        roleses.add(_role);
                    }

                }
            }

            if (roleses.isEmpty()) {
                throw new BadRequestException(" Roles do not exist");
            }

            Roles[] _roles = new Roles[roleses.size()];
            Set<Roles> roles = new HashSet<>(Arrays.asList(roleses.toArray(_roles)));
            //    USER.setUserRoles(roles);
            USER.setDateCreated(new Date());

            USER = controller.create(USER, data);

            //   UserRoleJ
            UserRole userRole = new UserRole();
            userRole.setUser(USER);
            if (roleses != null) {
                for (Roles r : roleses) {
                    userRole.setRole(r);
                    UserRoleJpaController.getInstance().create(userRole, data);
                }
            }

            // assign roles to user :: 
            return populateResponse(USER, true);
        } catch (Exception er) {

            if (USER != null && USER.getId() > 0L) {
                //   controller.destroy(USER.getId().intValue(), data);
            }
            LOG.log(Level.SEVERE, "USER-SERVICE CREATE USER FAILED");
            er.printStackTrace();

            throw er;
        }
    }

    //todo: retrieve authentication 
    /**
     *
     * @param schoolData
     * @param Id
     * @return
     * @throws Exception
     */
    @Override
    public UserResponse getById(SchoolData schoolData, Integer Id) throws Exception {

        Users _user = controller.findUser(Id, schoolData);
        if (_user == null) {
            throw new BadRequestException("USER DOES NOT EXIST");
        }
        return populateResponse(_user, true);

    }

    /**
     *
     * @param username
     * @param Password
     * @return
     */
    @Override
    public String convertToBasicAuth(String username, String Password) {
        String authString = username + ":" + Password;
        byte[] authEncBytes = Base64.getEncoder().encode(authString.getBytes());
        String authStringEnc = new String(authEncBytes);
        return ("Basic:" + authStringEnc);
    }

    //todo: validate authenticaton
    /**
     *
     * @param schoolData
     * @param authentication
     * @return
     * @throws Exception
     */
    @Override
    public AuthenticationResponse validateAuthentication(SchoolData schoolData, String authentication) throws Exception {
        authentication = authentication.replace("Basic:", "");
        String usernamePassword = new String(Base64.getDecoder().decode(authentication));
        String[] parts = usernamePassword.split(":");

        if (parts.length != 2) {
            LOG.log(Level.WARNING, "{0} :: invalid security credentials");
            throw new WebApplicationException("invalid security credentials", Response.Status.UNAUTHORIZED);
        }

        String username = parts[0];
        String password = parts[1];

        _login login = new _login();
        login.setUsername(username);
        login.setPassword(password);

        return login(schoolData, login, "LOGID");

    }

    /**
     *
     * @param tenantData
     * @param login
     * @param logId
     * @return
     * @throws Exception
     */
    @Override
    public AuthenticationResponse login(SchoolData tenantData, _login login, String logId) throws Exception {

        AuthenticationResponse response = new AuthenticationResponse();

        LOG.log(Level.INFO, "School Name {0} ", tenantData.getName());
        login.validate();
        try {
            LOG.log(Level.INFO, " School User Login ");
            String authentication = null;

            {
                if (login.getPassword() != null && login.getUsername() != null) {
                    // todo : encrypt password

                    String username = login.getUsername();
                    String password = login.getPassword();

                    password = encryptPassword_md5(password);

                    Users users = controller.login(username, password, tenantData);

                    if (users == null) {
                        throw new BadRequestException("INVALID USERNAME AND OR PASSWORD ");
                    } else {
                        // create response ::
                        authentication = convertToBasicAuth(login.getUsername(), login.getPassword());
                        response.setAuthentication(authentication);
                        Set<Roles> roleslist = users.getUserRoles();

                        List<PermissionsResponse> permissionsResponses = new ArrayList<>();

                        Collection<UserRole> arolesList = users.getUserRoleCollection();

                        if (arolesList.isEmpty()) {
                            LOG.log(Level.INFO, " RESPONSE S  EMPTY ");
                        }

                        for (Roles r : roleslist) {

                            Set<Permissions> _permissionset = r.getPermissions();
                            PermissionsResponse permissionsResponse = new PermissionsResponse();
                            permissionsResponse.setName(r.getName());
                            permissionsResponses.add(permissionsResponse);

//                            for (Permissions p : _permissionset) {
//                                permissions.add(p);
//                            }
                        }

//                        roleslist.stream().map((r) -> r.getPermissions()).forEachOrdered((_permissionset) -> {
//                            _permissionset.forEach((p) -> {
//                                PermissionsResponse permissionsResponse = new PermissionsResponse();
//                                permissionsResponse.setCode(p.getCode());
//                                permissionsResponse.setName(p.getName());
//                                permissionsResponse.setId(p.getId().intValue());
//                                permissionsResponses.add(permissionsResponse);
//                            });
//                        });
                        response.setPermissions(permissionsResponses);
                        response.setIsLoggedIn(true);

                    }
                    //todo : check username and password
                } else {
                    throw new BadRequestException(" USERNAME AND OR PASSWORD IS MANDATORY  ");
                }

            }

        } catch (BadRequestException er) {
            throw er;
        }

        return response;
    }

    @Override
    public List<UserResponse> list(SchoolData data, Integer ofset, Integer limit, AuthenticationResponse authentication) throws Exception {

        List<Users> _users = controller.findUsers(ofset, limit, data);
        List<UserResponse> userResponses = null;
        if (_users != null) {
            for (Users users : _users) {
                UserResponse userResponse = populateResponse(users, false);
                userResponses.add(userResponse);
            }
        }

        return userResponses;
    }

    /**
     *
     * @param tenantData
     * @param account_id
     */
    public void deactivate(SchoolData schoolData, Integer account_id) throws Exception {
        Users _user = controller.findUser(account_id, schoolData);
        if (_user == null) {
            throw new BadRequestException("USER DOES NOT EXIST");

        }
        _user.setStatus("DISABLED");

    }

    public void activate(SchoolData schoolData, Integer account_id) throws Exception {
        Users _user = controller.findUser(account_id, schoolData);
        if (_user == null) {
            throw new BadRequestException("USER DOES NOT EXIST");

        }
        _user.setStatus("ACTIVE");

    }

    private UserResponse populateResponse(Users entity, boolean extended) throws Exception {

        UserResponse response = new UserResponse();
        response.setId(entity.getId().intValue());
        response.setUsername(entity.getUsername());
        Set<Roles> roleSet = entity.getUserRoles();

        if (roleSet != null) {
            String[] rsArray = new String[roleSet.size()];

            List<RoleResponse> rrs = new ArrayList<>();
            List<RoleResponse> rsList = new ArrayList<>();
            roleSet.forEach((_role) -> {

                RoleResponse roleResponse = new RoleResponse();
                roleResponse.setDescription(_role.getDescription());
                roleResponse.setIsSystem(_role.getIsSystem() == 1);
                roleResponse.setName(_role.getName());

                if (extended == true) {

                    if (_role.getPermissions() != null) {
                        List<PermissionsResponse> permissionsResponses = new ArrayList<>();
                        for (Permissions p : _role.getPermissions()) {
                            PermissionsResponse permissionsResponse = new PermissionsResponse();
                            permissionsResponse.setCode(p.getCode());
                            permissionsResponse.setName(p.getName());
                            permissionsResponses.add(permissionsResponse);
                        }
                        PermissionsResponse[] prs = new PermissionsResponse[permissionsResponses.size()];
                        roleResponse.setPermissions(permissionsResponses.toArray(prs));
                    }
                }

                rsList.add(roleResponse);
            });

            RoleResponse[] roleResponses = new RoleResponse[rsList.size()];
            response.setRoles(rsList.toArray(roleResponses));

        }
        return response;
    }


}
