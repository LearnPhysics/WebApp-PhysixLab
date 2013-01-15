/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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
