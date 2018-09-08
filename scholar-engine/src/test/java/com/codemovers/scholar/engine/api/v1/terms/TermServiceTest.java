/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.terms;

import com.codemovers.scholar.engine.api.v1.accounts.entities.AuthenticationResponse;
import com.codemovers.scholar.engine.api.v1.terms.entities.TermResponse;
import com.codemovers.scholar.engine.api.v1.terms.entities._Term;
import com.codemovers.scholar.engine.db.entities.SchoolData;
import com.codemovers.scholar.engine.db.entities.StudyYear;
import com.codemovers.scholar.engine.db.entities.Terms;
import com.codemovers.scholar.engine.helper.exceptions.BadRequestException;
import java.util.Date;
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
public class TermServiceTest {

    public TermServiceTest() {
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
     * Test of validateTermEngine method, of class TermService.
     */
    @Test
    public void testValidateTermEngineWithNullParams() {
        System.out.println("testValidateTermEngineWithNullParams");
        try {
            Date term_start_date = null;
            Date term_end_date = null;
            Date year_start_date = null;
            Date year_end_date = null;
            TermService instance = new TermService();
            instance.validateTermEngine(term_start_date, term_end_date, year_start_date, year_end_date);
            // TODO review the generated test code and remove the default call to fail.
            fail("Not Expected to Reach Hear ");
        } catch (BadRequestException er) {
            assertEquals(er.getResponse().getStatus(), 400);
        }
    }

    @Test
    public void testValidateWithSimilarDate(){
        System.out.println("testValidateWithSimilarDate");
        try {
            Date test_date = new Date();
            Date term_start_date = test_date;
            Date term_end_date = test_date;
            Date year_start_date = test_date;
            Date year_end_date = test_date;
            TermService instance = new TermService();
            instance.validateTermEngine(term_start_date, term_end_date, year_start_date, year_end_date);
            // TODO review the generated test code and remove the default call to fail.
            fail("Not Expected to Reach Hear ");
        } catch (BadRequestException er) {
            assertEquals(er.getResponse().getStatus(), 400);
        }
    }

}
