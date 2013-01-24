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

import de.hofuniversity.iws.server.data.entities.*;
import javax.persistence.*;
import javax.persistence.criteria.*;

/**
 * Handler class encapsulating the database access to Game entities
 * Queries are coded with the JPA Criteria API
 * @author Oliver Schütz
 */
public class GameHandler {

    private static EntityManager entityManager = HibernateUtil
            .getEntityManagerFactory().createEntityManager();

    // Store game
    public static GameDBO store(GameDBO game) {
        GameDBO retval = game;

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
                GameDBO tmpGame = entityManager.find(GameDBO.class,
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
    public static GameDBO getGameEntity(long id, boolean detach) {
        return (GameDBO) GenericHandler.getEntity(GameDBO.class, id, detach);
    }
    
    public static GameDBO getGameEntity(String name, boolean detach) {
        GameDBO retval = null;

        try {
            if (entityManager.isOpen()) {
                entityManager.close();
            }
            entityManager = HibernateUtil.getEntityManagerFactory()
                    .createEntityManager();

            CriteriaBuilder criteriaBuilder = entityManager
                    .getCriteriaBuilder();
            CriteriaQuery<GameDBO> queryG = criteriaBuilder
                    .createQuery(GameDBO.class);
            Root<GameDBO> rootG = queryG
                    .from(GameDBO.class);
            queryG.where(
                    criteriaBuilder.equal(
                    rootG.get(GameDBO_.name), name));
            TypedQuery<GameDBO> typedGQuery = entityManager
                    .createQuery(queryG).setMaxResults(1);
            try {
                retval = typedGQuery.getSingleResult();
                if (detach) {
                    entityManager.detach(retval);
                    retval.setDetached(true);
                }
            } catch (NoResultException e) {
                retval = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retval;
    }
}
