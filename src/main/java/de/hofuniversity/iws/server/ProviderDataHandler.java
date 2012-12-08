/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.server;

import de.hofuniversity.iws.server.data.entities.User;
import de.hofuniversity.iws.server.oauth.Providers;
import java.sql.Array;
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
public class ProviderDataHandler {
    
    private OAuthService service = null;
    private OAuthRequest request = null;
    private Response response = null;
    
    JSONObject json = null;
    User tmpUser = null;
   
    public ProviderDataHandler(OAuthService s)
    {
        this.service = s;
    } 
    
    private String parseJSON (User user,String responceBody,String Provider)  
    {
        String id = null; 
       //     "location","profile_image_url","id"};
        String[] name={"screen_name","displayName"};
        String[] first_name={"givenName","first_name"};
        String[] last_name={"familyName","last_name"};
        
        try {
            // System.out.println(response.getBody());
                json = new JSONObject(responceBody);
        } catch (JSONException ex) {
            Logger.getLogger(ProviderDataHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(json.has("name"))
        {
              user.setUserName(json.optString("name"));                        
        }
        
        for (String item: name) {
            if(json.has(item)){
                    user.setUserName(json.optString(item));
            }
        }
        for (String item: first_name) {
            if(json.has(item)){
                    user.setFirstName(json.optString(item));
            }
        }
        for (String item: last_name) {
            if(json.has(item)){
                    user.setLastName(json.optString(item));
            }
        }           
        JSONObject tmp = json.optJSONObject("hometown");
        if(tmp != null)
        {
            if(tmp.has("name"))
            {       
                    user.setCity(tmp.optString("name"));
            }
        }  
        tmp = json.optJSONObject("image");
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
            for (String item: last_name) {
                if(tmp.has(item)){
                    user.setLastName(tmp.optString(item));
                }
            } 
            for (String item: first_name) {
                if(tmp.has(item)){
                    user.setFirstName(tmp.optString(item));
                }
            }            
        }
        
        if(json.has("id"))
        {
           id = json.optString("id"); 
        }
        
        if(json.has("location"))
        {
           user.setCity(json.optString("location")); 
        }
        
        if(json.has("profile_image_url"))
        {
           user.setUserPic(json.optString("profile_image_url")); 
        }

        
        if(Providers.FACEBOOK.name().equals(Provider))
        {
            user.setUserPic("https://graph.facebook.com/"+id+"/picture&type=normal");
        }
        return id;
    }
    
 
    
    public Object[] get_UserData(Token accessToken,String Provider){
     //   String s = Providers.getProp("GOOGLE_USER_URL");
        
        String PROTECTED_RECSOURCE_URL_Google = "https://www.googleapis.com/plus/v1/people/me";
        String PROTECTED_RECSOURCE_URL_Twitter = "https://api.twitter.com/1/account/verify_credentials.json";
        String PROTECTED_RECSOURCE_URL_Facebook = "https://graph.facebook.com/me";
                  
        if(Providers.GOOGLE.name().equals(Provider))
        {
            request = new OAuthRequest(Verb.GET, PROTECTED_RECSOURCE_URL_Google);
        } else if(Providers.TWITTER.name().equals(Provider))
        {
            request = new OAuthRequest(Verb.GET, PROTECTED_RECSOURCE_URL_Twitter);
        } else if(Providers.FACEBOOK.name().equals(Provider))
        {
            request = new OAuthRequest(Verb.GET, PROTECTED_RECSOURCE_URL_Facebook
                                       +"?access_token="+accessToken.getToken());          
        }
        
        service.signRequest(accessToken,request);
        response = request.send();
        tmpUser = new User();
        String id = parseJSON(tmpUser,response.getBody(),Provider);
        return new Object[] {tmpUser,id};
   //     return new Object[] {tmpUser, id};
    }

}
