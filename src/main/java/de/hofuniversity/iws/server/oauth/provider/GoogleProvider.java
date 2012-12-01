/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.server.oauth.provider;

import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.GoogleApi;
import org.scribe.model.Token;
import org.scribe.oauth.OAuthService;

/**
 *
 * @author Daniel Heinrich <dannynullzwo@gmail.com>
 */
public class GoogleProvider extends OAuthProvider {

    private static final String Google_Authorize_URL = "https://www.google.com/accounts/OAuthAuthorizeToken?oauth_token=";
    private static final String Google_SCOPE = "https://www.googleapis.com/auth/plus.me";

    public GoogleProvider(String apiKey, String apiSecret) {
        super(apiKey, apiSecret, GoogleApi.class);
    }

    @Override
    protected String createAuthorizationUrl(OAuthService service, Token request) {
        return Google_Authorize_URL + super.createAuthorizationUrl(service, request);
    }

    @Override
    protected ServiceBuilder custom(ServiceBuilder builder) {
        return builder.scope(Google_SCOPE);
    }    
}
