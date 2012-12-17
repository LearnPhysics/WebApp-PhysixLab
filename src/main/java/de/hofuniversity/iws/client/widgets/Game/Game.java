/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.widgets.Game;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.HeadingElement;
import com.google.gwt.dom.client.ParagraphElement;
import com.google.gwt.dom.client.Style;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import de.hofuniversity.iws.client.widgets.Thema.Spielwahl.TestGame;

/**
 *
 * @author Oliver
 */
public class Game extends Composite {
    
    public final static String NAME = "game";
    private static GameUiBinder uiBinder = GWT.create(GameUiBinder.class);
    
    private TestGame game;
    
    @UiField ScrollPanel sWrap;
    @UiField HeadingElement title;
    @UiField DivElement outerGame;
    @UiField DivElement innerGame;
    @UiField VerticalPanel gamePanel;
    @UiField ParagraphElement beschreibung;  
    
    interface GameUiBinder extends UiBinder<Widget, Game> {
    }
    
    public Game() {
        initWidget(uiBinder.createAndBindUi(this));
        History.newItem(NAME);
        sWrap.getElement().getStyle().setOverflow(Style.Overflow.HIDDEN);
    }
    
    public Game(TestGame game) {
        initWidget(uiBinder.createAndBindUi(this));
        History.newItem(NAME);
        sWrap.getElement().getStyle().setOverflow(Style.Overflow.HIDDEN);
        this.game = game;
        setup();
    }
    
    private void setup() {
        title.setInnerText(game.getName());
        beschreibung.setInnerText(game.getDescription());
        
        gamePanel.add(game.getGameWidget());
        outerGame.setAttribute("width", game.getGameWidget().getWidth() + "px");
    }
}