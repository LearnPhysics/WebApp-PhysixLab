/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.widgets.TestEntities;

/**
 *
 * @author Oliver
 */
public class EntityHolder {

    private static EntityHolder eh = null;
    
    private TestThema thema;
    private TestLektion lektion;
    private TestGame game;

    private EntityHolder() {
        thema = null;
        lektion = null;
        game = null;
    }

    public static EntityHolder getInstance() {
        if (eh == null) {
            eh = new EntityHolder();
        }
        return eh;
    }

    public TestThema getThema() {
        return thema;
    }

    public void setThema(TestThema thema) {
        this.thema = thema;
    }

    public TestLektion getLektion() {
        return lektion;
    }

    public void setLektion(TestLektion lektion) {
        this.lektion = lektion;
    }

    public TestGame getGame() {
        return game;
    }

    public void setGame(TestGame game) {
        this.game = game;
    }
}