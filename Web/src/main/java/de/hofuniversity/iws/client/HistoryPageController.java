/*
  * Copyright (C) 2012 Daniel Heinrich
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
package de.hofuniversity.iws.client;

import com.google.gwt.event.logical.shared.*;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import de.hofuniversity.iws.client.jsonbeans.*;
import de.hofuniversity.iws.client.util.*;
import de.hofuniversity.iws.client.widgets.Game.Game;
import de.hofuniversity.iws.client.widgets.Game.Game.GameFactory;
import de.hofuniversity.iws.client.widgets.Lektion.Lektion;
import de.hofuniversity.iws.client.widgets.Lektion.Lektion.Builder;
import de.hofuniversity.iws.client.widgets.LoginPage;
import de.hofuniversity.iws.client.widgets.Thema.Thema;
import de.hofuniversity.iws.client.widgets.Thema.Thema.ThemaFactory;
import de.hofuniversity.iws.client.widgets.UserHome.UserHome;
import de.hofuniversity.iws.shared.services.LessonServiceAsync;
import javax.inject.*;

/**
 *
 * @author Daniel Heinrich <dannynullzwo@gmail.com>
 */
@Singleton
public class HistoryPageController implements ValueChangeHandler<String> {

    public static final class Tokens {

        public static String loginpage() {
            return LoginPage.NAME;
        }

        public static String userhomepage() {
            return UserHome.NAME;
        }

        public static String themapage() {
            return Thema.NAME;
        }

        public static String lektionpage() {
            return Lektion.NAME;
        }

        public static String gamepage() {
            return Game.NAME;
        }
    }
    @Inject private LessonServiceAsync lessonService;
    @Inject private Provider<LoginPage> loginFactory;
    @Inject private Provider<UserHome> homeFactory;
    @Inject private Provider<Builder> lektionBuilder;
    @Inject private GameFactory gameFactory;
    @Inject private ThemaFactory themaFactory;

    @Override
    public void onValueChange(ValueChangeEvent<String> event) {
        changePage(History.getToken());
    }

    public void changePage(String token) {
        String[] split = token.split("\\?");
        String pageName = split[0];
        if (LoginPage.NAME.equals(pageName)) {
            changePage(loginFactory.get());
        } else if (UserHome.NAME.equals(pageName)) {
            changePage(homeFactory.get());
        } else if (split.length > 1) {
            if (Thema.NAME.equals(pageName)) {
                tryOpenThema(split[1]);
            } else if (Lektion.NAME.equals(pageName)) {
                tryOpenLektion(split[1]);
            } else if (Game.NAME.equals(pageName)) {
                tryOpenGame(split[1]);
            }
        } else {
            //TODO
        }
    }

    private void tryOpenThema(String options) {
        lessonService.getSubject(options, new AsyncCallback<String>() {
            @Override
            public void onFailure(Throwable caught) {
            }

            @Override
            public void onSuccess(String result) {
                changeToThema(SubjectJson.create(result));
            }
        });
    }

    public void changeToThema(SubjectJson thema) {
        changePage(themaFactory.create(thema));
    }

    private void tryOpenGame(String options) {
        lessonService.getGame(options, new AsyncCallback<String>() {
            @Override
            public void onFailure(Throwable caught) {
            }

            @Override
            public void onSuccess(String result) {
                changeToGame(GameJson.create(result));
            }
        });
    }

    public void changeToGame(GameJson bean) {
        changePage(gameFactory.create(null, bean));
    }

    private void tryOpenLektion(String options) {
        lektionBuilder.get().withLesson(options).create(new SuccessCallback<Lektion>() {
            @Override
            public void onSuccess(Lektion result) {
                changePage(result);
            }
        });
    }

    public void changePage(Composite c) {
        RootPanel.get().clear();
        RootPanel.get().add(c);
    }
}
