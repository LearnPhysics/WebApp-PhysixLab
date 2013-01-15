/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.widgets.Game;

import com.chrisgammage.ginjitsu.client.AfterInject;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.*;
import com.google.gwt.uibinder.client.*;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import com.google.inject.assistedinject.Assisted;
import de.hofuniversity.iws.client.jsonbeans.GameJson;
import de.hofuniversity.iws.client.jsonbeans.SubjectJson;
import de.hofuniversity.iws.client.playn.*;
import de.hofuniversity.iws.client.util.VoidCallback;
import de.hofuniversity.iws.client.widgets.Game.Game.GameUiBinder;
import de.hofuniversity.iws.client.widgets.base.CrumbPage;
import de.hofuniversity.iws.client.widgets.history.GameElement.GameElementFactory;
import de.hofuniversity.iws.shared.services.UserServiceAsync;
import javax.inject.Inject;

/**
 *
 * @author Oliver
 */
public class Game extends CrumbPage<GameUiBinder> implements GameEventListener {

    public interface GameUiBinder extends UiBinder<Widget, Game> {
    }
    private static GameInstantiator instantiator = GWT.create(GameInstantiator.class);
    public final static String NAME = "game";
    private final UserServiceAsync userService;
    private GameJson game;
    @UiField HeadingElement title;
    @UiField VerticalPanel outerGame;
    @UiField PlayNWidget gamePanel;
    @UiField ParagraphElement beschreibung;

    public interface GameFactory {

        public Game create(SubjectJson subjectBean, GameJson bean);
    }

    @Inject
    public Game(GameElementFactory element, UserServiceAsync userService,
                @Assisted SubjectJson subjectBean, @Assisted GameJson bean) {
        super(element.create(subjectBean, bean), NAME + "?" + bean.getName());
        game = bean;
        this.userService = userService;
    }

    @Override
    public void initWidget() {
        title.setInnerText(game.getTitle());
        beschreibung.setInnerText(game.getDescription());
    }

    @Override
    protected void onLoad() {
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
