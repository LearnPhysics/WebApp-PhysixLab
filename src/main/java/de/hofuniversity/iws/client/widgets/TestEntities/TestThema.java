/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.widgets.TestEntities;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Oliver
 */
public class TestThema {
    
    String title;
    String description;
    String imageURL;
    List<TestLektion> lektionen;
    List<TestGame> games;
    
    public TestThema() {
        lektionen = new LinkedList<TestLektion>();
        games = new LinkedList<TestGame>();
    }

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

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
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
