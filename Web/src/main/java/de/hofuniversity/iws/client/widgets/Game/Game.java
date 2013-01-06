/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.widgets.Game;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.*;
import com.google.gwt.uibinder.client.*;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.*;
import de.hofuniversity.iws.client.jsonbeans.GameJson;
import de.hofuniversity.iws.client.playn.*;

/**
 *
 * @author Oliver
 */
public class Game extends Composite {

    public final static String NAME = "game";
    private static GameUiBinder uiBinder = GWT.create(GameUiBinder.class);
    private static GameInstantiator instantiator = GWT.create(GameInstantiator.class);
    private GameJson game;
    @UiField
    ScrollPanel sWrap;
    @UiField
    HeadingElement title;
    @UiField
    VerticalPanel outerGame;
    @UiField
    PlayNWidget gamePanel;
    @UiField
    ParagraphElement beschreibung;

    interface GameUiBinder extends UiBinder<Widget, Game> {
    }

    public Game(GameJson bean) {
        game = bean;
        initWidget(uiBinder.createAndBindUi(this));
        
        sWrap.getElement().getStyle().setOverflow(Style.Overflow.HIDDEN);
        sWrap.setVerticalScrollPosition(0);
        title.setInnerText(game.getTitle());
        beschreibung.setInnerText(game.getDescription());

        History.newItem(NAME + "?" + bean.getName(), false);
    }

    @Override
    protected void onAttach() {
        super.onAttach();
        outerGame.getElement().getStyle().setWidth(gamePanel.getOffsetWidth() + 20, Style.Unit.PX);
    }

    @UiFactory
    public PlayNWidget createGameWidget() {
        return new PlayNWidget(instantiator.createGame(game.getWidget()));
    }
}