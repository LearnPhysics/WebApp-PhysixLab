/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;
import de.hofuniversity.iws.client.widgets.PhysixLabWidget;
import de.hofuniversity.iws.client.widgets.UserInfoWidget;

/**
 *
 * @author Daniel Heinrich <dannynullzwo@gmail.com>
 */
public class Main implements EntryPoint {

    PhysixLabWidget physixLab = null;
    UserInfoWidget userinfo = null;
    
    private final PhysixLabInjector injector = GWT.create(PhysixLabInjector.class);
    
    public void onModuleLoad() {
        
        
        physixLab = injector.getPhysixLab();
        userinfo = injector.getUserInfoWidget();
        RootPanel.get().add(userinfo);    
        RootPanel.get().add(physixLab);
   }
}
