/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client;


import com.google.common.base.Optional;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.RootPanel;
import de.hofuniversity.iws.client.widgets.LoginPage;
import de.hofuniversity.iws.client.widgets.SessionPage;
import de.hofuniversity.iws.shared.services.LoginServiceAsync;
import javax.inject.Inject;

/**
 *
 * @author Daniel Heinrich <dannynullzwo@gmail.com>
 */
public class PhysixLab {

    @Inject
    private LoginServiceAsync loginService;

    public void init() {
        loginService.getSessionToken(new AsyncCallback<Optional>() {
            @Override
            public void onFailure(Throwable caught) {
                gotoPage(new LoginPage(loginService));
            }

            @Override
            public void onSuccess(Optional result) {
                if (result.isPresent()) {
                    String token = (String) result.get();
                    gotoPage(new SessionPage(loginService, token));
                } else {
                    gotoPage(new LoginPage(loginService));
                }
            }
        });
    }

    private void gotoPage(Composite page) {
        RootPanel.get().clear();
        RootPanel.get().add(page);
    }
}
