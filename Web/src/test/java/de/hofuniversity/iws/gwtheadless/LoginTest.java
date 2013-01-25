/*
 * Copyright (C) 2013 Daniel Heinrich <dannynullzwo@gmail.com>
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * (version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library.  If not, see <http://www.gnu.org/licenses/> 
 * or write to the Free Software Foundation, Inc., 51 Franklin Street,
 * Fifth Floor, Boston, MA 02110-1301  USA.
 */
package de.hofuniversity.iws.gwtheadless;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.googlecode.gwt.test.*;
import com.googlecode.gwt.test.gin.GInjectorCreateHandler;
import de.hofuniversity.iws.client.HistoryPageController;
import de.hofuniversity.iws.client.widgets.LoginPage;
import de.hofuniversity.iws.client.widgets.UserHome.UserHome;
import de.hofuniversity.iws.gwttests.TestInjector;
import de.hofuniversity.iws.server.oauth.Providers;
import de.hofuniversity.iws.shared.services.*;
import org.junit.*;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

/**
 *
 * @author Daniel Heinrich <dannynullzwo@gmail.com>
 */
@GwtModule("de.hofuniversity.iws.PhysixLabTest")
public class LoginTest extends GwtTestWithMockito {

    @Mock LoginServiceAsync loginService;
    @Mock HistoryPageController pageController;
    TestInjector INSTANCE;

    @Before
    public void setupGIN() {
        addGwtCreateHandler(new GInjectorCreateHandler());
        INSTANCE = GWT.create(TestInjector.class);
    }

    @Test
    public void testLoginService() {
        LoginServiceAsync service = GWT.create(LoginService.class);

        for (final Providers provider : Providers.values()) {
            service.getOAuthLoginUrl(provider.name(), new AsyncCallback<String>() {
                @Override
                public void onFailure(Throwable caught) {
                    throw new UnsupportedOperationException("Not supported yet.");
                }

                @Override
                public void onSuccess(String result) {
                    try {
                        new java.net.URL(result).openConnection();
                    } catch (Exception ex) {
                        throw new RuntimeException("Can not connect to login-url of provider: " + provider.name() + "\n\t" + result);
                    }
                }
            });
        }
    }

    @Test
    public void testGoogleLogin() {
        doSuccessCallback(null).when(loginService).getOAuthLoginUrl(eq(Providers.GOOGLE.name()), any(AsyncCallback.class));

        LoginPage page = new LoginPage(loginService, INSTANCE.getLab(), INSTANCE.getPageController());
        page.loginWithGoogle(null);

        verify(loginService).getOAuthLoginUrl(eq(Providers.GOOGLE.name()), any(AsyncCallback.class));
    }

    @Test
    public void testFacebookLogin() {
        doSuccessCallback(null).when(loginService).getOAuthLoginUrl(eq(Providers.FACEBOOK.name()), any(AsyncCallback.class));

        LoginPage page = new LoginPage(loginService, INSTANCE.getLab(), INSTANCE.getPageController());
        page.loginWithFacebook(null);

        verify(loginService).getOAuthLoginUrl(eq(Providers.FACEBOOK.name()), any(AsyncCallback.class));
    }

    @Test
    public void testTwitterLogin() {
        doSuccessCallback(null).when(loginService).getOAuthLoginUrl(eq(Providers.TWITTER.name()), any(AsyncCallback.class));

        LoginPage page = new LoginPage(loginService, INSTANCE.getLab(), INSTANCE.getPageController());
        page.loginWithTwitter(null);

        verify(loginService).getOAuthLoginUrl(eq(Providers.TWITTER.name()), any(AsyncCallback.class));
    }

    @Test
    public void testLogin() {
        doSuccessCallback(null).when(loginService).getOAuthLoginUrl(any(String.class), any(AsyncCallback.class));
        doSuccessCallback(null).when(loginService).waitForOAuthVerification(any(AsyncCallback.class));

        LoginPage page = new LoginPage(loginService, INSTANCE.getLab(), pageController);
        page.loginWithTwitter(null);

        verify(pageController).changePage(UserHome.NAME);
    }
}
