/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.server.oauth.provider;

import de.hofuniversity.iws.server.ProviderDataHandler;
import de.hofuniversity.iws.server.data.entities.User;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;

/**
 *
 * @author Andreas Arndt <andreas.arndt@hof-university.de>
 */
public class GoogleProviderUserData implements IProviderData {
    
    private OAuthService service = null;
    JSONObject json = null;
        
    public GoogleProviderUserData(OAuthService s)
    {
        this.service = s;
    } 
    
    @Override
    public User get_UserData(Token accessToken) {
       String PROTECTED_RECSOURCE_URL =  ProviderDataFactory.getProp("GOOGLE_USER_URL");
       
       OAuthRequest request = new OAuthRequest(Verb.GET, PROTECTED_RECSOURCE_URL);
       
       service.signRequest(accessToken,request);
       Response response = request.send();
       User  tmpUser = new User();
        parseUserJSON(tmpUser,response.getBody());
        return tmpUser;
    }

    @Override
    public void parseUserJSON(User user, String responceBody) {
        try {
                json = new JSONObject(responceBody);
        } catch (JSONException ex) {
            Logger.getLogger(ProviderDataHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String id = null;
        if(json.has("id"))
        {
           id = json.optString("id"); 
        }
        
        if(json.has("displayName"))
        {
           user.setUserName(json.optString("displayName")); 
        }
        
        JSONObject tmp = json.optJSONObject("image");
        if(tmp != null)
        {
            if(tmp.has("url"))
            {
                    user.setUserPic(tmp.optString("url")); 
            }
        }
        tmp = json.optJSONObject("name");
        if(tmp != null)
        {
            if(tmp.has("familyName"))
            {
                    user.setLastName(tmp.optString("familyName")); 
            }
            if(tmp.has("givenName"))
            {
                    user.setFirstName(tmp.optString("givenName"));
            }
        } 
    }

    @Override
    public void parseFriendsJSON(List<User> friends, String response) {
        String ex = "read Google-Circle is not supported by Google+ REST-API";
        Logger.getLogger(ProviderDataHandler.class.getName()).log(Level.SEVERE, null, ex);
        System.out.println(ex);
    }

    @Override
    public List<User> get_Friends(Token accessToken,User currentUser)
    {
        String ex = "read Google-Circle is not supported by Google+ REST-API";
        Logger.getLogger(ProviderDataHandler.class.getName()).log(Level.SEVERE, null, ex);
        System.out.println(ex);
        return null;
    }
}
