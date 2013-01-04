/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.shared.dto;

import java.io.Serializable;

import de.hofuniversity.iws.shared.entityimpl.GameDBO;
import de.hofuniversity.iws.shared.games.GameFactory;

/**
 *
 * @author Daniel Heinrich <dannynullzwo@gmail.com>
 */
public class GameDTO implements Serializable {

    private String titel, description, imageUrl;
    private GameDBO dbo;
    private GameFactory widgetFactory;

    public GameFactory getWidgetFactory() {
        return widgetFactory;
    }

    public void setWidgetFactory(GameFactory widgetFactory) {
        this.widgetFactory = widgetFactory;
    }

    public String getTitle() {
        return titel;
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
