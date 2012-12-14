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

    private LoginPage loginPage = null;
    private SessionPage sessionPage = null;
    private static UserInfoWidget userInfoWidget = null;
    
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

        if (Tokens.loginpage().equals(pageName)) {
            loginPage = new LoginPage();
            changePage(loginPage);
        } else if (Tokens.sessionpage().equals(pageName)) {
            sessionPage = new SessionPage();
            changePage(sessionPage);
        } else if (Tokens.userinfopage().equals(pageName)) {
            userInfoWidget = new UserInfoWidget();
            changePage(userInfoWidget);
        } else {
            //TODO
        }
    }

    public void changePage(Composite c) {
        RootPanel.get().clear();
        RootPanel.get().add(c);
    }
    public LoginPage getLoginPage()
    {
        return loginPage;
    }
    public SessionPage getSessionPage()
    {
        return sessionPage;
    }
    public UserInfoWidget getUserInfoWidget()
    {
        return userInfoWidget;
    }
}
