/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.widgets.Game;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.*;
import com.google.gwt.uibinder.client.*;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import com.google.inject.assistedinject.*;
import de.hofuniversity.iws.client.jsonbeans.GameJson;
import de.hofuniversity.iws.client.playn.*;
import de.hofuniversity.iws.client.util.VoidCallback;
import de.hofuniversity.iws.client.widgets.CrumbPage;
import de.hofuniversity.iws.client.widgets.SubWidgets.BackButton.BackButtonFactory;
import de.hofuniversity.iws.client.widgets.SubWidgets.Breadcrumb.BreadcrumbFactory;
import de.hofuniversity.iws.shared.services.UserServiceAsync;

/**
 *
 * @author Oliver
 */
public class Game extends CrumbPage implements GameEventListener {

    interface GameUiBinder extends UiBinder<Widget, Game> {
    }
    private static GameUiBinder uiBinder = GWT.create(GameUiBinder.class);
    private static GameInstantiator instantiator = GWT.create(GameInstantiator.class);
    public final static String NAME = "game";
    private final UserServiceAsync userService;
    private GameJson game;
    @UiField ScrollPanel sWrap;
    @UiField HeadingElement title;
    @UiField VerticalPanel outerGame;
    @UiField PlayNWidget gamePanel;
    @UiField ParagraphElement beschreibung;

    public interface GameFactory {

        public Game create(GameJson bean);
    }

    @AssistedInject
    public Game(BackButtonFactory backFactory, BreadcrumbFactory breadFactory, UserServiceAsync userService, @Assisted GameJson bean) {
        super(backFactory, breadFactory, 3, NAME + "?" + bean.getName());
        game = bean;
        this.userService = userService;
        initWidget(uiBinder.createAndBindUi(this));
        title.setInnerText(game.getTitle());
        beschreibung.setInnerText(game.getDescription());
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

    @Override
    public void gameEnded(GameEvent ev) {
        userService.addGameResult(game.getName(), ev.points, new VoidCallback());
        Window.alert("You scored " + ev.points + " points!");
        gamePanel.restartGame();
    }
}
