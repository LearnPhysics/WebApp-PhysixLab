package de.hofuniversity.iws.server;

import de.hofuniversity.iws.client.playn.*;
import de.hofuniversity.iws.client.playn.games.KineticWars;
import playn.core.PlayN;
import playn.java.JavaPlatform;
import playn.java.JavaPlatform.Config;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author some
 */
public class GameTest {

    public static void main(String[] args) {
        startGame(new KineticWars(), 1600);
    }

    private static void startGame(PhysicGame game, int width) {
        PhysicGameBox g = new PhysicGameBox(game, width);

        JavaPlatform.Config c = new Config();
        c.width = width;
        c.height = g.getHeight();
        JavaPlatform.register(c);

        PlayN.run(g);
    }
}
