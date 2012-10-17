/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.html;

import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import de.hofuniversity.iws.core.WebApp;

/**
 *
 * @author Daniel Heinrich <dannynullzwo@gmail.com>
 */
@GinModules(WebAppModul.class)
public interface WebAppInjector extends Ginjector{
    WebApp createApp();
}
