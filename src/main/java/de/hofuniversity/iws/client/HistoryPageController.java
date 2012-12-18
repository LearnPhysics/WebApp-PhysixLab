/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client;

import de.hofuniversity.iws.client.widgets.*;

import com.google.gwt.event.logical.shared.*;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.*;
import de.hofuniversity.iws.client.widgets.Game.Game;
import de.hofuniversity.iws.client.widgets.Thema.Thema;
import de.hofuniversity.iws.client.widgets.UserHome.UserHome;

/**
 *
 * @author Daniel Heinrich <dannynullzwo@gmail.com>
 */
public class HistoryPageController implements ValueChangeHandler<String> {

    public static final class Tokens {

        public static String loginpage() {
            return LoginPage.NAME;
        }

        public static String sessionpage() {
            return SessionPage.NAME;
        }

        public static String userinfopage() {
            return UserInfoWidget.NAME;
        }

        public static String userhomepage() {
            return UserHome.NAME;
        }

        public static String themapage() {
            return Thema.NAME;
        }
        
        public static String gamepage() {
            return Game.NAME;
        }
    }

    @Override
    public void onValueChange(ValueChangeEvent<String> event) {
        changePage(History.getToken());
    }

    public void changePage(String pageName) {

        if (Tokens.loginpage().equals(pageName)) {
            changePage(new LoginPage());
        } else if (Tokens.sessionpage().equals(pageName)) {
            changePage(new SessionPage());
        } else if (Tokens.userinfopage().equals(pageName)) {
            changePage(new UserInfoWidget());
        } else if (Tokens.userhomepage().equals(pageName)) {
            changePage(new UserHome());
        } else if (Tokens.themapage().equals(pageName)) {
            changePage(new Thema());
        } else if (Tokens.gamepage().equals(pageName)) {
            changePage(new Game());
        } else {
            //TODO
        }
    }

    public void changePage(Composite c) {
        RootPanel.get().clear();
        RootPanel.get().add(c);
    }
}
