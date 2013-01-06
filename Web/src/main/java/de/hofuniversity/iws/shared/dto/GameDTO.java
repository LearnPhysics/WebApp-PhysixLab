/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.shared.dto;

import java.io.Serializable;

import de.hofuniversity.iws.shared.entityimpl.GameDBO;

/**
 *
 * @author Daniel Heinrich <dannynullzwo@gmail.com>
 */
public class GameDTO implements Serializable {

    private String titel, description, imageUrl, widget;
    private GameDBO dbo;    

    public String getTitle() {
        return titel;
    }

    public String getWidget() {
        return widget;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public GameDBO getDbo() {
        return dbo;
    }

    public void setDbo(GameDBO dbo) {
        this.dbo = dbo;
    }
}
