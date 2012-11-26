package de.hofuniversity.iws.server.data.handler;

import javax.persistence.EntityManager;

import de.hofuniversity.iws.server.data.entities.Lesson;

public class LessonHandler {

    private static EntityManager entityManager = HibernateUtil
            .getEntityManagerFactory().createEntityManager();

    // Store lesson
    public static Lesson store(Lesson lesson) {
        Lesson retval = lesson;

        if (entityManager.isOpen()) {
            entityManager.close();
        }
        entityManager = HibernateUtil.getEntityManagerFactory()
                .createEntityManager();

        try {
            if (lesson.getId() == null) {
                entityManager.getTransaction().begin();
                entityManager.persist(lesson);
                entityManager.getTransaction().commit();
            } else {
                Lesson tmpLesson = entityManager.find(Lesson.class,
                        lesson.getId());

                if (tmpLesson == null) // Phrase does not exist in the
                // database
                {
                    entityManager.getTransaction().begin();
                    entityManager.persist(lesson);
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
    public static Lesson getGameEntity(long id, boolean detach) {
        return (Lesson) GenericHandler.getEntity(Lesson.class, id, detach);
    }
}
