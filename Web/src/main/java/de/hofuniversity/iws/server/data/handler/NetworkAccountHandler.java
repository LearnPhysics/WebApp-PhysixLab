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

import de.hofuniversity.iws.server.data.entities.*;
import de.hofuniversity.iws.server.oauth.Providers;
import javax.persistence.*;
import javax.persistence.criteria.*;
import org.scribe.model.Token;

/**
 * Handler class encapsulating the database access to NetworkAccount entities
 * Queries are coded with the JPA Criteria API
 * @author Oliver Schütz
 */
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

    public static NetworkAccountDBO getNetworkAccountEntity(Providers networkName, String accountIdentificationString, boolean detach) {
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
                    rootNA.get(NetworkAccountDBO_.networkName), networkName.name()),
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
    
    public static NetworkAccountDBO getNetworkAccountEntityByAccessToken(Providers networkName, Token oauthToken, boolean detach) {
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
                    rootNA.get(NetworkAccountDBO_.networkName), networkName.name()),
                    criteriaBuilder.equal(
                    rootNA.get(NetworkAccountDBO_.oauthAccessToken), oauthToken.getToken()),
                    criteriaBuilder.equal(
                    rootNA.get(NetworkAccountDBO_.oauthAccessSecret), oauthToken.getSecret()));
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
