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
        games = new LinkedList<TestGame>();
        setTestGames();
        drawGames();
    }

    private void setTestGames() {
        TestGame game = new TestGame();
        game.setTitle("Kinetic Wars");
        game.setDescription("Bring die Bauten deiner Gegner zum Einsturz.");
        game.setImageURL("images/Thema/gameTest01.jpg");
        Widget w = new PlayNWidget(new KineticWars());
        w.setWidth(860+"px");
        game.setGameWidget(w);
        addGame(game);

        TestGame game0 = new TestGame();
        game0.setTitle("Go Fishing");
        game0.setDescription("Es handelt sich hier um ein reines Testspiel.");
        game0.setImageURL("images/Thema/gameTest02.jpg");
        game0.setGameWidget(new Image("images/Thema/gameWidget02.png"));
        addGame(game0);
    }

    public void addGame(TestGame game) {
        games.add(game);
    }

    public void drawGames() {
        for (int i = 0; i < games.size(); i++) {
            GameSelector gs = new GameSelector(games.get(i), i);
            gamesPanel.add(gs);
        }
    }
}
