package de.hofuniversity.iws.server.data.handler;

import de.hofuniversity.iws.shared.entityimpl.*;
import javax.persistence.*;
import javax.persistence.criteria.*;

public class NetworkAccountHandler {

    private static EntityManager entityManager = HibernateUtil
            .getEntityManagerFactory().createEntityManager();

    // Store network account
    public static NetworkAccountDBO store(NetworkAccountDBO networkAccount) {
        NetworkAccountDBO retval = networkAccount;

        if (entityManager.isOpen()) {
            entityManager.close();
        }
        entityManager = HibernateUtil.getEntityManagerFactory()
                .createEntityManager();

        try {
            if (networkAccount.getId() == null) {
                entityManager.getTransaction().begin();
                if (networkAccount.isDetached()) {
                    entityManager.merge(networkAccount);
                } else {
                    entityManager.persist(networkAccount);
                }
                entityManager.getTransaction().commit();
            } else {
                NetworkAccountDBO tmpNetworkAccount = entityManager.find(NetworkAccountDBO.class,
                        networkAccount.getId());

                if (tmpNetworkAccount == null) // Phrase does not exist in the
                // database
                {
                    entityManager.getTransaction().begin();
                    if (networkAccount.isDetached()) {
                        entityManager.merge(networkAccount);
                    } else {
                        entityManager.persist(networkAccount);
                    }
                    entityManager.getTransaction().commit();
                } else // phrase exists already in the Database - update the
                // Attributes
                {
                    // update values
                    tmpNetworkAccount.setNetworkName(networkAccount.getNetworkName());
                    tmpNetworkAccount.setAccountIdentificationString(networkAccount.getAccountIdentificationString());
                    tmpNetworkAccount.setOauthAccessToken(networkAccount.getOauthAccessToken());
                    tmpNetworkAccount.setUser(networkAccount.getUser());

                    // write values
                    entityManager.getTransaction().begin();
                    entityManager.persist(tmpNetworkAccount);
                    entityManager.getTransaction().commit();
                    retval = tmpNetworkAccount;
                }
            }
        } catch (Exception e) {
            if (networkAccount.isDetached()) {
                e.printStackTrace();
            } else {
                networkAccount.setDetached(true);
                return store(networkAccount);
            }
        }
        return retval;
    }

    // Get network account by Id
    public static NetworkAccountDBO getNetworkAccountEntity(long id, boolean detach) {
        return (NetworkAccountDBO) GenericHandler.getEntity(NetworkAccountDBO.class, id, detach);
    }

    public static NetworkAccountDBO getNetworkAccountEntity(String networkName, String accountIdentificationString, boolean detach) {
        NetworkAccountDBO retval = null;

        try {
            if (entityManager.isOpen()) {
                entityManager.close();
            }
            entityManager = HibernateUtil.getEntityManagerFactory()
                    .createEntityManager();

            CriteriaBuilder criteriaBuilder = entityManager
                    .getCriteriaBuilder();
            CriteriaQuery<NetworkAccountDBO> queryNA = criteriaBuilder
                    .createQuery(NetworkAccountDBO.class);
            Root<NetworkAccountDBO> rootNA = queryNA
                    .from(NetworkAccountDBO.class);
            queryNA.where(
                    criteriaBuilder.equal(
                    rootNA.get(NetworkAccountDBO_.networkName), networkName),
                    criteriaBuilder.equal(
                    rootNA.get(NetworkAccountDBO_.accountIdentificationString), accountIdentificationString));
            TypedQuery<NetworkAccountDBO> typedNAQuery = entityManager
                    .createQuery(queryNA).setMaxResults(1);
            try {
                retval = typedNAQuery.getSingleResult();
                if (detach) {
                    entityManager.detach(retval);
                    retval.setDetached(true);
                }
            } catch (NoResultException e) {
                retval = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retval;
    }
    
    public static NetworkAccountDBO getNetworkAccountEntityByAccessToken(String networkName, String oauthAccesToken, boolean detach) {
        NetworkAccountDBO retval = null;

        try {
            if (entityManager.isOpen()) {
                entityManager.close();
            }
            entityManager = HibernateUtil.getEntityManagerFactory()
                    .createEntityManager();

            CriteriaBuilder criteriaBuilder = entityManager
                    .getCriteriaBuilder();
            CriteriaQuery<NetworkAccountDBO> queryNA = criteriaBuilder
                    .createQuery(NetworkAccountDBO.class);
            Root<NetworkAccountDBO> rootNA = queryNA
                    .from(NetworkAccountDBO.class);
            queryNA.where(
                    criteriaBuilder.equal(
                    rootNA.get(NetworkAccountDBO_.networkName), networkName),
                    criteriaBuilder.equal(
                    rootNA.get(NetworkAccountDBO_.oauthAccessToken), oauthAccesToken));
            TypedQuery<NetworkAccountDBO> typedNAQuery = entityManager
                    .createQuery(queryNA).setMaxResults(1);
            try {
                retval = typedNAQuery.getSingleResult();
                if (detach) {
                    entityManager.detach(retval);
                    retval.setDetached(true);
                }
            } catch (NoResultException e) {
                retval = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retval;
    }
}
