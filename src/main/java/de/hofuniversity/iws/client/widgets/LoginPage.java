/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.widgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import de.hofuniversity.iws.client.PhysixLab;
import de.hofuniversity.iws.shared.services.LoginService;
import de.hofuniversity.iws.shared.services.LoginServiceAsync;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.*;

/**
 *
 * @author Daniel Heinrich <dannynullzwo@gmail.com>
 */
public class LoginPage extends Composite {

    public final static String NAME = "login";
    private final LoginServiceAsync loginService = (LoginServiceAsync) GWT.create(LoginService.class);
    private LoginPageUiBinder uiBinder = GWT.create(LoginPageUiBinder.class);

    interface LoginPageUiBinder extends UiBinder<Widget, LoginPage> {
    }
    
    @UiField
    HorizontalPanel providerPanel;

    public LoginPage() {
        initWidget(uiBinder.createAndBindUi(this));
        History.newItem(NAME);
        
        String[] provider = new String[]{"GOOGLE","FACEBOOK","TWITTER"};
        for(String name: provider)
        {
            Image img = new Image("images/"+name.toLowerCase()+".jpg");
            img.addClickHandler(new LoginButtonHandler(name));
            providerPanel.add(img);
        }
    }
    
    private class LoginButtonHandler implements ClickHandler{
        private final String providerName;

        public LoginButtonHandler(String providerName) {
            this.providerName = providerName;
        }
        
        @Override
        public void onClick(ClickEvent event) {
            loginService.getOAuthLoginUrl(providerName, new PopupCallback());
        }
        
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
                                                   + "dependent=true, height=300, width=600");
            loginService.waitForOAuthVerification(new VerificationCallback());
        }
    }

    private class VerificationCallback implements AsyncCallback<String> {

        @Override
        public void onFailure(Throwable caught) {
            throw new UnsupportedOperationException(caught.getLocalizedMessage());
        }

        @Override
        public void onSuccess(String result) {
            PhysixLab.PAGE_CONTROLLER.changePage(SessionPage.NAME);
        }
    }
}
