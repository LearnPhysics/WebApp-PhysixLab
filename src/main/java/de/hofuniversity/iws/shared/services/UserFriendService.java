/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.shared.services;

import de.hofuniversity.iws.shared.entitys.User;

import com.google.gwt.user.client.rpc.*;

/**
 *
 * @author User
 */
@RemoteServiceRelativePath("userfriend")
public interface UserFriendService extends RemoteService {

    public Iterable<User> getFriends();
}
