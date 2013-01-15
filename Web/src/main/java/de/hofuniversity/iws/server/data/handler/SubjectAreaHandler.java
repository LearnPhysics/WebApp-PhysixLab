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

import de.hofuniversity.iws.server.data.entities.SubjectAreaDBO;
import javax.persistence.EntityManager;

/**
 *
 * @author Oliver Schütz
 */
public class SubjectAreaHandler {

    private static EntityManager entityManager = HibernateUtil
            .getEntityManagerFactory().createEntityManager();

    // Store subject area
    public static SubjectAreaDBO store(SubjectAreaDBO subjectArea) {
        SubjectAreaDBO retval = subjectArea;

        if (entityManager.isOpen()) {
            entityManager.close();
        }
        entityManager = HibernateUtil.getEntityManagerFactory()
                .createEntityManager();

        try {
            if (subjectArea.getId() == null) {
                entityManager.getTransaction().begin();
                if (subjectArea.isDetached()) {
                    entityManager.merge(subjectArea);
                } else {
                    entityManager.persist(subjectArea);
                }
                entityManager.getTransaction().commit();
            } else {
                SubjectAreaDBO tmpSubjectArea = entityManager.find(SubjectAreaDBO.class,
                        subjectArea.getId());

                if (tmpSubjectArea == null) // Phrase does not exist in the
                // database
                {
                    entityManager.getTransaction().begin();
                    if (subjectArea.isDetached()) {
                        entityManager.merge(subjectArea);
                    } else {
                        entityManager.persist(subjectArea);
                    }
                    entityManager.getTransaction().commit();
                } else // phrase exists already in the Database - update the
                // Attributes
                {
                    // update values
                    tmpSubjectArea.setName(subjectArea.getName());
                    tmpSubjectArea.setLessonList(subjectArea.getLessonList());

                    // write values
                    entityManager.getTransaction().begin();
                    entityManager.persist(tmpSubjectArea);
                    entityManager.getTransaction().commit();
                    retval = tmpSubjectArea;
                }
            }
        } catch (Exception e) {
            if (subjectArea.isDetached()) {
                e.printStackTrace();
            } else {
                subjectArea.setDetached(true);
                return store(subjectArea);
            }
        }
        return retval;
    }

    // Get subject area by Id
    public static SubjectAreaDBO getGameEntity(long id, boolean detach) {
        return (SubjectAreaDBO) GenericHandler.getEntity(SubjectAreaDBO.class, id, detach);
    }
}
