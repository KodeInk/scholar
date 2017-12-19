/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.users;

import com.codemovers.scholar.engine.api.v1.abstracts.AbstractService;
import com.codemovers.scholar.engine.api.v1.accounts.entities.AuthenticationResponse;
import com.codemovers.scholar.engine.api.v1.accounts.entities.PermissionsResponse;
import com.codemovers.scholar.engine.api.v1.accounts.entities._login;
import com.codemovers.scholar.engine.api.v1.roles.RolesService;
import com.codemovers.scholar.engine.api.v1.users.entities.UserResponse;
import com.codemovers.scholar.engine.api.v1.users.entities._User;
import com.codemovers.scholar.engine.db.controllers.UsersJpaController;
import com.codemovers.scholar.engine.db.entities.Permissions;
import com.codemovers.scholar.engine.db.entities.Roles;
import static com.codemovers.scholar.engine.db.entities.Roles_.permissions;
import com.codemovers.scholar.engine.db.entities.SchoolData;
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
import javax.ws.rs.InternalServerErrorException;
import java.util.Base64;
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
        try {
            //todo: validate mandatories
            entity.validate();

            Users USER = new Users();

            USER.setUsername(entity.getUsername());
            String encryptedPassword = encryptPassword_md5(entity.getPassword());
            USER.setPassword(encryptedPassword);

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
            USER.setUserRoles(roles);
            USER.setDateCreated(new Date());

            USER = controller.create(USER, data);
            return populateResponse(USER);
        } catch (Exception er) {
            LOG.log(Level.SEVERE, "USER-SERVICE CREATE USER FAILED");
            throw new InternalServerErrorException("User could not be created successfully ");
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
        return populateResponse(_user);

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
    public boolean validateAuthentication(SchoolData schoolData, String authentication) throws Exception {
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

        login(schoolData, login, "LOGID");

        //login(schoolData, username, password, "LOGID");
        // at this time, there is already approved school data :
        return true;
    }

    private UserResponse populateResponse(Users entity) throws Exception {

        UserResponse response = new UserResponse();
        response.setId(entity.getId().intValue());
        response.setUsername(entity.getUsername());
        Set<Roles> roleSet = entity.getUserRoles();

        if (!roleSet.isEmpty()) {
            String[] rsArray = new String[roleSet.size()];
            List<String> rsList = new ArrayList<>();
            roleSet.forEach((_role) -> {
                rsList.add(_role.getName());
            });
            response.setRoles(rsList.toArray(rsArray));

        }
        return response;
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

        LOG.log(Level.INFO, "School Name {0} ", tenantData.getName());
        login.validate();
        try {
            LOG.log(Level.INFO, " General Account Service Login ");
            String authentication = null;

            AuthenticationResponse response = new AuthenticationResponse();

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
                        authentication = UserService.getInstance().convertToBasicAuth(login.getUsername(), login.getPassword());
                        response.setAuthentication(authentication);
                        Set<Roles> roleslist = users.getUserRoles();
                        List<PermissionsResponse> permissionsResponses = new ArrayList<>();

                        for (Roles r : roleslist) {

                            Set<Permissions> _permissionset = r.getPermissions();

//                            for (Permissions p : _permissionset) {
//                                permissions.add(p);
//                            }

                        }

                        roleslist.stream().map((r) -> r.getPermissions()).forEachOrdered((_permissionset) -> {
                            _permissionset.forEach((p) -> {
                                PermissionsResponse permissionsResponse = new PermissionsResponse();
                                permissionsResponse.setCode(p.getCode());
                                permissionsResponse.setName(p.getName());
                                permissionsResponse.setId(p.getId().intValue());
                                permissionsResponses.add(permissionsResponse);
                            });
                        });

                        response.setPermissions(permissionsResponses);
                        response.setIsLoggedIn(true);

                    }
                    //todo : check username and password
                } else {
                    throw new BadRequestException(" USERNAME AND OR PASSWORD IS MANDATORY  ");
                }

            }

        } catch (Exception er) {
            throw new BadRequestException(" USERNAME AND OR PASSWORD IS MANDATORY  ");
        }

        return null;
    }

}
