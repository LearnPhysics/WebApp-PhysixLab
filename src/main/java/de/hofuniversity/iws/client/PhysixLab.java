/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client;

import java.sql.Date;

import com.google.common.base.Optional;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.RootPanel;
import de.hofuniversity.iws.client.widgets.LoginPage;
import de.hofuniversity.iws.client.widgets.SessionPage;
import de.hofuniversity.iws.shared.dto.SessionDTO;
import de.hofuniversity.iws.shared.services.LoginServiceAsync;
import javax.inject.Inject;

/**
 *
 * @author Daniel Heinrich <dannynullzwo@gmail.com>
 */
public class PhysixLab {

    public static final String SESSION_ID_COOKIE = "session_id";
    public static final String SESSION_EXPIRE_COOKIE = "session_expire_date";
    @Inject
    private LoginServiceAsync loginService;

    public void init() {
        String sessionId = Cookies.getCookie(SESSION_ID_COOKIE);

        if (sessionId == null) {
            gotoPage(new LoginPage(loginService));
        } else {
            String expire = Cookies.getCookie(SESSION_EXPIRE_COOKIE);
            if (expire != null) {
                try {
                    Date ex = new Date(Long.parseLong(expire));
                    if (new Date(System.currentTimeMillis()).before(ex)) {
                        loginService.getSessionFromId(sessionId, new SessionCallback());
                    }
                } catch (NumberFormatException ex) {
                }
            }
        }
    }

    private void gotoPage(Composite page) {
        RootPanel.get().clear();
        RootPanel.get().add(page);
    }

    private class SessionCallback implements AsyncCallback<Optional<SessionDTO>> {

        @Override
        public void onSuccess(Optional<SessionDTO> result) {
            if (result.isPresent()) {
                SessionDTO session = result.get();
                
                Cookies.setCookie(SESSION_ID_COOKIE, session.getSessionID());
                Cookies.setCookie(SESSION_EXPIRE_COOKIE, Long.toString(session.getExpireDate().getTime()));
                
                gotoPage(new SessionPage(loginService, session));
            } else {
                gotoPage(new LoginPage(loginService));
            }
        }

        @Override
        public void onFailure(Throwable caught) {
            gotoPage(new LoginPage(loginService));
        }
    }
}
