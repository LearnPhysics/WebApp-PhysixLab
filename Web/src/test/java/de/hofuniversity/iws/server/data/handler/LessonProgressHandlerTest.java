/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.server.data.handler;

import de.hofuniversity.iws.server.data.entities.LessonProgressDBO;
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
public class LessonProgressHandlerTest {
    
    public LessonProgressHandlerTest() {
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
     * Test of store method, of class LessonProgressHandler.
     */
    @Test
    public void testStore() {
        LessonProgressDBO lp = new LessonProgressDBO();
        lp.setPoints((int)(Math.random()*100000));
        assertNull(lp.getId());
        LessonProgressDBO result = LessonProgressHandler.store(lp);
        assertNotNull(lp.getId());
        assertEquals(lp, result);
    }

    /**
     * Test of getLessonProgressEntity method, of class LessonProgressHandler.
     */
    @Test
    public void testGetLessonProgressEntity() {
        LessonProgressDBO lp = new LessonProgressDBO();
        lp.setPoints((int)(Math.random()*100000));
        LessonProgressHandler.store(lp);
        
        LessonProgressDBO result01 = LessonProgressHandler.getLessonProgressEntity(lp.getId(), true);
        LessonProgressDBO result02 = LessonProgressHandler.getLessonProgressEntity(lp.getId(), false);
        
        // Detached attribute is different
        assertNotSame(result01, result02);
        assertNotSame(lp, result01);
        assertNotSame(lp, result02);
        
        // The actually saved attributes are identical
        assertEquals(result01.getPoints(), result02.getPoints());
        assertEquals(lp.getPoints(), result01.getPoints());
        assertEquals(lp.getPoints(), result02.getPoints());
    }
}
