/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 *
 * @author Daniel Heinrich <dannynullzwo@gmail.com>
 */
public interface GWTServiceTestAsync {

    public void myMethod(String s, AsyncCallback<String> callback);
}
