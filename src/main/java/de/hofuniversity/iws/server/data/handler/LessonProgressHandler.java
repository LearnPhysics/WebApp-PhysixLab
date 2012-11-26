package de.hofuniversity.iws.server.data.handler;

import javax.persistence.EntityManager;

import de.hofuniversity.iws.server.data.entities.LessonProgress;

public class LessonProgressHandler {

    private static EntityManager entityManager = HibernateUtil
            .getEntityManagerFactory().createEntityManager();

    // Store lesson progress
    public static LessonProgress store(LessonProgress lessonProgress) {
        LessonProgress retval = lessonProgress;

        if (entityManager.isOpen()) {
            entityManager.close();
        }
        entityManager = HibernateUtil.getEntityManagerFactory()
                .createEntityManager();

        try {
            if (lessonProgress.getId() == null) {
                entityManager.getTransaction().begin();
                entityManager.persist(lessonProgress);
                entityManager.getTransaction().commit();
            } else {
                LessonProgress tmpLessonProgress = entityManager.find(LessonProgress.class,
                        lessonProgress.getId());

                if (tmpLessonProgress == null) // Phrase does not exist in the
                // database
                {
                    entityManager.getTransaction().begin();
                    entityManager.persist(lessonProgress);
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
    public static LessonProgress getGameEntity(long id, boolean detach) {
        return (LessonProgress) GenericHandler.getEntity(LessonProgress.class, id, detach);
    }
}
