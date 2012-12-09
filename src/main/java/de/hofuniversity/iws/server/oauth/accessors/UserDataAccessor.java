/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.server.oauth.accessors;


import de.hofuniversity.iws.server.data.entities.User;

import org.scribe.model.Token;

/**
 *
 * @author Andreas Arndt <andreas.arndt@hof-university.de>
 */
public interface UserDataAccessor extends Accessor{

    public User getUserData(Token accessToken) throws AccessException;
}
