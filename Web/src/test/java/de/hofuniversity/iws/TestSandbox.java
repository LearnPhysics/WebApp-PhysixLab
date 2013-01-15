/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws;

import de.hofuniversity.iws.client.Main;
import de.hofuniversity.iws.client.widgets.LoginPage;
import de.hofuniversity.iws.server.oauth.Providers;
import de.hofuniversity.iws.shared.services.LoginServiceAsync;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.googlecode.gwt.test.*;
import org.junit.*;

import static org.mockito.Mockito.*;


/**
 *
 * @author Daniel Heinrich <dannynullzwo@gmail.com>
 */
/* @GwtModule("de.hofuniversity.iws.PhysixLabTest") */
public class TestSandbox /* extends GwtTestWithMockito */ {

//    private LoginPage page;
//
//    @Before
//    public void before() {
//        app = new Main();
//        app.onModuleLoad();
//    }
    
//    @Mock
//    private LoginServiceAsync loginService;
//    
//    @Test
//    public void testGoogleLogin()
//    {
//        LoginPage page = new LoginPage();
//        
//        doSuccessCallback(null).when(loginService).getOAuthLoginUrl(eq(Providers.GOOGLE.name()), any(AsyncCallback.class));
//        
//        page.loginWithGoogle(null);
//        
//        verify(loginService).getOAuthLoginUrl(eq(Providers.GOOGLE.name()), any(AsyncCallback.class));        
//    }
}
