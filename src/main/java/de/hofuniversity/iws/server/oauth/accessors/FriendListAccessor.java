/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.server.oauth.accessors;

import de.hofuniversity.iws.server.data.entities.UserDBO;

import org.scribe.model.Token;

/**
 *
 * @author Daniel Heinrich <dannynullzwo@gmail.com>
 */
public interface FriendListAccessor extends Accessor{

    public Iterable<UserDBO> getFriends(Token accessToken, UserDBO currentUser) throws AccessException;
}
