/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.*;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import de.hofuniversity.iws.client.jsonbeans.SubjectJson;
import de.hofuniversity.iws.client.widgets.Game.Game;
import de.hofuniversity.iws.client.widgets.Lektion.Lektion;
import de.hofuniversity.iws.client.widgets.LoginPage;
import de.hofuniversity.iws.client.widgets.Thema.Thema;
import de.hofuniversity.iws.client.widgets.UserHome.UserHome;
import de.hofuniversity.iws.shared.services.*;

/**
 *
 * @author Daniel Heinrich <dannynullzwo@gmail.com>
 */
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
    
    private final LessonServiceAsync lessonService = (LessonServiceAsync) GWT.create(LessonService.class);

    @Override
    public void onValueChange(ValueChangeEvent<String> event) {
        changePage(History.getToken());
    }

    public void changePage(String token) {
        String[] split = token.split("\\?");
        String pageName = split[0];
        if (LoginPage.NAME.equals(pageName)) {
            changePage(new LoginPage());
        } else if (UserHome.NAME.equals(pageName)) {
            changePage(new UserHome());            
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
                changePage(new Thema(sj));
            }
        });
    }

    private void tryOpenGame(String options) {
        //TODO
    }

    private void tryOpenLektion(String options) {
        Lektion l = Lektion.build().withLesson(options).create();
        changePage(l);
    }

    public void changePage(Composite c) {
        RootPanel.get().clear();
        RootPanel.get().add(c);
    }
}