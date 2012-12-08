/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.server.oauth.provider;

import de.hofuniversity.iws.server.ProviderDataHandler;
import de.hofuniversity.iws.server.data.entities.User;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
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
public class TwitterProviderUserData implements IProviderData {
    
    private OAuthService service = null;
    JSONObject json = null;
        
    public TwitterProviderUserData(OAuthService s)
    {
        this.service = s;
    } 
    
    @Override
    public User get_UserData(Token accessToken) {
       String PROTECTED_RECSOURCE_URL =  ProviderDataFactory.getProp("TWITTER_USER_URL");
       
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
            
        if(json.has("id"))
        {
           user.setAccountIdentificationString(json.optString("id"));            
        }
                
        if(json.has("location"))
        {
           user.setCity(json.optString("location")); 
        }
        
        if(json.has("profile_image_url"))
        {
           user.setUserPic(json.optString("profile_image_url")); 
        }
        if(json.has("screen_name"))
        {
           user.setUserName(json.optString("screen_name")); 
        }
        if(json.has("name"))
        {
           user.setUserName(json.optString("name"));  
        }
    }
    
    @Override
    public List<User> get_Friends(Token accessToken,User currentUser) {
       List<User> friendsList = new  ArrayList<User>();
       
       String PROTECTED_RECSOURCE_URL =  ProviderDataFactory.getProp("TWITTER_FOLLOWERS_URL");
       
       OAuthRequest request = new OAuthRequest(Verb.GET, PROTECTED_RECSOURCE_URL+currentUser.getAccountIdentificationString());
       
       service.signRequest(accessToken,request);
       Response response = request.send();
        try {
            json = new JSONObject(response.getBody());
        } catch (JSONException ex) {
            Logger.getLogger(TwitterProviderUserData.class.getName()).log(Level.SEVERE, null, ex);
        }

                JSONArray getArray = json.optJSONArray("ids");
                PROTECTED_RECSOURCE_URL =  ProviderDataFactory.getProp("TWITTER_USER_BY_ID_URL");
                 for(int i=0;i< getArray.length();i++)
                 {
                     if(getArray.optString(i).toString()!=null)
                     {
                            User tmpUser = new User();   
                            String s= PROTECTED_RECSOURCE_URL+getArray.optString(i).toString();
                            request = new OAuthRequest(Verb.GET, s+"&include_entities=true");
                            service.signRequest(accessToken,request);
                            response = request.send();
                            parseUserJSON(tmpUser,response.getBody());     
                            friendsList.add(tmpUser);
                     }
                 }       
       return friendsList;
    }

    @Override
    public void parseFriendsJSON(List<User> friends, String response) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
