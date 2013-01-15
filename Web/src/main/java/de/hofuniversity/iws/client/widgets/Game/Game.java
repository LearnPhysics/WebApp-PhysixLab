/*
  * Copyright (C) 2012 Oliver Schütz
  *
  * This program is free software: you can redistribute it and/or modify
  * it under the terms of the GNU General Public License as published by
  * the Free Software Foundation, either version 3 of the License, or
  * (at your option) any later version.
  *
  * This program is distributed in the hope that it will be useful,
  * but WITHOUT ANY WARRANTY; without even the implied warranty of
  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  * GNU General Public License for more details.
  *
  * You should have received a copy of the GNU General Public License
  * along with this program.  If not, see <http://www.gnu.org/licenses/>.
  */
package de.hofuniversity.iws.client.widgets.Game;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.*;
import com.google.gwt.uibinder.client.*;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import com.google.inject.assistedinject.Assisted;
import de.hofuniversity.iws.client.jsonbeans.*;
import de.hofuniversity.iws.client.playn.*;
import de.hofuniversity.iws.client.util.VoidCallback;
import de.hofuniversity.iws.client.widgets.Game.Game.GameUiBinder;
import de.hofuniversity.iws.client.widgets.base.CrumbPage;
import de.hofuniversity.iws.client.widgets.history.GameElement.GameElementFactory;
import de.hofuniversity.iws.shared.services.UserServiceAsync;
import javax.inject.Inject;

/**
 *
 * @author Oliver Schütz
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
