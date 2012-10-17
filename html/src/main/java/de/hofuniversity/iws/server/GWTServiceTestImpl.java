/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hofuniversity.iws.client.GWTServiceTest;

/**
 *
 * @author Daniel Heinrich <dannynullzwo@gmail.com>
 */
public class GWTServiceTestImpl extends RemoteServiceServlet implements GWTServiceTest {

    public String myMethod(String s) {
        // Do something interesting with 's' here on the server.
        return "Server says: " + s;
    }
}
