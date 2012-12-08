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
public class FacebookProviderUserData implements IProviderData {  
    
    private OAuthService service = null;
    JSONObject json = null;
        
    public FacebookProviderUserData(OAuthService s)
    {
        this.service = s;
    } 

    @Override
    public User get_UserData(Token accessToken) {
       String PROTECTED_RECSOURCE_URL =  ProviderDataFactory.getProp("FACEBOOK_USER_URL");
       
       OAuthRequest request = new OAuthRequest(Verb.GET, PROTECTED_RECSOURCE_URL
                                       +"?access_token="+accessToken.getToken());
       service.signRequest(accessToken,request);
       Response response = request.send();
       User  tmpUser = new User();
        parseUserJSON(tmpUser,response.getBody());
       return tmpUser;
    }

    
    @Override
    public void parseUserJSON(User user, String responceBody) {
        try {
            // System.out.println(response.getBody());
                json = new JSONObject(responceBody);
        } catch (JSONException ex) {
            Logger.getLogger(ProviderDataHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        String id = null;
        if(json.has("id"))
        {
           user.setAccountIdentificationString(json.optString("id")); 
        }
        
        if(json.has("name"))
        {
            user.setUserName(json.optString("name"));
        }
        
        if(json.has("last_name"))
        {
            user.setLastName(json.optString("last_name"));
        }
        
        if(json.has("first_name"))
        {
            user.setFirstName(json.optString("first_name"));
        }
        
        JSONObject tmp = json.optJSONObject("hometown");
        if(tmp != null)
        {
            if(tmp.has("name"))
            {       
                    user.setCity(tmp.optString("name"));
            }
        }              
        user.setUserPic("https://graph.facebook.com/"+id+"/picture&type=normal");    
    }
    @Override
    public void parseFriendsJSON(List<User> friends,String response)
    {
        try {
                json = new JSONObject(response);
        } catch (JSONException ex) {
            Logger.getLogger(ProviderDataHandler.class.getName()).log(Level.SEVERE, null, ex);
        }  
                 JSONArray getArray = json.optJSONArray("data");
                    for(int i = 0; i < getArray.length(); i++)
                    {
                     User tmpUser = new User();
                     JSONObject objectInArray = getArray.optJSONObject(i);
                           tmpUser.setUserName(objectInArray.optString("name"));
                           tmpUser.setAccountIdentificationString(objectInArray.optString("id"));
                           tmpUser.setUserPic("https://graph.facebook.com/"+objectInArray.optString("id")+"/picture&type=normal");
                           friends.add(tmpUser);
                    }
        String s = null;
    }
    @Override
    public List<User> get_Friends(Token accessToken,User currentUser)
    {
       List<User> friendsList = new  ArrayList<User>();
       String PROTECTED_RECSOURCE_URL =  ProviderDataFactory.getProp("FACEBOOK_FRIENDS_URL");
       
       OAuthRequest request = new OAuthRequest(Verb.GET, PROTECTED_RECSOURCE_URL
                                       +"?access_token="+accessToken.getToken());
       service.signRequest(accessToken,request);
       Response response = request.send();
       parseFriendsJSON(friendsList,response.getBody());
        return friendsList;
    }    
}
