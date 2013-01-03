/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.server;

import java.util.*;

import de.hofuniversity.iws.shared.games.GameFactory;

import com.google.common.base.Optional;

/**
 *
 * @author Daniel Heinrich <dannynullzwo@gmail.com>
 */
public class GameFactorys {

    public static Optional<GameFactory> get(String gameClassName) {
        Objects.requireNonNull(gameClassName);
        for (GameFactory fac : ServiceLoader.load(GameFactory.class)) {
            if (gameClassName.equals(fac.getGameClass().getCanonicalName())) {
                return Optional.of(fac);
            }
        }
        return Optional.absent();
    }

    public static Iterable<GameFactory> getAll() {
        return ServiceLoader.load(GameFactory.class);
    }
}
