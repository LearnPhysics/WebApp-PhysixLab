/*
 * Copyright (C) 2012 Andreas Arndt
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
package de.hofuniversity.iws.server.oauth.accessors;

import de.hofuniversity.iws.server.data.entities.UserDBO;
import org.scribe.model.Token;

/**
 * Accessor type for data related to friends of users
 * @author Andreas Arndt <andreas.arndt@hof-university.de>
 */
public interface FriendListAccessor extends Accessor{

    /**
     * Search for friends of a specified user.
     * @param accessToken
     * @param currentUser
     * user for which friends should be searched
     * @return
     * @throws AccessException 
     */
    public Iterable<UserDBO> getFriends(Token accessToken, UserDBO currentUser) throws AccessException;
}
