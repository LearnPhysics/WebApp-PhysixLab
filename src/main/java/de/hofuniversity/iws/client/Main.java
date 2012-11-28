/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;

/**
 *
 * @author Daniel Heinrich <dannynullzwo@gmail.com>
 */
public class Main implements EntryPoint {

    private final PhysixLabInjector injector = GWT.create(PhysixLabInjector.class);

    @Override
    public void onModuleLoad() {
        PhysixLab physixLab = injector.getPhysixLab();
        physixLab.init();
    }
}
