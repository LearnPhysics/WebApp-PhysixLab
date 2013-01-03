/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.shared.games;

import darwin.annotations.ServiceProvider;

import de.hofuniversity.iws.client.playn.games.KineticWars;

/**
 *
 * @author Daniel Heinrich <dannynullzwo@gmail.com>
 */
@ServiceProvider(GameFactory.class)
public class KineticWarsFactory implements GameFactory<KineticWars> {

    @Override
    public KineticWars create() {
        return new KineticWars();
    }

    @Override
    public Class<KineticWars> getGameClass() {
        return KineticWars.class;
    }
}
