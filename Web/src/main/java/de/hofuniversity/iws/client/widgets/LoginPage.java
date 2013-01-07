/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.widgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.*;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;
import de.hofuniversity.iws.client.PhysixLab;
import de.hofuniversity.iws.client.util.*;
import de.hofuniversity.iws.client.widgets.UserHome.UserHome;
import de.hofuniversity.iws.shared.dto.LoginDTO;
import de.hofuniversity.iws.shared.services.*;

/**
 *
 * @author Daniel Heinrich <dannynullzwo@gmail.com>
 */
public class LoginPage extends HistoryPage {
    
    private LoginPageUiBinder uiBinder = GWT.create(LoginPageUiBinder.class);
    
    interface LoginPageUiBinder extends UiBinder<Widget, LoginPage> {
    }
    public static final String NAME = "login";
    private final LoginServiceAsync loginService = (LoginServiceAsync) GWT.create(LoginService.class);
    
    public LoginPage() {
        initWidget(uiBinder.createAndBindUi(this));
        AddressStack.getInstance().addAddress(new CrumbTuple(this, " Startseite ", 0));
    }
    
    @Override
    public String getName() {
        return NAME;
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
            PhysixLab.setLoginData(result);
            PhysixLab.PAGE_CONTROLLER.changePage(UserHome.NAME);
        }
    }
}
