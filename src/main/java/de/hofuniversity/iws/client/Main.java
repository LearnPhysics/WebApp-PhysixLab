/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;
import de.hofuniversity.iws.client.widgets.PhysixLabWidget;

/**
 *
 * @author Daniel Heinrich <dannynullzwo@gmail.com>
 */
public class Main implements EntryPoint {

    private final PhysixLabInjector injector = GWT.create(PhysixLabInjector.class);

    public void onModuleLoad() {
        PhysixLabWidget physixLab = injector.getPhysixLab();
        RootPanel.get().add(physixLab);
    }
}
