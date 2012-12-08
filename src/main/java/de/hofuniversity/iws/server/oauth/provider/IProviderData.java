/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.server.oauth.provider;

import de.hofuniversity.iws.server.data.entities.User;
import de.hofuniversity.iws.server.oauth.Providers;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import org.scribe.model.Token;

/**
 *
 * @author Andreas Arndt <andreas.arndt@hof-university.de>
 */
public interface IProviderData {
   
    public User get_UserData(Token accessToken);
    public void parseUserJSON(User user, String responceBody);
    public List<User> get_Friends(Token accessToken,User currentUser);
    public void parseFriendsJSON(List<User> friends,String response);
 
}
