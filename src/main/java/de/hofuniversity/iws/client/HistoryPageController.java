/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client;

import de.hofuniversity.iws.client.widgets.*;

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

        public static String sessionpage() {
            return SessionPage.NAME;
        }

        public static String userinfopage() {
            return UserInfoWidget.NAME;
        }
    }

    @Override
    public void onValueChange(ValueChangeEvent<String> event) {
        changePage(History.getToken());
    }

    public void changePage(String pageName) {

        if (LoginPage.NAME.equals(pageName)) {
            changePage(new LoginPage());
        } else if (SessionPage.NAME.equals(pageName)) {
            changePage(new SessionPage());
        } else if (UserInfoWidget.NAME.equals(pageName)) {
            changePage(new UserInfoWidget());
        } else {
            //TODO
        }
    }

    public void changePage(Composite c) {
        RootPanel.get().clear();
        RootPanel.get().add(c);
    }
}
