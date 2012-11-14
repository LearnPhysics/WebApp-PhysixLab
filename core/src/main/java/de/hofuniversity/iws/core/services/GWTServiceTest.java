/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.core.services;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 *
 * @author Daniel Heinrich <dannynullzwo@gmail.com>
 */
@RemoteServiceRelativePath("gwtservicetest")
public interface GWTServiceTest extends RemoteService {

    public RPCVector randomVector();
}
