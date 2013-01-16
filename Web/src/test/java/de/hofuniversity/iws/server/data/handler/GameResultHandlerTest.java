/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.server.data.handler;

import de.hofuniversity.iws.server.data.entities.GameResultDBO;
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
public class GameResultHandlerTest {
    
    public GameResultHandlerTest() {
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
     * Test of store method, of class GameResultHandler.
     */
    @Test
    public void testStore() {
        GameResultDBO gr = new GameResultDBO();
        gr.setPoints((int)(Math.random()*100000));
        assertNull(gr.getId());
        GameResultDBO result = GameResultHandler.store(gr);
        assertNotNull(gr.getId());
        assertEquals(gr, result);
    }

    /**
     * Test of getGameResultEntity method, of class GameResultHandler.
     */
    @Test
    public void testGetGameResultEntity() {
        GameResultDBO gr = new GameResultDBO();
        gr.setPoints((int)(Math.random()*100000));
        GameResultHandler.store(gr);
        
        GameResultDBO result01 = GameResultHandler.getGameResultEntity(gr.getId(), true);
        GameResultDBO result02 = GameResultHandler.getGameResultEntity(gr.getId(), false);
        
        // Detached attribute is different
        assertNotSame(result01, result02);
        assertNotSame(gr, result01);
        assertNotSame(gr, result02);
        
        // The actually saved attributes are identical
        assertEquals(result01.getPoints(), result02.getPoints());
        assertEquals(gr.getPoints(), result01.getPoints());
        assertEquals(gr.getPoints(), result02.getPoints());
    }
}
