/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client.services;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 *
 * @author User
 */
@RemoteServiceRelativePath("login")
public interface LoginService extends RemoteService {
    
    public String SocialNetworkLogin(String s);
}
