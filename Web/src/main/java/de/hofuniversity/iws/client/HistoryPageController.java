/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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
import de.hofuniversity.iws.client.widgets.UserHome.UserHome.UserHomeFactory;
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
    private final LessonServiceAsync lessonService;
    private final LoginPage loginFactory;
    private final UserHomeFactory homeFactory;
    private final Builder lektionBuilder;
    private final GameFactory gameFactory;
    private final AddressStack crumbStack;
    private final ThemaFactory themaFactory;

    @Inject
    public HistoryPageController(LessonServiceAsync lessonService, LoginPage loginFactory,
                                 UserHomeFactory homeFactory, Builder lektionBuilder,
                                 GameFactory gameFactory, AddressStack crumbStack, ThemaFactory themaFactory) {
        this.lessonService = lessonService;
        this.loginFactory = loginFactory;
        this.homeFactory = homeFactory;
        this.lektionBuilder = lektionBuilder;
        this.gameFactory = gameFactory;
        this.crumbStack = crumbStack;
        this.themaFactory = themaFactory;
    }

    @Override
    public void onValueChange(ValueChangeEvent<String> event) {
        changePage(History.getToken());
    }

    public void changePage(String token) {
        String[] split = token.split("\\?");
        String pageName = split[0];
        if (LoginPage.NAME.equals(pageName)) {
            changePage(loginFactory, "Startseite", 0);
        } else if (UserHome.NAME.equals(pageName)) {
            changePage(homeFactory.create(), "Home", 1);
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
                SubjectJson sj = SubjectJson.create(result);

            }
        });
    }

    public void changeToThema(SubjectJson thema) {
        changePage(themaFactory.create(thema), thema.getTitle(), 2);
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
        changePage(gameFactory.create(bean), bean.getTitle(), 3);
    }

    private void tryOpenLektion(String options) {
        Lektion l = lektionBuilder.withLesson(options).withSubject((String) null).create();
        changeToLektion(l);
    }

    public void changeToLektion(Lektion lektion) {
        changePage(lektion, lektion.getTitle(), 3);
    }

    private void changePage(Composite c, String titel, int layer) {
        crumbStack.addAddress(new CrumbTuple(c, titel, layer));
        changePage(c);
    }

    public void changePage(Composite c) {
        RootPanel.get().clear();
        RootPanel.get().add(c);
    }
}
