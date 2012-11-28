package de.hofuniversity.iws.server.data.handler;

import javax.persistence.EntityManager;

import de.hofuniversity.iws.server.data.entities.User;

public class UserHandler {

    private static EntityManager entityManager = HibernateUtil
            .getEntityManagerFactory().createEntityManager();

    // Store user
    public static User store(User user) {
        User retval = user;

        if (entityManager.isOpen()) {
            entityManager.close();
        }
        entityManager = HibernateUtil.getEntityManagerFactory()
                .createEntityManager();

        try {
            if (user.getId() == null) {
                entityManager.getTransaction().begin();
                if (user.isDetached()) {
                    entityManager.merge(user);
                } else {
                    entityManager.persist(user);
                }
                entityManager.getTransaction().commit();
            } else {
                User tmpUser = entityManager.find(User.class,
                        user.getId());

                if (tmpUser == null) // Phrase does not exist in the
                // database
                {
                    entityManager.getTransaction().begin();
                    if (user.isDetached()) {
                        entityManager.merge(user);
                    } else {
                        entityManager.persist(user);
                    }
                    entityManager.getTransaction().commit();
                } else // phrase exists already in the Database - update the
                // Attributes
                {
                    // update values
                    tmpUser.setUserName(user.getUserName());
                    tmpUser.setLastName(user.getLastName());
                    tmpUser.setFirstName(user.getFirstName());
                    tmpUser.setBirthDate(user.getBirthDate());
                    tmpUser.setCity(user.getCity());
                    tmpUser.setUserPic(user.getUserPic());
                    tmpUser.setNetworkAccountList(user.getNetworkAccountList());
                    tmpUser.setGameResultList(user.getGameResultList());
                    tmpUser.setLessonProgressList(user.getLessonProgressList());

                    // write values
                    entityManager.getTransaction().begin();
                    entityManager.persist(tmpUser);
                    entityManager.getTransaction().commit();
                    retval = tmpUser;
                }
            }
        } catch (Exception e) {
            if (user.isDetached()) {
                e.printStackTrace();
            } else {
                user.setDetached(true);
                return store(user);
            }
        }
        return retval;
    }

    // Get user by Id
    public static User getGameEntity(long id, boolean detach) {
        return (User) GenericHandler.getEntity(User.class, id, detach);
    }
}
