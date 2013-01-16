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
package de.hofuniversity.iws.server.oauth;

import org.scribe.model.*;
import org.scribe.oauth.OAuthService;

/**
 *
 * @author Andreas Arndt <andreas.arndt@hof-university.de>
 */
public class OAuthAccessRequest {

    private final Token requestToken;
    private final OAuthService service;
    private final String authorization;

    public OAuthAccessRequest(Token requestToken, OAuthService service, String authorizeUrl) {
        this.requestToken = requestToken;
        this.service = service;
        this.authorization = authorizeUrl;
    }

    public String getAuthorizeUrl() {
        return authorization;
    }

    public Token generateAccessToken(Verifier verifier) {
        return service.getAccessToken(requestToken, verifier);
    }

    public OAuthService getService() {
        return service;
    }
}
