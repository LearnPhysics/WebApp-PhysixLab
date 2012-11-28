package de.hofuniversity.iws.server.data.handler;

import javax.persistence.EntityManager;

import de.hofuniversity.iws.server.data.entities.SubjectArea;

public class SubjectAreaHandler {

    private static EntityManager entityManager = HibernateUtil
            .getEntityManagerFactory().createEntityManager();

    // Store subject area
    public static SubjectArea store(SubjectArea subjectArea) {
        SubjectArea retval = subjectArea;

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
                SubjectArea tmpSubjectArea = entityManager.find(SubjectArea.class,
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
    public static SubjectArea getGameEntity(long id, boolean detach) {
        return (SubjectArea) GenericHandler.getEntity(SubjectArea.class, id, detach);
    }
}
