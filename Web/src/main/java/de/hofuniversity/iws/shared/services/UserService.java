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
public interface UserService extends RemoteService {

    public Iterable<? extends User> getFriends();
    
    public void addGameResult(String game, int points);
}
