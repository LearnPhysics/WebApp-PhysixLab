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

import de.hofuniversity.iws.server.data.entities.GenericEntity;
import javax.persistence.EntityManager;

/**
 *
 * @author Oliver Schütz
 */
public class GenericHandler {

    private static EntityManager entityManager = HibernateUtil
            .getEntityManagerFactory().createEntityManager();

    public static GenericEntity store(Class c, GenericEntity entity) {

        GenericEntity retval = entity;

        if (entityManager.isOpen()) {
            entityManager.close();
        }
        entityManager = HibernateUtil.getEntityManagerFactory()
                .createEntityManager();

        try {
            if (entity.getId() == null) { // Persisting a new entity
                entityManager.getTransaction().begin();
                if (entity.isDetached()) {
                    entityManager.merge(entity);
                } else {
                    entityManager.persist(entity);
                }
                entityManager.getTransaction().commit();
            } else {
                GenericEntity tmpEntity = (GenericEntity) entityManager.find(c,
                        entity.getId());

                if (tmpEntity == null) // Persisting a new entity. For some reason a id was given manually (?)
                // database
                {
                    entityManager.getTransaction().begin();
                    if (entity.isDetached()) {
                        entityManager.merge(entity);
                    } else {
                        entityManager.persist(entity);
                    }
                    entityManager.getTransaction().commit();
                } else // Update the already existing entity
                {
                    // write values
                    entityManager.getTransaction().begin();
                    entityManager.persist(tmpEntity);
                    entityManager.getTransaction().commit();
                    retval = tmpEntity;
                }
            }
        } catch (Exception e) {
            if (entity.isDetached()) {
                e.printStackTrace();
            } else {
                entity.setDetached(true);
                return store(c, entity);
            }
        }
        return retval;
    }

    public static GenericEntity getEntity(Class c, long id, boolean detach) {
        if (entityManager.isOpen()) {
            entityManager.close();
        }
        entityManager = HibernateUtil.getEntityManagerFactory()
                .createEntityManager();
        GenericEntity retval = (GenericEntity) entityManager.find(c, id);
        if (detach) {
            entityManager.detach(retval);
            retval.setDetached(true);
        }
        return retval;
    }
}
