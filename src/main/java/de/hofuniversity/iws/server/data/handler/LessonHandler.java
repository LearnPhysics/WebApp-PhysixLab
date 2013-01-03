package de.hofuniversity.iws.server.data.handler;

import de.hofuniversity.iws.shared.entityimpl.LessonDBO;

import javax.persistence.EntityManager;

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
    public static LessonDBO getGameEntity(long id, boolean detach) {
        return (LessonDBO) GenericHandler.getEntity(LessonDBO.class, id, detach);
    }
}
