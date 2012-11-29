/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.server;

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
    
    public ProviderDataHandler(OAuthService s)
    {
        this.service = s;
    }
    
    public JSONObject get_GoogleUser(Token accessToken){
        
        String PROTECTED_RECSOURCE_URL = "https://www.google.com/plus/v1/pepople/me";
        request = new OAuthRequest(Verb.GET, PROTECTED_RECSOURCE_URL);
        service.signRequest(accessToken,request);
        response = request.send();
        System.out.println(response.getBody());
        return null;
    }
    public JSONObject get_FacebookUser(Token accessToken){
        
        String PROTECTED_RECSOURCE_URL = "https://graph.facebook.com/me";
        request = new OAuthRequest(Verb.GET, PROTECTED_RECSOURCE_URL
                                                +"?access_token="+accessToken.getToken());
        service.signRequest(accessToken,request);
        response = request.send();
                System.out.println(response.getBody());
        return null;
    }
        public JSONObject get_TwitterUser(Token accessToken){
        
        String PROTECTED_RECSOURCE_URL = "http://api.twitter.com/1.1/users/show.json";
        request = new OAuthRequest(Verb.GET, PROTECTED_RECSOURCE_URL);
        service.signRequest(accessToken,request);
        response = request.send();
        System.out.println(response.getBody());
        return null;
    }
}
