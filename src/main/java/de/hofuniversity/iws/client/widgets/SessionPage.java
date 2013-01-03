/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.widgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.*;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import de.hofuniversity.iws.client.PhysixLab;
import de.hofuniversity.iws.shared.services.LoginService;
import de.hofuniversity.iws.shared.services.LoginServiceAsync;

/**
 *
 * @author Daniel Heinrich <dannynullzwo@gmail.com>
 */
public class SessionPage extends Composite {

    public final static String NAME = "session";
    private SessionPagetUiBinder uiBinder = GWT.create(SessionPagetUiBinder.class);

    interface SessionPagetUiBinder extends UiBinder<Widget, SessionPage> {
    }
    private final LoginServiceAsync loginService = (LoginServiceAsync)GWT.create(LoginService.class);
    @UiField
    Label sessionLabel;

    public SessionPage() {
        initWidget(uiBinder.createAndBindUi(this));
        sessionLabel.setVisible(true);
        sessionLabel.setText("token: " + PhysixLab.getSessionToken());
        History.newItem(NAME);
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
