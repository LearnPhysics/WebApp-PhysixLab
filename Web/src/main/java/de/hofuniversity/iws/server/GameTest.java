package de.hofuniversity.iws.server;

import de.hofuniversity.iws.client.playn.PhysicGame;
import java.net.MalformedURLException;

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

    private static void startGame(PhysicGame game, int width) {
        PhysicGameBox g = new PhysicGameBox(game, width);

        JavaPlatform.Config c = new Config();
        c.width = width;
        c.height = g.getHeight();
        
        JavaPlatform.register(c);

        PlayN.run(g);
    }
}
