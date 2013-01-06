/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.server.oauth;

import org.scribe.model.*;
import org.scribe.oauth.OAuthService;

/**
 *
 * @author Daniel Heinrich <dannynullzwo@gmail.com>
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
