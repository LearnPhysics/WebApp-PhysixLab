/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.gwt.inject.client.assistedinject.GinFactoryModuleBuilder;
import com.google.inject.name.Names;
import de.hofuniversity.iws.TestAssisted.TestAssistedFactory;
import javax.inject.Provider;

/**
 *
 * @author Daniel Heinrich <DannyNullZwo@gmail.com>
 */
public class TestGinModule extends AbstractGinModule {

    @Override
    protected void configure() {
//        Class[] factoryClasses = new Class[]{
//            TestAssistedFactory.class
//        };
//
//        for (Class factory : factoryClasses) {
//        }

        bind(String.class).annotatedWith(Names.named("Test")).toProvider(StringProvider.class);
        install(new GinFactoryModuleBuilder().build(TestAssistedFactory.class));        
    }

    public static class StringProvider implements Provider<String> {

        @Override
        public String get() {
            return "This is a test: ";
        }
    }
}
