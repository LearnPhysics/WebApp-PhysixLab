/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.widgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.*;
import com.google.gwt.user.client.ui.*;
import de.hofuniversity.iws.client.PhysixLab;
import de.hofuniversity.iws.client.playn.PlayNWidget;
import de.hofuniversity.iws.client.playn.games.TestGame;
import de.hofuniversity.iws.shared.services.*;

/**
 *
 * @author Daniel Heinrich <dannynullzwo@gmail.com>
 */
public class SessionPage extends HistoryPage {

    private SessionPagetUiBinder uiBinder = GWT.create(SessionPagetUiBinder.class);

    interface SessionPagetUiBinder extends UiBinder<Widget, SessionPage> {
    }
    public static final String NAME = "session";
    private final LoginServiceAsync loginService = (LoginServiceAsync) GWT.create(LoginService.class);
    @UiField
    Label sessionLabel;

    public SessionPage() {
        initWidget(uiBinder.createAndBindUi(this));
        sessionLabel.setVisible(true);
        sessionLabel.setText("token: " + PhysixLab.getSessionToken());
    }

    @Override
    public String getName() {
        return NAME;
    }

    @UiHandler("logout")
    public void logout(ClickEvent ev) {
        loginService.logout(null);
        PhysixLab.PAGE_CONTROLLER.changePage(LoginPage.NAME);
    }
}
