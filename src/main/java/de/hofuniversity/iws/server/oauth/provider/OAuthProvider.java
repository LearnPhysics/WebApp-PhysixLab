/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.server.oauth.provider;

import de.hofuniversity.iws.server.oauth.OAuthRequest;
import de.hofuniversity.iws.server.oauth.Providers;
import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.Api;
import org.scribe.model.Token;
import org.scribe.oauth.OAuthService;

/**
 *
 * @author Daniel Heinrich <dannynullzwo@gmail.com>
 */
public class OAuthProvider {

    private final String apiKey, apiSecret;
    private final Class<? extends Api> providerClass;
    private static final String CALLBACK_URL = Providers.getProp("CALLBACK_URL");

    public OAuthProvider(String apiKey, String apiSecret, Class<? extends Api> providerClass) {
        this.apiKey = apiKey;
        this.apiSecret = apiSecret;
        this.providerClass = providerClass;
    }

    public OAuthRequest createRequest() {
        OAuthService service = custom(new ServiceBuilder())
                .provider(providerClass)
                .apiKey(apiKey)
                .apiSecret(apiSecret)
                .callback(CALLBACK_URL)
                .build();

        Token request = createRequestToken(service);
        String url = createAuthorizationUrl(service, request);

        return new OAuthRequest(request, service, url);
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
