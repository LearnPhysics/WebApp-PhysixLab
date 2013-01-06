/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.playn;

/**
 *
 * @author some
 */
public interface GameInstantiator {
    public PhysicGame createGame(String clazz);
}
