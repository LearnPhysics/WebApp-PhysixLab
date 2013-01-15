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
package de.hofuniversity.iws.shared.services;

import com.google.common.base.Optional;
import com.google.gwt.user.client.rpc.*;
import de.hofuniversity.iws.shared.dto.LoginDTO;
import org.scribe.exceptions.OAuthException;

/**
 *
 * @author Andreas Arndt <andreas.arndt@hof-university.de>
 */
@RemoteServiceRelativePath("login")
public interface LoginService extends RemoteService {  
    
    /**
     * returns the current valid session if present
     * @return
     */
    public Optional<LoginDTO> getLoginData();
    
    /**
     * Method to wait for an OAuth verification after the client opend a login popup of the OAuth Provider
     * @return
     * A valid session ID
     * @throws OAuthException 
     */
    public LoginDTO waitForOAuthVerification() throws LoginException;
    
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
}
