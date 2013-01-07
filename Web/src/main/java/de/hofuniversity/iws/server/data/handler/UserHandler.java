package de.hofuniversity.iws.server.data.handler;

import java.util.*;

import com.google.common.base.Optional;
import de.hofuniversity.iws.server.oauth.Providers;
import de.hofuniversity.iws.shared.entityimpl.*;
import javax.persistence.*;
import javax.persistence.criteria.*;
import org.scribe.model.Token;

public class UserHandler {

    private static EntityManager entityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();

    // Store user
    public static UserDBO store(UserDBO user) {
        UserDBO retval = user;

//        if (hasDuplicateProvider(user)) {
//            //TODO log warning
//        }

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
                UserDBO tmpUser = entityManager.find(UserDBO.class,
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
                    tmpUser.setFriends(user.getFriends());
                    tmpUser.setDevotees(user.getDevotees());
                    
                    // write values
                    entityManager.getTransaction().begin();
                    entityManager.merge(user);
                    entityManager.getTransaction().commit();
                    retval = user;
                }
            }
        } catch (Exception e) {
            if (user.isDetached()) {
                //e.printStackTrace();
            } else {
                user.setDetached(true);
                return store(user);
            }
        }
        return retval;
    }

    // Get user by Id
    public static UserDBO getUserEntity(long id, boolean detach) {
        return (UserDBO) GenericHandler.getEntity(UserDBO.class, id, detach);
    }

    // Get list of all users
    public static List<UserDBO> getAllUsers() {
        List<UserDBO> userList = null;
        try {
            if (entityManager.isOpen()) {
                entityManager.close();
            }
            entityManager = HibernateUtil.getEntityManagerFactory()
                    .createEntityManager();

            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<UserDBO> criteria = builder
                    .createQuery(UserDBO.class);
            Root<UserDBO> userEntityRoot = criteria.from(UserDBO.class);
            criteria.select(userEntityRoot);
            criteria.where(builder.greaterThan(
                    userEntityRoot.get(UserDBO_.id), -1L));

            TypedQuery query = entityManager.createQuery(criteria);
            userList = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return userList;
    }

    public static UserDBO getUser(Token oauthAccessToken, Providers networkName) {
        NetworkAccountDBO na = NetworkAccountHandler.getNetworkAccountEntityByAccessToken(networkName, oauthAccessToken, true);
        return na != null ? na.getUser() : null;
    }

    public static UserDBO getUserByAIDString(String accountIdentificationString, Providers networkName) {
        NetworkAccountDBO na = NetworkAccountHandler.getNetworkAccountEntity(networkName, accountIdentificationString, true);
        return na != null ? na.getUser() : null;
    }

    private static boolean hasDuplicateProvider(UserDBO user) {
        HashSet<String> s = new HashSet<String>();
        for (NetworkAccountDBO na : user.getNetworkAccountList()) {
            if (!s.add(na.getNetworkName())) {
                return true;
            }
        }
        return false;
    }

    public static Optional<NetworkAccountDBO> getNetworkAccount(UserDBO user, Providers prov) {
        for (NetworkAccountDBO na : user.getNetworkAccountList()) {
            if (prov.name().equals(na.getNetworkName())) {
                return Optional.of(na);
            }
        }
        return Optional.absent();
    }
}
