/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client;

import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import de.hofuniversity.iws.client.widgets.PhysixLabWidget;
import de.hofuniversity.iws.client.widgets.UserInfoWidget;

/**
 *
 * @author Daniel Heinrich <dannynullzwo@gmail.com>
 */
@GinModules(PhysixLabModul.class)
public interface PhysixLabInjector extends Ginjector {

    public PhysixLabWidget getPhysixLab();
    public UserInfoWidget getUserInfoWidget();
}
