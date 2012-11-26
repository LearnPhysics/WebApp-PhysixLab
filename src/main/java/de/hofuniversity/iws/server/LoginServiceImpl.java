/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.server;

import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import de.hofuniversity.iws.client.services.LoginService;

/**
 *
 * @author User
 */
@RemoteServiceRelativePath("login")
public class LoginServiceImpl extends RemoteServiceServlet implements LoginService {

    private OAuthLogin oauth = null;
    
    @Override
    public String SocialNetworkLogin(String NetworkName) {
        oauth = new OAuthLogin();
        getThreadLocalRequest().getSession().setAttribute("obj_OAuthClass", oauth);        
        return oauth.Login(NetworkName);
    }
}
