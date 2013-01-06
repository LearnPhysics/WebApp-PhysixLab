/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.server.oauth.provider;

import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.GoogleApi;

/**
 *
 * @author Daniel Heinrich <dannynullzwo@gmail.com>
 */
public class GoogleProvider extends OAuthProvider {

    private static final String Google_SCOPE = "https://www.googleapis.com/auth/plus.me";

    public GoogleProvider(String apiKey, String apiSecret) {
        super(apiKey, apiSecret, GoogleApi.class);
    }

    @Override
    protected ServiceBuilder custom(ServiceBuilder builder) {
        return builder.scope(Google_SCOPE);
    }
}
