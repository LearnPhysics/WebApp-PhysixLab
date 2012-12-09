/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.server.oauth.provider;

import org.scribe.builder.api.FacebookApi;
import org.scribe.model.Token;
import org.scribe.oauth.OAuthService;

/**
 *
 * @author Daniel Heinrich <dannynullzwo@gmail.com>
 */
public class FacebookProvider extends OAuthProvider{

    public FacebookProvider(String apiKey, String apiSecret) {
        super(apiKey, apiSecret, FacebookApi.class);
    }

    @Override
    protected Token createRequestToken(OAuthService service) {
        return null;
    }    
}
