/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.inject.client.AbstractGinModule;
import de.hofuniversity.iws.shared.services.*;
import javax.inject.Provider;

/**
 *
 * @author Daniel Heinrich <dannynullzwo@gmail.com>
 */
public class PhysixLabModul extends AbstractGinModule {

    @Override
    protected void configure() {
//        bind(LoginServiceAsync.class).toProvider(LoginProvider.class);
    }

    public static class LoginProvider implements Provider<LoginServiceAsync> {

        @Override
        public LoginServiceAsync get() {
            return GWT.create(LoginService.class);
        }
    }
}
