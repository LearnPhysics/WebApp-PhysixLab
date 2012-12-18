/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.widgets.TestEntities;

import com.google.gwt.user.client.ui.Widget;

/**
 *
 * @author Oliver
 */
public class TestGame {

    String title;
    String description;
    String imageURL;
    Widget gameWidget;

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

    public Widget getGameWidget() {
        return gameWidget;
    }

    public void setGameWidget(Widget gameWidget) {
        this.gameWidget = gameWidget;
    }
}
