/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client;

import com.chrisgammage.ginjitsu.client.JitsuInjector;
import com.google.gwt.inject.client.GinModules;

/**
 *
 * @author Daniel Heinrich <DannyNullZwo@gmail.com>
 */
@GinModules(PhysixLabModul.class)
public interface PhysixLabInjector extends JitsuInjector {

    public PhysixLab createLab();
}
