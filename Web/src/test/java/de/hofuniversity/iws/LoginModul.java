package de.hofuniversity.iws;

import com.google.gwt.core.client.*;
import de.hofuniversity.iws.client.widgets.LoginPage;

import com.google.gwt.user.client.ui.RootPanel;
import com.google.inject.Inject;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Daniel Heinrich <dannynullzwo@gmail.com>
 */
public class LoginModul implements EntryPoint {

  private final TestInjector injector = GWT.create(TestInjector.class);
    private LoginPage login;

    @Inject
    public LoginModul() {
        login = injector.getLoginPage().create();
    }

    @Override
    public void onModuleLoad() {
        RootPanel.get().add(login);
    }

    public LoginPage getLogin() {
        return login;
    }
}
