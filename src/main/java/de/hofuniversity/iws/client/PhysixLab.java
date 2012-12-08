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
        loginService.getSessionToken(new AsyncCallback<Optional<String>>() {
            @Override
            public void onFailure(Throwable caught) {
                gotoPage(new LoginPage());
            }

            @Override
            public void onSuccess(Optional<String> result) {
                if (result.isPresent()) {
                    String token = result.get();
                    gotoPage(new SessionPage(token));
                } else {
                    gotoPage(new LoginPage());
                }
            }
        });
    }

    private void gotoPage(Composite page) {
        RootPanel.get().clear();
        RootPanel.get().add(page);
    }
}
