/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.shared.services;

import com.google.gwt.user.client.rpc.*;
import de.hofuniversity.iws.shared.entitys.User;

/**
 *
 * @author User
 */
@RemoteServiceRelativePath("userfriend")
public interface UserFriendService extends RemoteService {

    public Iterable<User> getFriends();
}
