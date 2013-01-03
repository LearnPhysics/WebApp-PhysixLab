/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.widgets.Game;

import de.hofuniversity.iws.client.widgets.TestEntities.*;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.*;
import com.google.gwt.uibinder.client.*;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.*;

/**
 *
 * @author Oliver
 */
public class Game extends Composite {

    public final static String NAME = "game";
    private static GameUiBinder uiBinder = GWT.create(GameUiBinder.class);
    private TestGame game;
    @UiField
    ScrollPanel sWrap;
    @UiField
    HeadingElement title;
    @UiField
    VerticalPanel outerGame;
    @UiField
    VerticalPanel innerGame;
    @UiField
    VerticalPanel gamePanel;
    @UiField
    ParagraphElement beschreibung;

    interface GameUiBinder extends UiBinder<Widget, Game> {
    }

    public Game() {
        initWidget(uiBinder.createAndBindUi(this));
        History.newItem(NAME);
        sWrap.getElement().getStyle().setOverflow(Style.Overflow.HIDDEN);
        sWrap.setVerticalScrollPosition(0);
        this.game = EntityHolder.getInstance().getGame();
        if(game != null) {
            setup();
        }
    }

    private void setup() {
            title.setInnerText(game.getTitle());            
            gamePanel.add(game.getGameWidget());
            beschreibung.setInnerText(game.getDescription());
            outerGame.getElement().getStyle().setWidth(
                    (extractIntFromCss(game.getGameWidget().getElement().getStyle().getWidth() + 20)), Style.Unit.PX);
    }

    private int extractIntFromCss(String css) {
        css = css.replaceAll("\\D+", "");
        return Integer.parseInt(css);
    }
}