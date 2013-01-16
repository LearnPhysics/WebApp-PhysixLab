/*
 * Copyright (C) 2012 Daniel Heinrich
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package de.hofuniversity.iws.server;

import java.net.MalformedURLException;

import de.hofuniversity.iws.client.playn.*;
import de.hofuniversity.iws.client.playn.games.KineticWars;
import playn.core.PlayN;
import playn.java.JavaPlatform;
import playn.java.JavaPlatform.Config;

/**
 *
 * @author Daniel Heinrich <DannyNullZwo@gmail.com>
 */
public class GameTest {

    public static void main(String[] args) throws MalformedURLException {
//        JFrame frame = new JFrame();
//        Container main = frame.getContentPane();
//        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
//
//        for (final GameFactory game : GameFactorys.getAll()) {
//            JButton but = new JButton("start " + game.getGameClass().getSimpleName());
//            but.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    startGame(game.create(), 800);
//                }
//            });
//            main.add(but);
//        }
//        
//        frame.setMinimumSize(new Dimension(200, 400));
//        frame.setLocationRelativeTo(null);
//        frame.pack();
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setVisible(true);
        startGame(new KineticWars(), 600);
    }

    private static void startGame(final PhysicGame game, int width) {
        PhysicGameBox g = new PhysicGameBox(game, width, new GameEventListener() {
            @Override
            public void gameEnded(GameEvent ev) {
                System.out.println("Game finished with " + ev.points + " points!");
                game.restart();
            }
        });

        JavaPlatform.Config c = new Config();
        c.width = width;
        c.height = g.getHeight();

        JavaPlatform.register(c);

        PlayN.run(g);
    }
}
