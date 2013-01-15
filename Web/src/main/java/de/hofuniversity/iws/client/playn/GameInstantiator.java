/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.playn;

/**
 *
 * @author Daniel Heinrich <DannyNullZwo@gmail.com>
 */
//don't change anything, because this interface is used in a gwt generator with hardcoded names
public interface GameInstantiator {
    public PhysicGame createGame(String clazz);
}
