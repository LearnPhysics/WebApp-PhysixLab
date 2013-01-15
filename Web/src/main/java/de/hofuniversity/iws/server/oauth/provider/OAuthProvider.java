/*
 * Copyright (C) 2012 Andreas Arndt
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package de.hofuniversity.iws.server.oauth.provider;

import de.hofuniversity.iws.server.oauth.*;
import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.Api;
import org.scribe.model.Token;
import org.scribe.oauth.OAuthService;

/**
 *
 * @author Andreas Arndt <andreas.arndt@hof-university.de>
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
