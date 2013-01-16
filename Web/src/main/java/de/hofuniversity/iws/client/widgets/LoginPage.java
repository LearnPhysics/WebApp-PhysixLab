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
package de.hofuniversity.iws.client.widgets;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.*;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import de.hofuniversity.iws.client.*;
import de.hofuniversity.iws.client.widgets.LoginPage.LoginPageUiBinder;
import de.hofuniversity.iws.client.widgets.UserHome.UserHome;
import de.hofuniversity.iws.client.widgets.base.HistoryPage;
import de.hofuniversity.iws.shared.dto.LoginDTO;
import de.hofuniversity.iws.shared.services.LoginServiceAsync;
import javax.inject.Inject;

/**
 *
 * @author Andreas Arndt <andreas.arndt@hof-university.de>
 * @author Oliver Schütz
 */
public class LoginPage extends HistoryPage<LoginPageUiBinder> {

    public interface LoginPageUiBinder extends UiBinder<Widget, LoginPage> {
    }
    public static final String NAME = "login";
    private final LoginServiceAsync loginService;
    private final PhysixLab lab;
    private final HistoryPageController pageController;
    @UiField//so gwt-test-util is happy
    Image facebookLogin;
    @UiField//so gwt-test-util is happy
    Image googleLogin;
    @UiField//so gwt-test-util is happy
    Image twitterLogin;
    @Inject @UiField(provided = true)
    Header header;

    @Inject
    public LoginPage(LoginServiceAsync loginService, PhysixLab lab,
                     HistoryPageController pageController) {
        super(NAME);
        this.loginService = loginService;
        this.lab = lab;
        this.pageController = pageController;
    }

    @UiHandler("googleLogin")
    public void loginWithGoogle(ClickEvent ev) {
        loginService.getOAuthLoginUrl("GOOGLE", new PopupCallback());
    }

    @UiHandler("twitterLogin")
    public void loginWithTwitter(ClickEvent ev) {
        loginService.getOAuthLoginUrl("TWITTER", new PopupCallback());
    }

    @UiHandler("facebookLogin")
    public void loginWithFacebook(ClickEvent ev) {
        loginService.getOAuthLoginUrl("FACEBOOK", new PopupCallback());
    }

    private class PopupCallback implements AsyncCallback<String> {

        @Override
        public void onFailure(Throwable caught) {
            throw new UnsupportedOperationException(caught.getLocalizedMessage());
        }

        @Override
        public void onSuccess(String result) {
            com.google.gwt.user.client.Window.open(result, "Please Login",
                                                   "menubar=no,"
                                                   + "location=false,"
                                                   + "resizable=yes,"
                                                   + "scrollbars=yes,"
                                                   + "status=no,"
                                                   + "dependent=true, height=500, width=600");
            loginService.waitForOAuthVerification(new VerificationCallback());
        }
    }

    private class VerificationCallback implements AsyncCallback<LoginDTO> {

        @Override
        public void onFailure(Throwable caught) {
            throw new UnsupportedOperationException(caught.getLocalizedMessage());
        }

        @Override
        public void onSuccess(LoginDTO result) {
            lab.setLoginData(result);
            pageController.changePage(UserHome.NAME);
        }
    }
}
