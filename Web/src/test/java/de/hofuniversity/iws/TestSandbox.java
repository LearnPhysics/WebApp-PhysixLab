/*
  * Copyright (C) 2012 daniel
  *
  * This program is free software: you can redistribute it and/or modify
  * it under the terms of the GNU General Public License as published by
  * the Free Software Foundation, either version 3 of the License, or
  * (at your option) any later version.
  *
  * This program is distributed in the hope that it will be useful,
  * but WITHOUT ANY WARRANTY; without even the implied warranty of
  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  * GNU General Public License for more details.
  *
  * You should have received a copy of the GNU General Public License
  * along with this program.  If not, see <http://www.gnu.org/licenses/>.
  */
package de.hofuniversity.iws;

import java.io.InputStream;
import de.hofuniversity.iws.client.Main;
import de.hofuniversity.iws.client.widgets.LoginPage;
import de.hofuniversity.iws.server.oauth.Providers;
import de.hofuniversity.iws.shared.services.LoginServiceAsync;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.googlecode.gwt.test.*;
import com.googlecode.gwt.test.gin.GInjectorCreateHandler;
import de.hofuniversity.iws.TestAssisted.TestAssistedFactory;
import org.junit.*;

import static org.mockito.Mockito.*;

/**
 *
 * @author Daniel Heinrich <dannynullzwo@gmail.com>
 */
//@GwtModule("de.hofuniversity.iws.PhysixLabTest")
//public class TestSandbox extends GwtTestWithMockito {
//
////    private LoginModul page;
//
//    @Before
//    public void setupGIN() {
//        addGwtCreateHandler(new GInjectorCreateHandler());
//    }
//
////    @Before
////    public void before() {
////        page = new LoginModul();
////        page.onModuleLoad();
////    }
//    
////    @Test
////    public void test()
////    {
////        TestAssistedFactory get = TestInjector.INSTANCE.get();
////        TestAssisted create = get.create("test");
////    }
////    @Mock
////    private LoginServiceAsync loginService;
////
////    @Test
////    public void testGoogleLogin() {
////        doSuccessCallback(null).when(loginService).getOAuthLoginUrl(eq(Providers.GOOGLE.name()), any(AsyncCallback.class));
////        doSuccessCallback(null).when(loginService).waitForOAuthVerification(any(AsyncCallback.class));
////
////        page.getLogin().loginWithGoogle(null);
////
////        verify(loginService).getOAuthLoginUrl(eq(Providers.GOOGLE.name()), any(AsyncCallback.class));
////    }
//}
