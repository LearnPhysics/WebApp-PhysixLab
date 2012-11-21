
package de.hofuniversity.iws.server;

import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Andreas Arndt <andreas.arndt@hof-university.de>
 */
public class OAuthLogin {
    
    private String Twitter_apiKey = "Yzcw300dkMxJwfHtVlQ";
    private String Twitter_apiSecret = "UZKldYhGxPNrIpxGTAv0qCZLMIL4tnl2BrKBU6F0bLY";
    
    private String Google_apiKey = "311073576402-pq7e4l5ropak4fk7oniu44mk4a9047f5.apps.googleusercontent.com";
    private String Google_apiSecret = "OpfV0o26Sqr3A8UWDsKhE7og";
    private static final String Google_SCOPE = "https://www.googleapis.com/auth/plus.me";
    
    private String Facebook_apiKey = "370534136372045";
    private String Facebook_apiSecret = "84578d9af4ea152df5cd74e4cf086885";
    
    private String AUTHORIZE_URL = null;
    
    /**
     *
     * @param body
     * @return
     * @throws JSONException
     */
    public JSONObject convert_string_to_json(String body) throws JSONException {

	  		JSONObject jsonObj = new JSONObject(body);
			return jsonObj;
    }  
    
    public String Login(String NetworkName)
    {
        return AUTHORIZE_URL;
    }
    public String get_AUTHORIZE_URL()
    {
        return AUTHORIZE_URL;
    }
}
