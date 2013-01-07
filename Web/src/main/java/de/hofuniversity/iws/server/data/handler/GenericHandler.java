package de.hofuniversity.iws.server.data.handler;

import de.hofuniversity.iws.server.data.entities.GenericEntity;
import javax.persistence.EntityManager;

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
