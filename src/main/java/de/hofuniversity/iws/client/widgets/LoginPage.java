/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.widgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.*;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import de.hofuniversity.iws.shared.services.LoginServiceAsync;
import javax.inject.Inject;

/**
 *
 * @author Daniel Heinrich <dannynullzwo@gmail.com>
 */
public class LoginPage extends Composite {

    private final LoginServiceAsync loginService;
    private LoginPageUiBinder uiBinder = GWT.create(LoginPageUiBinder.class);

    interface LoginPageUiBinder extends UiBinder<Widget, LoginPage> {
    }

    @Inject
    public LoginPage(LoginServiceAsync loginService) {
        this.loginService = loginService;
        initWidget(uiBinder.createAndBindUi(this));
    }

    @UiHandler("googleLogin")
    public void loginWithGoogle(ClickEvent ev) {
//        loginService.SocialNetworkLogin("G", new PopupCallback("http://www.google.com"));
    }

    @UiHandler("twitterLogin")
    public void loginWithTwitter(ClickEvent ev) {
//        loginService.SocialNetworkLogin("T", new PopupCallback("http://www.twitter.com"));
    }

    @UiHandler("facebookLogin")
    public void loginWithFacebook(ClickEvent ev) {
//        loginService.SocialNetworkLogin("F", new PopupCallback("http://www.facebook.com"));
    }

    private static class PopupCallback implements AsyncCallback<String> {

        private final String url;

        public PopupCallback(String url) {
            this.url = url;
        }

        public void onFailure(Throwable caught) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public void onSuccess(String result) {
            openNewWindow("Testing Window", result);
        }
    }

    public static void openNewWindow(String name, String url) {
        com.google.gwt.user.client.Window.open(url, name.replace(" ", "_"),
                                               "menubar=no,"
                                               + "location=false,"
                                               + "resizable=yes,"
                                               + "scrollbars=yes,"
                                               + "status=no,"
                                               + "dependent=true, height=300, width=600");
    }
}