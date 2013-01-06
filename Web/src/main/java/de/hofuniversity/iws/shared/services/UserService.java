/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.shared.services;

import com.google.gwt.user.client.rpc.*;
import de.hofuniversity.iws.client.jsonbeans.LessonJson;
import de.hofuniversity.iws.shared.entitys.User;

/**
 *
 * @author User
 */
@RemoteServiceRelativePath("user")
public interface UserService extends RemoteService {

    public Iterable<? extends User> getFriends();
    
    public void addGameResult(String game, int points);
    public void addTestResult(String lesson, String subject, int points);
}
