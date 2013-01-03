/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.shared;

import java.io.Serializable;

import de.hofuniversity.iws.client.playn.StandardPhysicGame;

/**
 *
 * @author Daniel Heinrich <dannynullzwo@gmail.com>
 */
public interface GameFactory<T extends StandardPhysicGame> extends Serializable {

    public T create();

    public Class<T> getGameClass();
}
