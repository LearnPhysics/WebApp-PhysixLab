/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.shared.services;

import com.google.common.base.Optional;
import com.google.gwt.user.client.rpc.*;
import de.hofuniversity.iws.shared.dto.SessionDTO;
import de.hofuniversity.iws.shared.services.login.*;

/**
 *
 * @author User
 */
@RemoteServiceRelativePath("login")
public interface LoginService extends RemoteService {  
    /**
     * Requests if the provided session ID is valid
     * @param sessionId
     * @return 
     * returns a Session object if the ID is valid, otherwise returns Absent Optional
     */
    public boolean isSessionValid(String sessionId);
    
    /**
     * Method to wait for an OAuth verification after the client opend a login popup of the OAuth Provider
     * @return
     * A valid session ID
     * @throws OAuthException 
     */
    public SessionDTO waitForOAuthSessionID() throws OAuthException;
    
    /**
     * Get a verification URL of the named OAuth provider
     * @param provider
     * @return 
     */
    public String getOAuthLoginUrl(LoginProvider provider);
    
    /**
     * invalidats the active session
     */
    public void logout();
}
