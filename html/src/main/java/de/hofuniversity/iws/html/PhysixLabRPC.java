/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.html;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 *
 * @author User
 */
@RemoteServiceRelativePath("physixlabrpc")
public interface PhysixLabRPC extends RemoteService {
    
    public String SocialNetworkLogin(String s);
}
