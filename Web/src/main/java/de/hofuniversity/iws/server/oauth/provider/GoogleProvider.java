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
import org.scribe.builder.api.GoogleApi;

/**
 *
 * @author Andreas Arndt <andreas.arndt@hof-university.de>
 */
public class GoogleProvider extends OAuthProvider {
    
    private static final String Google_SCOPE = OAuthProperties.APP.getPropertie("GOOGLE.Scope");
    
    public GoogleProvider(String apiKey, String apiSecret) {
        super(apiKey, apiSecret, GoogleApi.class);
    }
    
    @Override
    protected ServiceBuilder custom(ServiceBuilder builder) {
        return builder.scope(Google_SCOPE);
    }
}
