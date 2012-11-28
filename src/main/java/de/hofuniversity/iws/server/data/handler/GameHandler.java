package de.hofuniversity.iws.server.data.handler;

import javax.persistence.EntityManager;

import de.hofuniversity.iws.server.data.entities.Game;

public class GameHandler {

    private static EntityManager entityManager = HibernateUtil
            .getEntityManagerFactory().createEntityManager();

    // Store game
    public static Game store(Game game) {
        Game retval = game;

        if (entityManager.isOpen()) {
            entityManager.close();
        }
        entityManager = HibernateUtil.getEntityManagerFactory()
                .createEntityManager();

        try {
            if (game.getId() == null) {
                entityManager.getTransaction().begin();
                if (game.isDetached()) {
                    entityManager.merge(game);
                } else {
                    entityManager.persist(game);
                }
                entityManager.getTransaction().commit();
            } else {
                Game tmpGame = entityManager.find(Game.class,
                        game.getId());

                if (tmpGame == null) // Phrase does not exist in the
                // database
                {
                    entityManager.getTransaction().begin();
                    if (game.isDetached()) {
                        entityManager.merge(game);
                    } else {
                        entityManager.persist(game);
                    }
                    entityManager.getTransaction().commit();
                } else // phrase exists already in the Database - update the
                // Attributes
                {
                    // update values
                    tmpGame.setName(game.getName());
                    tmpGame.setGameResultList(game.getGameResultList());
                    tmpGame.setLessonList(game.getLessonList());

                    // write values
                    entityManager.getTransaction().begin();
                    entityManager.persist(tmpGame);
                    entityManager.getTransaction().commit();
                    retval = tmpGame;
                }
            }
        } catch (Exception e) {
            if (game.isDetached()) {
                e.printStackTrace();
            } else {
                game.setDetached(true);
                return store(game);
            }
        }
        return retval;
    }

    // Get game by Id
    public static Game getGameEntity(long id, boolean detach) {
        return (Game) GenericHandler.getEntity(Game.class, id, detach);
    }
}
