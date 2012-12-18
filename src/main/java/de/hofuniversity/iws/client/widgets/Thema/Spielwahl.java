/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.widgets.Thema;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import de.hofuniversity.iws.client.widgets.SubWidgets.GameSelector;
import java.util.LinkedList;
import java.util.List;

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
        game.setName("Kinetic Wars");
        game.setDescription("Bring die Bauten deiner Gegner zum Einsturz.");
        game.setImageURL("images/Thema/gameTest01.jpg");
        addGame(game);
        
        TestGame game0 = new TestGame();
        game0.setName("Go Fishing");
        game0.setDescription("Es handelt sich hier um ein reines Testspiel.");
        game0.setImageURL("images/Thema/gameTest02.jpg");

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

    public class TestGame {

        String name;
        String description;
        String imageURL;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
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
    }
}
