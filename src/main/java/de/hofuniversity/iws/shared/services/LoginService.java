/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.shared.services;

import com.google.common.base.Optional;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import de.hofuniversity.iws.server.data.entities.User;
import de.hofuniversity.iws.shared.services.login.LoginException;
import org.scribe.exceptions.OAuthException;

/**
 *
 * @author User
 */
@RemoteServiceRelativePath("login")
public interface LoginService extends RemoteService {  
    
    /**
     * returns the current valid session if present
     * @return
     */
    public Optional<String> getSessionToken();
    
    /**
     * Method to wait for an OAuth verification after the client opend a login popup of the OAuth Provider
     * @return
     * A valid session ID
     * @throws OAuthException 
     */
    public String waitForOAuthVerification() throws LoginException;
    
    /**
     * Get a verification URL of the named OAuth provider
     * @param provider
     * @return 
     */
    public String getOAuthLoginUrl(String provider);
    
    /**
     * invalidates the active session
     */
    public void logout();
    /*
     * Get the actual User from this Session
     */
    public User getUser();
}
