/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.server.data.handler;

import de.hofuniversity.iws.server.data.entities.GameDBO;
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
public class GameHandlerTest {
    
    public GameHandlerTest() {
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
     * Test of store method, of class GameHandler.
     */
    @Test
    public void testStore() {
        GameDBO g = new GameDBO();
        g.setName("testSubjectArea");
        assertNull(g.getId());
        GameDBO result = GameHandler.store(g);
        assertNotNull(g.getId());
        assertEquals(g, result);
    }

    /**
     * Test of getGameEntity method, of class GameHandler.
     */
    @Test
    public void testGetGameEntity_long_boolean() {
        GameDBO g = new GameDBO();
        g.setName("testSubjectArea");
        GameHandler.store(g);
        
        GameDBO result01 = GameHandler.getGameEntity(g.getId(), true);
        GameDBO result02 = GameHandler.getGameEntity(g.getId(), false);
        
        // Detached attribute is different
        assertNotSame(result01, result02);
        assertNotSame(g, result01);
        assertNotSame(g, result02);
        
        // The actually saved attributes are identical
        assertEquals(result01.getName(), result02.getName());
        assertEquals(g.getName(), result01.getName());
        assertEquals(g.getName(), result02.getName());
    }

    /**
     * Test of getGameEntity method, of class GameHandler.
     */
    @Test
    public void testGetGameEntity_String_boolean() {
        String name = "testGame" + Math.random();
        GameDBO g = new GameDBO();
        g.setName(name);
        GameHandler.store(g);
        
        GameDBO result01 = GameHandler.getGameEntity(name, true);
        GameDBO result02 = GameHandler.getGameEntity(name, false);
        
        // Detached attribute is different
        assertNotSame(result01, result02);
        assertNotSame(g, result01);
        assertNotSame(g, result02);
        
        // The actually saved attributes are identical
        assertEquals(result01.getName(), result02.getName());
        assertEquals(g.getName(), result01.getName());
        assertEquals(g.getName(), result02.getName());
    }
}