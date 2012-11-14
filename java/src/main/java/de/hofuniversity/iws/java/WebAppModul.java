/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.java;

import java.net.CookieManager;
import java.net.CookiePolicy;

import com.gdevelop.gwt.syncrpc.SyncProxy;
import com.google.inject.AbstractModule;
import com.google.inject.Provider;
import de.hofuniversity.iws.core.services.GWTServiceTestAsync;
import javax.inject.Singleton;

/**
 *
 * @author Daniel Heinrich <dannynullzwo@gmail.com>
 */
public class WebAppModul extends AbstractModule {

    @Override
    protected void configure() {
        bind(GWTServiceTestAsync.class).toProvider(GWTServiceTestProvider.class).in(Singleton.class);
    }

    public static class GWTServiceTestProvider implements Provider<GWTServiceTestAsync> {

        @Override
        public GWTServiceTestAsync get() {
            CookieManager session = new CookieManager(null, CookiePolicy.ACCEPT_ALL);
            return (GWTServiceTestAsync) SyncProxy.newProxyInstance(
                    GWTServiceTestAsync.class,
                    "http://localhost:8080/WebSA_WS12/", // URL to webapp -- http://127.0.0.1:8888/testgame
                    "gwtservicetest",
                    session);
        }
    }
}
