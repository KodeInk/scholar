/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.users;

import com.codemovers.scholar.engine.api.v1.accounts.entities.AuthenticationResponse;
import com.codemovers.scholar.engine.api.v1.roles.entities.RoleResponse;
import com.codemovers.scholar.engine.api.v1.users.entities.Login;
import com.codemovers.scholar.engine.api.v1.users.entities.UserResponse;
import com.codemovers.scholar.engine.api.v1.users.entities._User;
import com.codemovers.scholar.engine.db.entities.Profile;
import com.codemovers.scholar.engine.db.entities.Roles;
import com.codemovers.scholar.engine.db.entities.SchoolData;
import com.codemovers.scholar.engine.db.entities.Users;
import java.util.List;
import java.util.Set;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Mover 7:49
 */
public class UserServiceTest {

    public UserServiceTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of getInstance method, of class UserService.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        UserService expResult = null;
        UserService result = UserService.getInstance();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of create method, of class UserService.
     */
    @Test
    public void testCreate() throws Exception {
        System.out.println("create");
        SchoolData data = null;
        _User entity = null;
        AuthenticationResponse authentication = null;
        UserService instance = new UserService();
        UserResponse expResult = null;
        UserResponse result = instance.create(data, entity, authentication);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getById method, of class UserService.
     */
    @Test
    public void testGetById() throws Exception {
        System.out.println("getById");
        SchoolData schoolData = null;
        Integer Id = null;
        UserService instance = new UserService();
        UserResponse expResult = null;
        UserResponse result = instance.getById(schoolData, Id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of convertToBasicAuth method, of class UserService.
     */
    @Test
    public void testConvertToBasicAuth() {
        System.out.println("convertToBasicAuth");
        String username = "";
        String Password = "";
        UserService instance = new UserService();
        String expResult = "";
        String result = instance.convertToBasicAuth(username, Password);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of validateAuthentication method, of class UserService.
     */
    @Test
    public void testValidateAuthentication() throws Exception {
        System.out.println("validateAuthentication");
        SchoolData schoolData = null;
        String authentication = "";
        UserService instance = new UserService();
        AuthenticationResponse expResult = null;
        AuthenticationResponse result = instance.validateAuthentication(schoolData, authentication);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of login method, of class UserService.
     */
    @Test
    public void testLogin() throws Exception {
        System.out.println("login");
        SchoolData tenantData = null;
        Login login = null;
        String logId = "";
        UserService instance = new UserService();
        AuthenticationResponse expResult = null;
        AuthenticationResponse result = instance.login(tenantData, login, logId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of list method, of class UserService.
     */
    @Test
    public void testList() throws Exception {
        System.out.println("list");
        SchoolData data = null;
        Integer ofset = null;
        Integer limit = null;
        AuthenticationResponse authentication = null;
        UserService instance = new UserService();
        List<UserResponse> expResult = null;
        List<UserResponse> result = instance.list(data, ofset, limit, authentication);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deactivate method, of class UserService.
     */
    @Test
    public void testDeactivate() throws Exception {
        System.out.println("deactivate");
        SchoolData schoolData = null;
        Integer account_id = null;
        UserService instance = new UserService();
        instance.deactivate(schoolData, account_id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of activate method, of class UserService.
     */
    @Test
    public void testActivate() throws Exception {
        System.out.println("activate");
        SchoolData schoolData = null;
        Integer account_id = null;
        UserService instance = new UserService();
        instance.activate(schoolData, account_id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of AttachRoles method, of class UserService.
     */
    @Test
    public void testAttachRoles() throws Exception {
        System.out.println("AttachRoles");
        _User entity = null;
        SchoolData data = null;
        Users USER = null;
        UserService instance = new UserService();
        instance.AttachRoles(entity, data, USER);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of AttachUserProfile method, of class UserService.
     */
    @Test
    public void testAttachUserProfile() {
        System.out.println("AttachUserProfile");
        Users USER = null;
        Profile profile = null;
        SchoolData data = null;
        UserService instance = new UserService();
        instance.AttachUserProfile(USER, profile, data);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUser method, of class UserService.
     */
    @Test
    public void testGetUser() throws Exception {
        System.out.println("getUser");
        _User entity = null;
        UserService instance = new UserService();
        Users expResult = null;
        Users result = instance.getUser(entity);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRoles method, of class UserService.
     */
    @Test
    public void testGetRoles() throws Exception {
        System.out.println("getRoles");
        String[] rs = null;
        SchoolData data = null;
        UserService instance = new UserService();
        List<Roles> expResult = null;
        List<Roles> result = instance.getRoles(rs, data);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLogin method, of class UserService.
     */
    @Test
    public void testGetLogin() {
        System.out.println("getLogin");
        String[] parts = null;
        UserService instance = new UserService();
        Login expResult = null;
        Login result = instance.getLogin(parts);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRolesResponse method, of class UserService.
     */
    @Test
    public void testGetRolesResponse() {
        System.out.println("getRolesResponse");
        Set<Roles> roleSet = null;
        boolean extended = false;
        UserService instance = new UserService();
        RoleResponse[] expResult = null;
        RoleResponse[] result = instance.getRolesResponse(roleSet, extended);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
