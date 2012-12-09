package de.hofuniversity.iws.server.data.tests;

import de.hofuniversity.iws.server.data.entities.Game;
import de.hofuniversity.iws.server.data.handler.GameHandler;
import de.hofuniversity.iws.server.data.handler.HibernateUtil;

public class Test {

    public static void main(String[] args) {

        HibernateUtil.isConnectedToDB();
        Game game = new Game();
        game.setName("Testgame");
        GameHandler.store(game);
        Game ret2 = GameHandler.getGameEntity(1, true);
        System.out.println(ret2.getName() + " is detached(" + ret2.isDetached() + ") from context.");
    }
}
