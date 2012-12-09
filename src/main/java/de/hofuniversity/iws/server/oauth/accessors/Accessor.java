/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.server.oauth.accessors;

import de.hofuniversity.iws.server.oauth.Providers;

/**
 *
 * @author Daniel Heinrich <dannynullzwo@gmail.com>
 */
public interface Accessor {
    public Providers supportedProvider();
}
