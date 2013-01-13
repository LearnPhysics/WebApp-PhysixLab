package de.hofuniversity.iws;


import de.hofuniversity.iws.client.widgets.LoginPage;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Daniel Heinrich <dannynullzwo@gmail.com>
 */
public class LoginModul implements EntryPoint {

    private LoginPage login;

    @Override
    public void onModuleLoad() {
        login = new LoginPage();
        RootPanel.get().add(login);
    }

    public LoginPage getLogin() {
        return login;
    }
}
