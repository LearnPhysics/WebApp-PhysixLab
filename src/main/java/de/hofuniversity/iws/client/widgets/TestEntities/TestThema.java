/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.widgets.TestEntities;

import java.util.List;

/**
 *
 * @author Oliver
 */
public class TestThema {
    
    String title;
    String description;
    List<TestLektion> lektionen;
    List<TestGame> games;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<TestLektion> getLektionen() {
        return lektionen;
    }

    public void setLektionen(List<TestLektion> lektionen) {
        this.lektionen = lektionen;
    }

    public List<TestGame> getGames() {
        return games;
    }

    public void setGames(List<TestGame> games) {
        this.games = games;
    }
}
