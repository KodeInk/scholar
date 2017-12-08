/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.db.controllers;

import com.codemovers.scholar.engine.db.entities.Addresses;
import com.codemovers.scholar.engine.db.entities.SchoolData;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mover
 */
public class AddressJpaControllerTest {

    public AddressJpaControllerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getInstance method, of class AddressJpaController.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        AddressJpaController expResult = null;
        AddressJpaController result = AddressJpaController.getInstance();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of create method, of class AddressJpaController.
     */
    @Test
    public void testCreate() {
        System.out.println("create");
        Addresses entity = null;
        SchoolData data = null;
        AddressJpaController instance = new AddressJpaController();
        Addresses expResult = null;
        Addresses result = instance.create(entity, data);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of edit method, of class AddressJpaController.
     */
    @Test
    public void testEdit() throws Exception {
        System.out.println("edit");
        Addresses addresses = null;
        SchoolData data = null;
        AddressJpaController instance = new AddressJpaController();
        instance.edit(addresses, data);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findContact method, of class AddressJpaController.
     */
    @Test
    public void testFindContact() {
        System.out.println("findContact");
        Integer id = null;
        SchoolData data = null;
        AddressJpaController instance = new AddressJpaController();
        Addresses expResult = null;
        Addresses result = instance.findContact(id, data);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findAddresses method, of class AddressJpaController.
     */
    @Test
    public void testFindAddresses_String_SchoolData() {
        System.out.println("findAddresses");
        String parentType = "";
        SchoolData data = null;
        AddressJpaController instance = new AddressJpaController();
        List<Addresses> expResult = null;
        List<Addresses> result = instance.findAddresses(parentType, data);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findAddresses method, of class AddressJpaController.
     */
    @Test
    public void testFindAddresses_3args() {
        System.out.println("findAddresses");
        String parentType = "";
        Integer parentId = null;
        SchoolData data = null;
        AddressJpaController instance = new AddressJpaController();
        List<Addresses> expResult = null;
        List<Addresses> result = instance.findAddresses(parentType, parentId, data);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findAddressEntities method, of class AddressJpaController.
     */
    @Test
    public void testFindAddressEntities_SchoolData() {
        System.out.println("findAddressEntities");
        SchoolData data = null;
        AddressJpaController instance = new AddressJpaController();
        List<Addresses> expResult = null;
        List<Addresses> result = instance.findAddressEntities(data);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findAddressEntities method, of class AddressJpaController.
     */
    @Test
    public void testFindAddressEntities_3args() {
        System.out.println("findAddressEntities");
        int maxResults = 0;
        int firstResult = 0;
        SchoolData data = null;
        AddressJpaController instance = new AddressJpaController();
        List<Addresses> expResult = null;
        List<Addresses> result = instance.findAddressEntities(maxResults, firstResult, data);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCount method, of class AddressJpaController.
     */
    @Test
    public void testGetCount() {
        System.out.println("getCount");
        SchoolData data = null;
        AddressJpaController instance = new AddressJpaController();
        int expResult = 0;
        int result = instance.getCount(data);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
