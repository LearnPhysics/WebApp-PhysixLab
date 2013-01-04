/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client;

import de.hofuniversity.iws.client.widgets.Game.Game;
import de.hofuniversity.iws.client.widgets.Lektion.Lektion;
import de.hofuniversity.iws.client.widgets.LoginPage;
import de.hofuniversity.iws.client.widgets.Thema.Thema;
import de.hofuniversity.iws.client.widgets.UserHome.UserHome;

import com.google.gwt.event.logical.shared.*;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.*;

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

    @Override
    public void onValueChange(ValueChangeEvent<String> event) {
        changePage(History.getToken());
    }

    public void changePage(String token) {
        String[] split = token.split("\\?");
        String pageName = split[0];
        if (Tokens.loginpage().equals(pageName)) {
            changePage(new LoginPage());
        } else if (Tokens.userhomepage().equals(pageName)) {
            changePage(new UserHome());
        } else if (split.length > 1) {
            if (Tokens.themapage().equals(pageName)) {
                tryOpenThema(split[1]);
            } else if (Tokens.lektionpage().equals(pageName)) {
                tryOpenLektion(split[1]);
            } else if (Tokens.gamepage().equals(pageName)) {
                tryOpenGame(split[1]);
            }
        } else {
            //TODO
        }
    }

    private void tryOpenThema(String options) {
        //TODO options = topicName, get from server
    }

    private void tryOpenGame(String options) {
        //TODO
    }

    private void tryOpenLektion(String options) {
        //TODO
    }

    public void changePage(Composite c) {
        RootPanel.get().clear();
        RootPanel.get().add(c);
    }
}
