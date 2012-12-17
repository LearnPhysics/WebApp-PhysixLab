/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client;

import com.google.gwt.inject.client.*;

/**
 *
 * @author Daniel Heinrich <dannynullzwo@gmail.com>
 */
@GinModules(PhysixLabModul.class)
public interface PhysixLabInjector extends Ginjector {

    public PhysixLab getPhysixLab();
}
