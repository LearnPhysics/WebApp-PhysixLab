package de.hofuniversity.iws.server.data.handler;

import de.hofuniversity.iws.server.data.entities.GameResultDBO;
import javax.persistence.EntityManager;

public class GameResultHandler {

    private static EntityManager entityManager = HibernateUtil
            .getEntityManagerFactory().createEntityManager();

    // Store game result
    public static GameResultDBO store(GameResultDBO gameResult) {
        GameResultDBO retval = gameResult;

        if (entityManager.isOpen()) {
            entityManager.close();
        }
        entityManager = HibernateUtil.getEntityManagerFactory()
                .createEntityManager();

        try {
            if (gameResult.getId() == null) {
                entityManager.getTransaction().begin();
                if (gameResult.isDetached()) {
                    entityManager.merge(gameResult);
                } else {
                    entityManager.persist(gameResult);
                }
                entityManager.getTransaction().commit();
            } else {
                GameResultDBO tmpGameResult = entityManager.find(GameResultDBO.class,
                        gameResult.getId());

                if (tmpGameResult == null) // Phrase does not exist in the
                // database
                {
                    entityManager.getTransaction().begin();
                    if (gameResult.isDetached()) {
                        entityManager.merge(gameResult);
                    } else {
                        entityManager.persist(gameResult);
                    }
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
    public static GameResultDBO getGameResultEntity(long id, boolean detach) {
        return (GameResultDBO) GenericHandler.getEntity(GameResultDBO.class, id, detach);
    }
}
