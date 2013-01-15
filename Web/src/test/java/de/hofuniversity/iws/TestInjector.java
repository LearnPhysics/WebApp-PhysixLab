/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws;

import com.google.gwt.core.client.GWT;
import com.google.gwt.inject.client.*;
import de.hofuniversity.iws.TestAssisted.TestAssistedFactory;

/**
 *
 * @author Daniel Heinrich <DannyNullZwo@gmail.com>
 */
@GinModules(TestGinModule.class)
public interface TestInjector extends Ginjector {
    public static final TestInjector INSTANCE = GWT.create(TestInjector.class);

//    public LoginPageFactory getLoginPage();
    public TestAssistedFactory get();
}
