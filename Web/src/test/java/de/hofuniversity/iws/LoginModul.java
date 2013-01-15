package de.hofuniversity.iws;

import com.google.gwt.core.client.*;
import de.hofuniversity.iws.client.widgets.LoginPage;

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

//    private LoginPage login;

    public LoginModul() {
        TestInjector.INSTANCE.get().create("asd");
    }

    @Override
    public void onModuleLoad() {
//        RootPanel.get().add(login);
    }

//    public LoginPage getLogin() {
//        return login;
//    }
}
