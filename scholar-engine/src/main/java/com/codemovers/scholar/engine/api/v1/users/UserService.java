/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.users;

import com.codemovers.scholar.engine.api.v1.abstracts.AbstractService;
import com.codemovers.scholar.engine.api.v1.roles.RolesService;
import com.codemovers.scholar.engine.api.v1.users.entities.UserResponse;
import com.codemovers.scholar.engine.api.v1.users.entities._User;
import com.codemovers.scholar.engine.db.controllers.UsersJpaController;
import com.codemovers.scholar.engine.db.entities.Roles;
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
import org.sonatype.plexus.components.cipher.Base64;

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

    //todo: retrieve authentication 
    @Override
    public Users login(String username, String password, String logid) throws Exception {

        return null;

    }

    @Override
    public UserResponse getById(Integer Id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String convertToBasicAuth(String username, String Password) {
        String authString = username + ":" + Password;
        byte[] authEncBytes = Base64.encodeBase64(authString.getBytes());
        String authStringEnc = new String(authEncBytes);
        return ("Basic:" + authStringEnc);
        //  String possibleAuthenticationKey = "Basic " + Base64.getEncoder().encodeToString(usernamePassowrd.trim().getBytes());

    }

    //todo: validate authenticaton
    @Override
    public boolean validateAuthentication(String Schoolid, String authentication) throws Exception {


        return true;
    }

}
