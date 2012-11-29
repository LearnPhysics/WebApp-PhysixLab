
package de.hofuniversity.iws.server;

import org.json.JSONObject;
import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.FacebookApi;
import org.scribe.builder.api.GoogleApi;
import org.scribe.builder.api.TwitterApi;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;

/**
 *
 * @author Andreas Arndt <andreas.arndt@hof-university.de>
 */
public class OAuthLogin {
    
    private static final Token EMPTY_TOKEN = null;
    private String SOCIAL_NETWORK_NAME = null;
    
    private final String Twitter_apiKey = "Yzcw300dkMxJwfHtVlQ";
    private final String Twitter_apiSecret = "UZKldYhGxPNrIpxGTAv0qCZLMIL4tnl2BrKBU6F0bLY";
    
    private final String Google_apiKey = "311073576402-pq7e4l5ropak4fk7oniu44mk4a9047f5.apps.googleusercontent.com";
    private final String Google_apiSecret = "OpfV0o26Sqr3A8UWDsKhE7og";
    private final String Google_Authorize_URL = "https://www.google.com/accounts/OAuthAuthorizeToken?oauth_token=";
    private static final String Google_SCOPE = "https://www.googleapis.com/auth/plus.me";
    
    
    private final String Facebook_apiKey = "370534136372045";
    private final String Facebook_apiSecret = "84578d9af4ea152df5cd74e4cf086885";
    
    private String AUTHORIZE_URL = null;
    private Token  REQUEST_TOKEN = null;
    private Verifier OAUTH_VERIFIER = null;
    private Token ACCESS_TOKEN = null;
    private final String CALLBACK_URL = "http://127.0.0.1:8888/PhysixLab/oauth_callback";
    
    //private OAuthService service = null;
    private OAuthRequest request = null;
    private Response response = null;
    
    
    private OAuthService service = null;
    
    private String GoogleService()
    {
        service = new ServiceBuilder()
                        .provider(GoogleApi.class)
                        .apiKey(Google_apiKey)
                        .apiSecret(Google_apiSecret)
                        .scope(Google_SCOPE)
                        .callback(CALLBACK_URL)
                        .build();
        
        REQUEST_TOKEN = service.getRequestToken();
        
        AUTHORIZE_URL  = Google_Authorize_URL + REQUEST_TOKEN.getToken();
        
        return AUTHORIZE_URL;
    }
    
    private String TwitterService()
    {
        service = new ServiceBuilder()
                .provider(TwitterApi.class)
                .apiKey(Twitter_apiKey)
                .apiSecret(Twitter_apiSecret)
                .callback(CALLBACK_URL)
                .build();
         
        REQUEST_TOKEN = service.getRequestToken();
        
        AUTHORIZE_URL = service.getAuthorizationUrl(REQUEST_TOKEN);
                
        return AUTHORIZE_URL;
    }
    
    private String FacebookService()
    {
        service = new ServiceBuilder()
                .provider(FacebookApi.class)
                .apiKey(Facebook_apiKey)
                .apiSecret(Facebook_apiSecret)
                .callback(CALLBACK_URL)
                .build();
        
        AUTHORIZE_URL = service.getAuthorizationUrl(EMPTY_TOKEN);

        return AUTHORIZE_URL;
    }    
    
    public String Login(String NetworkName)
    {
        this.SOCIAL_NETWORK_NAME = NetworkName;
        this.AUTHORIZE_URL = null;
        if(NetworkName.equals("Google"))
        {
            GoogleService();
        }
        if(NetworkName.equals("Twitter"))
        {
            TwitterService();
        }
        if(NetworkName.equals("Facebook"))
        {
            FacebookService();
        }
        return AUTHORIZE_URL;
    }
    
    public String get_AUTHORIZE_URL()
    {
        return AUTHORIZE_URL;
    }

    public OAuthService get_OAUTH_SERVICE()
    {
        return service;
    }
    
    public Token get_REQUEST_TOKEN()
    {
        return REQUEST_TOKEN;
    }
    
    public void set_OAUTH_VERIFIER(String verifier)
    {
            OAUTH_VERIFIER = new Verifier(verifier);
            generate_accessToken();
    }

    public void generate_accessToken() {
       // throw new UnsupportedOperationException("Not yet implemented");
        if(!(OAUTH_VERIFIER==null))
        {
            if(SOCIAL_NETWORK_NAME.contains("Facebook"))
            {
                ACCESS_TOKEN = service.getAccessToken(EMPTY_TOKEN, OAUTH_VERIFIER);
            } else {
                ACCESS_TOKEN = service.getAccessToken(REQUEST_TOKEN, OAUTH_VERIFIER);
            }
        }
    }

    public Token get_accessToken()
    {
        return ACCESS_TOKEN;
    }
    
  /*  public JSONObject get_TwitterUser(){
        
        String PROTECTED_RECSOURCE_URL = "http://api.twitter.com/1.1/users/show.json";
        request = new OAuthRequest(Verb.GET, PROTECTED_RECSOURCE_URL);
        service.signRequest(ACCESS_TOKEN,request);
        response = request.send();
        System.out.println(response.getBody());
        return null;
    }*/
}
