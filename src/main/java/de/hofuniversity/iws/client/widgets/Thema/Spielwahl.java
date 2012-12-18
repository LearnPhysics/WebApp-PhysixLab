/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.widgets.Thema;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import de.hofuniversity.iws.client.widgets.SubWidgets.GameSelector;
import de.hofuniversity.iws.client.widgets.TestEntities.EntityHolder;
import de.hofuniversity.iws.client.widgets.TestEntities.TestGame;
import java.util.LinkedList;
import java.util.List;

import de.hofuniversity.iws.client.playn.PlayNWidget;
import de.hofuniversity.iws.client.playn.games.KineticWars;

/**
 *
 * @author Oliver
 */
public class Spielwahl extends Composite {

    private static SpielwahlUiBinder uiBinder = GWT.create(SpielwahlUiBinder.class);
    private static List<TestGame> games;
    @UiField
    VerticalPanel gamesPanel;

    interface SpielwahlUiBinder extends UiBinder<Widget, Spielwahl> {
    }

    public Spielwahl() {
        initWidget(uiBinder.createAndBindUi(this));
        this.games = EntityHolder.getInstance().getThema().getGames();
        if(games != null) {
            setup();
        }
    }

    public void setup() {
        for (int i = 0; i < games.size(); i++) {
            GameSelector gs = new GameSelector(games.get(i), i);
            gamesPanel.add(gs);
        }
    }
}
