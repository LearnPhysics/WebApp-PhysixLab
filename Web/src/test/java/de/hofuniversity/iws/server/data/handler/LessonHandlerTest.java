/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.server.data.handler;

import de.hofuniversity.iws.server.data.entities.LessonDBO;
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
public class LessonHandlerTest {
    
    public LessonHandlerTest() {
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
     * Test of store method, of class LessonHandler.
     */
    @Test
    public void testStore() {
        LessonDBO l = new LessonDBO();
        l.setName("testSubjectArea");
        assertNull(l.getId());
        LessonDBO result = LessonHandler.store(l);
        assertNotNull(l.getId());
        assertEquals(l, result);
    }

    /**
     * Test of getLessonEntity method, of class LessonHandler.
     */
    @Test
    public void testGetLessonEntity() {
        LessonDBO l = new LessonDBO();
        l.setName("testSubjectArea");
        LessonHandler.store(l);
        
        LessonDBO result01 = LessonHandler.getLessonEntity(l.getId(), true);
        LessonDBO result02 = LessonHandler.getLessonEntity(l.getId(), false);
        
        // Detached attribute is different
        assertNotSame(result01, result02);
        assertNotSame(l, result01);
        assertNotSame(l, result02);
        
        // The actually saved attributes are identical
        assertEquals(result01.getName(), result02.getName());
        assertEquals(l.getName(), result01.getName());
        assertEquals(l.getName(), result02.getName());
    }

    /**
     * Test of getLessonEntityOrCreateTemplate method, of class LessonHandler.
     */
    @Test
    public void testGetLessonEntityOrCreateTemplate() {
        String name = "testLesson" + Math.random();
        LessonDBO l = new LessonDBO();
        l.setName(name);
        LessonHandler.store(l);
        
        LessonDBO result01 = LessonHandler.getLessonEntityOrCreateTemplate(name, true);
        LessonDBO result02 = LessonHandler.getLessonEntityOrCreateTemplate(name, false);
        
        // Detached attribute is different
        assertNotSame(result01, result02);
        assertNotSame(l, result01);
        assertNotSame(l, result02);
        
        // The actually saved attributes are identical
        assertEquals(result01.getName(), result02.getName());
        assertEquals(l.getName(), result01.getName());
        assertEquals(l.getName(), result02.getName());
        
        String nameT = "templateLesson" + Math.random();
        
        LessonDBO result03 = LessonHandler.getLessonEntityOrCreateTemplate(nameT, true);
        LessonDBO result04 = LessonHandler.getLessonEntityOrCreateTemplate(nameT, false);
        
        // Results are unsaved templates
        assertEquals(result03.getId(), null);
        assertEquals(result04.getId(), null);
        
        // Name is filled
        assertEquals(nameT, result03.getName());
        assertEquals(nameT, result04.getName());
    }
}
