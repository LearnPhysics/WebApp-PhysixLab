/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client;

import de.hofuniversity.iws.client.widgets.*;
import de.hofuniversity.iws.shared.services.LoginServiceAsync;

import com.google.common.base.Optional;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.rpc.AsyncCallback;
import javax.inject.Inject;

/**
 *
 * @author Daniel Heinrich <dannynullzwo@gmail.com>
 */
public class PhysixLab {

    public static final HistoryPageController PAGE_CONTROLLER = new HistoryPageController();
    private static String sessionToken;
    @Inject
    private LoginServiceAsync loginService;

    public void init() {
        History.addValueChangeHandler(PAGE_CONTROLLER);


        loginService.getSessionToken(new AsyncCallback<Optional>() {
            @Override
            public void onFailure(Throwable caught) {
                PAGE_CONTROLLER.changePage(LoginPage.NAME);
            }

            @Override
            public void onSuccess(Optional result) {
                if (result.isPresent()) {
                    sessionToken = (String) result.get();
                    if (History.getToken().isEmpty()) {
                        PAGE_CONTROLLER.changePage(SessionPage.NAME);
                    } else {
                        PAGE_CONTROLLER.changePage(History.getToken());
                    }

                } else {
                    PAGE_CONTROLLER.changePage(LoginPage.NAME);
                }
            }
        });
    }

    public static String getSessionToken() {
        return sessionToken;
    }
}
