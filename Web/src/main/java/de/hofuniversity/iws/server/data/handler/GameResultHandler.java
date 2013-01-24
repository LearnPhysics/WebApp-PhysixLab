/*
 * Copyright (C) 2012 Oliver Schütz
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
package de.hofuniversity.iws.server.data.handler;

import de.hofuniversity.iws.server.data.entities.GameResultDBO;
import javax.persistence.EntityManager;

/**
 * Handler class encapsulating the database access to GameResult entities
 * @author Oliver Schütz
 */
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
