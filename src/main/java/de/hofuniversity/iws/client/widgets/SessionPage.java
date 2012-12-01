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

/**
 *
 * @author Daniel Heinrich <dannynullzwo@gmail.com>
 */
public class SessionPage extends Composite {

    private SessionPagetUiBinder uiBinder = GWT.create(SessionPagetUiBinder.class);

    interface SessionPagetUiBinder extends UiBinder<Widget, SessionPage> {
    }
    private final LoginServiceAsync loginService = LoginServiceAsync.Util.getInstance();
    private final String token;
    @UiField
    Label sessionLabel;

    public SessionPage(String token) {
        this.token = token;
        initWidget(uiBinder.createAndBindUi(this));
        
        sessionLabel.setVisible(true);
        sessionLabel.setText("token: " + token);
    }

    @UiHandler("logout")
    public void logout(ClickEvent ev) {        
        loginService.logout(new AsyncCallback<Void>() {
            @Override
            public void onFailure(Throwable caught) {
                RootPanel.get().clear();
                RootPanel.get().add(new LoginPage());
            }

            @Override
            public void onSuccess(Void result) {
                RootPanel.get().clear();
                RootPanel.get().add(new LoginPage());
            }
        });
    }
}
