/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.server;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import de.hofuniversity.iws.core.services.GWTServiceTest;
import de.hofuniversity.iws.core.services.RPCVector;

/**
 *
 * @author Daniel Heinrich <dannynullzwo@gmail.com>
 */
public class GWTServiceTestImpl extends RemoteServiceServlet implements GWTServiceTest {

    @Override
    public RPCVector randomVector() {
        Random rnd = ThreadLocalRandom.current();
        float x = rnd.nextFloat() * 10;
        float y = rnd.nextFloat() * 10;
        return new RPCVector(x, y);
    }
}
