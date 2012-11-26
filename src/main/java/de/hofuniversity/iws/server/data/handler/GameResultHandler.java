package de.hofuniversity.iws.server.data.handler;

import javax.persistence.EntityManager;

import de.hofuniversity.iws.server.data.entities.GameResult;

public class GameResultHandler {

    private static EntityManager entityManager = HibernateUtil
            .getEntityManagerFactory().createEntityManager();

    // Store game result
    public static GameResult store(GameResult gameResult) {
        GameResult retval = gameResult;

        if (entityManager.isOpen()) {
            entityManager.close();
        }
        entityManager = HibernateUtil.getEntityManagerFactory()
                .createEntityManager();

        try {
            if (gameResult.getId() == null) {
                entityManager.getTransaction().begin();
                entityManager.persist(gameResult);
                entityManager.getTransaction().commit();
            } else {
                GameResult tmpGameResult = entityManager.find(GameResult.class,
                        gameResult.getId());

                if (tmpGameResult == null) // Phrase does not exist in the
                // database
                {
                    entityManager.getTransaction().begin();
                    entityManager.persist(gameResult);
                    entityManager.getTransaction().commit();
                } else // phrase exists already in the Database - update the
                // Attributes
                {
                    // update values
                    tmpGameResult.setDate(gameResult.getDate());
                    tmpGameResult.setPoints(gameResult.getPoints());
                    tmpGameResult.setUser(gameResult.getUser());
                    tmpGameResult.setGame(gameResult.getGame());

                    // write values
                    entityManager.getTransaction().begin();
                    entityManager.persist(tmpGameResult);
                    entityManager.getTransaction().commit();
                    retval = tmpGameResult;
                }
            }
        } catch (Exception e) {
            if (gameResult.isDetached()) {
                e.printStackTrace();
            } else {
                gameResult.setDetached(true);
                return store(gameResult);
            }
        }
        return retval;
    }

    // Get game result by Id
    public static GameResult getGameEntity(long id, boolean detach) {
        return (GameResult) GenericHandler.getEntity(GameResult.class, id, detach);
    }
}
