/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.widgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import de.hofuniversity.iws.shared.services.LoginServiceAsync;

/**
 *
 * @author Daniel Heinrich <dannynullzwo@gmail.com>
 */
public class LoginPage extends Composite {

    private final LoginServiceAsync loginService = GWT.create(LoginServiceAsync.class);
    private LoginPageUiBinder uiBinder = GWT.create(LoginPageUiBinder.class);

    interface LoginPageUiBinder extends UiBinder<Widget, LoginPage> {
    }

    public LoginPage() {
        initWidget(uiBinder.createAndBindUi(this));
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
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void onSuccess(String result) {
            com.google.gwt.user.client.Window.open(result, "Please Login",
                                                   "menubar=no,"
                                                   + "location=false,"
                                                   + "resizable=yes,"
                                                   + "scrollbars=yes,"
                                                   + "status=no,"
                                                   + "dependent=true, height=300, width=600");
            loginService.waitForOAuthVerification(new VerificationCallback());
        }
    }

    private class VerificationCallback implements AsyncCallback<String> {

        @Override
        public void onFailure(Throwable caught) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void onSuccess(String result) {
            RootPanel.get().clear();
            RootPanel.get().add(new SessionPage(result));
        }
    }
}
