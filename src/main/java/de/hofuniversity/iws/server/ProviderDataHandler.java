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
    
    private String parseJSON (User user,String responceBody,String Provider) 
    {
         String id = null; 
        // System.out.println(response.getBody());
         try {
            json = JSONUtility.convert_string_to_json(responceBody);
        } catch (JSONException ex) {
            Logger.getLogger(ProviderDataHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        /* Ermittlung des Benutzernamens */   
        try {           
            user.setUserName(json.getString("screen_name"));
        } catch (JSONException ex) {
            Logger.getLogger(ProviderDataHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
         try {           
            user.setUserName(json.getString("displayName"));
        } catch (JSONException ex) {
            Logger.getLogger(ProviderDataHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {           
            user.setUserName(json.getString("name"));
        } catch (JSONException ex) {
            Logger.getLogger(ProviderDataHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            JSONObject name = json.getJSONObject("name");
            user.setLastName(name.getString("familyName"));
        } catch (JSONException ex)
        {
             Logger.getLogger(ProviderDataHandler.class.getName()).log(Level.SEVERE, null, ex);           
        }
        try {           
            user.setFirstName(json.getString("givenName"));
        } catch (JSONException ex) {
            Logger.getLogger(ProviderDataHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            JSONObject name = json.getJSONObject("name");
            user.setFirstName(name.getString("givenName"));
        } catch (JSONException ex)
        {
             Logger.getLogger(ProviderDataHandler.class.getName()).log(Level.SEVERE, null, ex);           
        }
        try {           
            user.setLastName(json.getString("familyName"));
        } catch (JSONException ex) {
            Logger.getLogger(ProviderDataHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {           
            user.setFirstName(json.getString("first_name"));
        } catch (JSONException ex) {
            Logger.getLogger(ProviderDataHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {           
            user.setLastName(json.getString("last_name"));
        } catch (JSONException ex) {
            Logger.getLogger(ProviderDataHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        /* Ende Benutzername */
        /* Standort bzw. Wohnort ermitteln */
        try {           
            user.setCity(json.getString("location"));
        } catch (JSONException ex) {
            Logger.getLogger(ProviderDataHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            JSONObject hometown = json.getJSONObject("hometown");
            user.setCity(hometown.getString("name"));
        } catch (JSONException ex)
        {
             Logger.getLogger(ProviderDataHandler.class.getName()).log(Level.SEVERE, null, ex);           
        }
        /* Ende Wohnort, Standort */
        /* Benutzer-ID beim Provider ermitteln */
        try {
            id = json.getString("id");
        } catch (JSONException ex) {
            Logger.getLogger(ProviderDataHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        /* Möglichkeiten das Profilbild zu erhalten */
        try {
            user.setUserPic(json.getString("profile_image_url"));
        } catch (JSONException ex) {
            Logger.getLogger(ProviderDataHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(Provider.equals("Facebook"))
        {
            user.setUserPic("https://graph.facebook.com/"+id+"/picture&type=normal");
        }
        try {
            JSONObject name = json.getJSONObject("image");
            user.setUserPic(name.getString("url"));
        } catch (JSONException ex)
        {
             Logger.getLogger(ProviderDataHandler.class.getName()).log(Level.SEVERE, null, ex);           
        }
        /* Ende Profilbild */
        return id;
    }
    
    public Object[] get_UserData(Token accessToken,String Provider){
        
        String PROTECTED_RECSOURCE_URL_Google = "https://www.googleapis.com/plus/v1/people/me";
        String PROTECTED_RECSOURCE_URL_Twitter = "https://api.twitter.com/1/account/verify_credentials.json";
        String PROTECTED_RECSOURCE_URL_Facebook = "https://graph.facebook.com/me";
                  
        if(Provider.equals("Google"))
        {
            request = new OAuthRequest(Verb.GET, PROTECTED_RECSOURCE_URL_Google);
        } else if(Provider.equals("Twitter"))
        {
            request = new OAuthRequest(Verb.GET, PROTECTED_RECSOURCE_URL_Twitter);
        } else if(Provider.equals("Facebook"))
        {
            request = new OAuthRequest(Verb.GET, PROTECTED_RECSOURCE_URL_Facebook
                                       +"?access_token="+accessToken.getToken());          
        }
        
        service.signRequest(accessToken,request);
        response = request.send();
        tmpUser = new User();
        String id = parseJSON(tmpUser,response.getBody(),Provider);
        
 /*      // System.out.println(response.getBody());
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
        }*/
            return new Object[] {tmpUser, id};
    }
 /*   public Object[] get_FacebookUserData(Token accessToken){
        
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
    }*/
}
