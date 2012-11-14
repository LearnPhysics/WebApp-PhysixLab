/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.core.services;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 *
 * @author Daniel Heinrich <dannynullzwo@gmail.com>
 */
public interface GWTServiceTestAsync {
    public void randomVector(AsyncCallback<RPCVector> callback);
}
