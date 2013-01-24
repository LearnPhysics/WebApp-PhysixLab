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

import javax.persistence.*;

/**
 * Encapsulates the EntityManagerFactory
 * @author Oliver Schütz
 */
// entityManager.detach(entity);
// later
// entityManager.merge(entity);
public class HibernateUtil {

    private static final EntityManagerFactory emf = init();

    private static EntityManagerFactory init() {
        try {
            return Persistence.createEntityManagerFactory("physixLab");
        } catch (Throwable e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        return emf;
    }

    public static boolean isConnectedToDB() {
        boolean successfully = false;

        try {
            EntityManager entityManager = getEntityManagerFactory()
                    .createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.getTransaction().commit();
            entityManager.close();
            successfully = true;
        } catch (Exception e) {
            System.err.println(e);
        }
        return successfully;
    }
}