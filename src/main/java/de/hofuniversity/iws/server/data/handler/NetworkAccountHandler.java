package de.hofuniversity.iws.server.data.handler;

import javax.persistence.EntityManager;

import de.hofuniversity.iws.server.data.entities.NetworkAccount;
import de.hofuniversity.iws.server.data.entities.NetworkAccount_;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class NetworkAccountHandler {

    private static EntityManager entityManager = HibernateUtil
            .getEntityManagerFactory().createEntityManager();

    // Store network account
    public static NetworkAccount store(NetworkAccount networkAccount) {
        NetworkAccount retval = networkAccount;

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
                NetworkAccount tmpNetworkAccount = entityManager.find(NetworkAccount.class,
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
    public static NetworkAccount getNetworkAccountEntity(long id, boolean detach) {
        return (NetworkAccount) GenericHandler.getEntity(NetworkAccount.class, id, detach);
    }
    
    public static NetworkAccount getNetworkAccountEntity(String networkName, String accountIdentificationString, boolean detach) {
        NetworkAccount retval = null;

        try {
            if (entityManager.isOpen()) {
                entityManager.close();
            }
            entityManager = HibernateUtil.getEntityManagerFactory()
                    .createEntityManager();

            CriteriaBuilder criteriaBuilder = entityManager
                    .getCriteriaBuilder();
            CriteriaQuery<NetworkAccount> queryPhraseEntity = criteriaBuilder
                    .createQuery(NetworkAccount.class);
            Root<NetworkAccount> rootPhraseEntity = queryPhraseEntity
                    .from(NetworkAccount.class);
            queryPhraseEntity.where(
                    criteriaBuilder.equal(
                    rootPhraseEntity.get(NetworkAccount_.networkName), networkName),
                    criteriaBuilder.equal(
                    rootPhraseEntity.get(NetworkAccount_.accountIdentificationString), accountIdentificationString));
            TypedQuery<NetworkAccount> typedPhraseEntityQuery = entityManager
                    .createQuery(queryPhraseEntity).setMaxResults(1);
            try {
                retval = typedPhraseEntityQuery.getSingleResult();
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
