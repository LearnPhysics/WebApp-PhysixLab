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
 * Handler class encapsulating the database access to lesson entities
 * Queries are coded with the JPA Criteria API
 * @author Oliver Schütz
 */
public class LessonHandler {

    private static EntityManager entityManager = HibernateUtil
            .getEntityManagerFactory().createEntityManager();

    // Store lesson
    public static LessonDBO store(LessonDBO lesson) {
        LessonDBO retval = lesson;

        if (entityManager.isOpen()) {
            entityManager.close();
        }
        entityManager = HibernateUtil.getEntityManagerFactory()
                .createEntityManager();

        try {
            if (lesson.getId() == null) {
                entityManager.getTransaction().begin();
                if (lesson.isDetached()) {
                    entityManager.merge(lesson);
                } else {
                    entityManager.persist(lesson);
                }
                entityManager.getTransaction().commit();
            } else {
                LessonDBO tmpLesson = entityManager.find(LessonDBO.class,
                        lesson.getId());

                if (tmpLesson == null) // Phrase does not exist in the
                // database
                {
                    entityManager.getTransaction().begin();
                    if (lesson.isDetached()) {
                        entityManager.merge(lesson);
                    } else {
                        entityManager.persist(lesson);
                    }
                    entityManager.getTransaction().commit();
                } else // phrase exists already in the Database - update the
                // Attributes
                {
                    // update values
                    tmpLesson.setName(lesson.getName());
                    tmpLesson.setLessonProgressList(lesson.getLessonProgressList());
                    tmpLesson.setSubjectArea(lesson.getSubjectArea());
                    tmpLesson.setGameList(lesson.getGameList());
                    tmpLesson.setParentLesson(lesson.getParentLesson());

                    // write values
                    entityManager.getTransaction().begin();
                    entityManager.persist(tmpLesson);
                    entityManager.getTransaction().commit();
                    retval = tmpLesson;
                }
            }
        } catch (Exception e) {
            if (lesson.isDetached()) {
                e.printStackTrace();
            } else {
                lesson.setDetached(true);
                return store(lesson);
            }
        }
        return retval;
    }

    // Get lesson by Id
    public static LessonDBO getLessonEntity(long id, boolean detach) {
        return (LessonDBO) GenericHandler.getEntity(LessonDBO.class, id, detach);
    }
    
    public static LessonDBO getLessonEntityOrCreateTemplate(String name, boolean detach) {
        LessonDBO retval = null;

        try {
            if (entityManager.isOpen()) {
                entityManager.close();
            }
            entityManager = HibernateUtil.getEntityManagerFactory()
                    .createEntityManager();

            CriteriaBuilder criteriaBuilder = entityManager
                    .getCriteriaBuilder();
            CriteriaQuery<LessonDBO> queryL = criteriaBuilder
                    .createQuery(LessonDBO.class);
            Root<LessonDBO> rootL = queryL
                    .from(LessonDBO.class);
            queryL.where(
                    criteriaBuilder.equal(
                    rootL.get(LessonDBO_.name), name));
            TypedQuery<LessonDBO> typedGQuery = entityManager
                    .createQuery(queryL).setMaxResults(1);
            try {
                retval = typedGQuery.getSingleResult();
                if (detach) {
                    entityManager.detach(retval);
                    retval.setDetached(true);
                }
            } catch (NoResultException e) {
                retval = null;
                retval = new LessonDBO();
                retval.setName(name);  
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retval;
    }
}
