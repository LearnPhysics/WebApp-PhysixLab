/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client;

import com.google.gwt.core.client.EntryPoint;

/**
 *
 * @author Daniel Heinrich <dannynullzwo@gmail.com>
 */
public class Main implements EntryPoint {

    @Override
    public void onModuleLoad() {
        new PhysixLab().init();
    }
}