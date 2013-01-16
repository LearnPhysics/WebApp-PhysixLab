/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.server.data.handler;

import de.hofuniversity.iws.server.data.entities.SubjectAreaDBO;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Oliver
 */
public class SubjectAreaHandlerTest {
    
    public SubjectAreaHandlerTest() {
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
     * Test of store method, of class SubjectAreaHandler.
     */
    @Test
    public void testStore() {
        SubjectAreaDBO sa = new SubjectAreaDBO();
        sa.setName("testSubjectArea");
        assertNull(sa.getId());
        SubjectAreaDBO result = SubjectAreaHandler.store(sa);
        assertNotNull(sa.getId());
        assertEquals(sa, result);
    }

    /**
     * Test of getSubjectAreaEntity method, of class SubjectAreaHandler.
     */
    @Test
    public void testGetSubjectAreaEntity() {
        SubjectAreaDBO sa = new SubjectAreaDBO();
        sa.setName("testSubjectArea");
        SubjectAreaHandler.store(sa);
        
        SubjectAreaDBO result01 = SubjectAreaHandler.getSubjectAreaEntity(sa.getId(), true);
        SubjectAreaDBO result02 = SubjectAreaHandler.getSubjectAreaEntity(sa.getId(), false);
        
        // Detached attribute is different
        assertNotSame(result01, result02);
        assertNotSame(sa, result01);
        assertNotSame(sa, result02);
        
        // The actually saved attributes are identical
        assertEquals(result01.getName(), result02.getName());
        assertEquals(sa.getName(), result01.getName());
        assertEquals(sa.getName(), result02.getName());
    }
}