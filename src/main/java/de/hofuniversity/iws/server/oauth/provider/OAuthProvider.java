/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.server.oauth.provider;

import de.hofuniversity.iws.server.oauth.*;
import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.Api;
import org.scribe.model.Token;
import org.scribe.oauth.OAuthService;

/**
 *
 * @author Daniel Heinrich <dannynullzwo@gmail.com>
 */
public class OAuthProvider {
    private static final String CALLBACK_URL = OAuthProperties.APP.getPropertie("CALLBACK_URL");
    private final OAuthService service;

    public OAuthProvider(String apiKey, String apiSecret, Class<? extends Api> providerClass) {
        service = custom(new ServiceBuilder())
                .provider(providerClass)
                .apiKey(apiKey)
                .apiSecret(apiSecret)
                .callback(CALLBACK_URL)
                .build();
    }

    public OAuthAccessRequest createRequest() {
        Token request = createRequestToken(service);
        String url = createAuthorizationUrl(service, request);

        return new OAuthAccessRequest(request, service, url);
    }

    public OAuthService getService() {
        return service;
    }

    protected ServiceBuilder custom(ServiceBuilder builder) {
        return builder;
    }

    protected Token createRequestToken(OAuthService service) {
        Token request = service.getRequestToken();
        return request;
    }

    protected String createAuthorizationUrl(OAuthService service, Token request) {
        String url = service.getAuthorizationUrl(request);
        return url;
    }
}
