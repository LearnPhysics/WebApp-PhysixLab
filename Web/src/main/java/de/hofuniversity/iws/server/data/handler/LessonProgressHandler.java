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

import de.hofuniversity.iws.server.data.entities.LessonProgressDBO;
import javax.persistence.EntityManager;

/**
 * Handler class encapsulating the database access to LessonProgress entities
 * @author Oliver Schütz
 */
public class LessonProgressHandler {

    private static EntityManager entityManager = HibernateUtil
            .getEntityManagerFactory().createEntityManager();

    // Store lesson progress
    public static LessonProgressDBO store(LessonProgressDBO lessonProgress) {
        LessonProgressDBO retval = lessonProgress;

        if (entityManager.isOpen()) {
            entityManager.close();
        }
        entityManager = HibernateUtil.getEntityManagerFactory()
                .createEntityManager();

        try {
            if (lessonProgress.getId() == null) {
                entityManager.getTransaction().begin();
                if (lessonProgress.isDetached()) {
                    entityManager.merge(lessonProgress);
                } else {
                    entityManager.persist(lessonProgress);
                }
                entityManager.getTransaction().commit();
            } else {
                LessonProgressDBO tmpLessonProgress = entityManager.find(LessonProgressDBO.class,
                        lessonProgress.getId());

                if (tmpLessonProgress == null) // Phrase does not exist in the
                // database
                {
                    entityManager.getTransaction().begin();
                    if (lessonProgress.isDetached()) {
                        entityManager.merge(lessonProgress);
                    } else {
                        entityManager.persist(lessonProgress);
                    }
                    entityManager.getTransaction().commit();
                } else // phrase exists already in the Database - update the
                // Attributes
                {
                    // update values
                    tmpLessonProgress.setDate(lessonProgress.getDate());
                    tmpLessonProgress.setPoints(lessonProgress.getPoints());
                    tmpLessonProgress.setUser(lessonProgress.getUser());
                    tmpLessonProgress.setLesson(lessonProgress.getLesson());

                    // write values
                    entityManager.getTransaction().begin();
                    entityManager.persist(tmpLessonProgress);
                    entityManager.getTransaction().commit();
                    retval = tmpLessonProgress;
                }
            }
        } catch (Exception e) {
            if (lessonProgress.isDetached()) {
                e.printStackTrace();
            } else {
                lessonProgress.setDetached(true);
                return store(lessonProgress);
            }
        }
        return retval;
    }

    // Get lesson progress by Id
    public static LessonProgressDBO getLessonProgressEntity(long id, boolean detach) {
        return (LessonProgressDBO) GenericHandler.getEntity(LessonProgressDBO.class, id, detach);
    }
}
