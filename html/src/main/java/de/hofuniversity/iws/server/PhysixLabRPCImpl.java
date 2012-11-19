/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;


/**
 *
 * @author User
 */
public class PhysixLabRPCImpl extends RemoteServiceServlet implements PhysixLabRPC {

    @Override
    public String SocialNetworkLogin(String s)
    {
        return "Auth-Key";
    }
}
