/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.server;

import de.hofuniversity.iws.server.data.entities.User;
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
    
    public Object[] get_GoogleUserData(Token accessToken){
        
        String PROTECTED_RECSOURCE_URL = "https://www.googleapis.com/plus/v1/people/me";
        String id = null;
        request = new OAuthRequest(Verb.GET, PROTECTED_RECSOURCE_URL);
        service.signRequest(accessToken,request);
        response = request.send();
        tmpUser = new User();
        
       // System.out.println(response.getBody());
         try {
            json = JSONUtility.convert_string_to_json(response.getBody());
        } catch (JSONException ex) {
            Logger.getLogger(ProviderDataHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {           
            tmpUser.setUserName(json.getString("name"));
            id = json.getString("id");
            tmpUser.setUserPic(json.getString("profile_image_url"));
        } catch (JSONException ex) {
            Logger.getLogger(ProviderDataHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
            return new Object[] {tmpUser, id};
    }
    public Object[] get_FacebookUserData(Token accessToken){
        
        String PROTECTED_RECSOURCE_URL = "https://graph.facebook.com/me";
        String id = null;
        request = new OAuthRequest(Verb.GET, PROTECTED_RECSOURCE_URL
                                                +"?access_token="+accessToken.getToken());
        service.signRequest(accessToken,request);
        response = request.send();
        tmpUser = new User();
        
        // System.out.println(response.getBody());
        try {
            json = JSONUtility.convert_string_to_json(response.getBody());
        } catch (JSONException ex) {
            Logger.getLogger(ProviderDataHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        try { 
            tmpUser.setUserName(json.getString("name"));
            id = json.getString("id");
            tmpUser.setUserPic("https://graph.facebook.com/"+json.get("id")+"/picture&type=normal");
        } catch (JSONException ex) {
            Logger.getLogger(ProviderDataHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Object[] {tmpUser, id};
    }
    public Object[] get_TwitterUserData(Token accessToken) {
        
        String PROTECTED_RECSOURCE_URL = "https://api.twitter.com/1/account/verify_credentials.json";
        String id = null;
      //  String PROTECTED_RECSOURCE_URL = "http://api.twitter.com/1.1/users/show.json";
        request = new OAuthRequest(Verb.GET, PROTECTED_RECSOURCE_URL);
        service.signRequest(accessToken,request);
        response = request.send();
        tmpUser = new User();
        
        // System.out.println(response.getBody());
        try {
            json = JSONUtility.convert_string_to_json(response.getBody());
        } catch (JSONException ex) {
            Logger.getLogger(ProviderDataHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
                    tmpUser = new User();
        try {         
            tmpUser.setUserName(json.getString("name"));
            id = json.getString("id");
            tmpUser.setUserPic(json.getString("profile_image_url"));
        } catch (JSONException ex) {
            Logger.getLogger(ProviderDataHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Object[] {tmpUser, id};
    }
}
