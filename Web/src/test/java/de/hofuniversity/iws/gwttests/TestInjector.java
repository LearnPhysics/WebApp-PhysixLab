/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.gwttests;

import com.chrisgammage.ginjitsu.client.JitsuInjector;
import com.google.gwt.core.client.GWT;
import com.google.gwt.inject.client.GinModules;
import de.hofuniversity.iws.client.*;
import de.hofuniversity.iws.client.widgets.LoginPage;

/**
 *
 * @author Daniel Heinrich <DannyNullZwo@gmail.com>
 */
@GinModules(TestGinModule.class)
public interface TestInjector extends JitsuInjector {

//    public static final TestInjector INSTANCE = GWT.create(TestInjector.class);

    public LoginPage getLoginPage();

    public PhysixLab getLab();

    public HistoryPageController getPageController();
}
