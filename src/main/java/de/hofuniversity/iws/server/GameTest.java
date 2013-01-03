package de.hofuniversity.iws.server;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.net.*;
import java.util.ServiceLoader;
import de.hofuniversity.iws.client.playn.*;
import de.hofuniversity.iws.shared.games.GameFactory;

import javax.swing.*;
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
        JFrame frame = new JFrame();
        Container main = frame.getContentPane();
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));

        for (final GameFactory game : GameFactorys.getAll()) {
            JButton but = new JButton("start " + game.getGameClass().getSimpleName());
            but.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    startGame(game.create(), 800);
                }
            });
            main.add(but);
        }
        
        frame.setMinimumSize(new Dimension(200, 400));
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
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
