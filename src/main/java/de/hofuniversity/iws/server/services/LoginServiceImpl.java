/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.server.services;

import java.util.UUID;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import de.hofuniversity.iws.server.oauth.*;
import de.hofuniversity.iws.server.oauth.provider.OAuthProvider;
import de.hofuniversity.iws.shared.services.LoginService;
import de.hofuniversity.iws.shared.services.login.LoginException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author User
 */
@RemoteServiceRelativePath("login")
public class LoginServiceImpl extends RemoteServiceServlet implements LoginService {

    public static final String SESSION_ATTRIBUTE = "session";
    public static final String TOKEN_ATTRIBUTE = "token";
    public static final String USER_ATTRIBUTE = "user";
    public static final String FRIENDS_ATTRIBUTE = "friends";
    public static final int TIMEOUT_INTERVALL = 60_000;

    @Override
    public Optional<String> getSessionToken() {
        return getSessionAttribute(TOKEN_ATTRIBUTE);
    }

    @Override
    public String waitForOAuthVerification() throws LoginException {
        Optional<OAuthLogin> login = getSessionAttribute(OAuthCallbackServlet.OAUTH_LOGIN_ATTRIBUTE);
        if (login.isPresent()) {
            OAuthLogin l = login.get();
            synchronized (l) {
                if (!l.successfull) {
                   try {
                        l.wait(TIMEOUT_INTERVALL);
                    } catch (InterruptedException ex) {
                    }
                }
                if (l.successfull) {
                    String token = UUID.randomUUID().toString();
                    storeSessionAttribute(TOKEN_ATTRIBUTE, token);
                    return token;
                } else {
                    throw new LoginException("Login Timeout eceeded or login wasn't succesfull!");
                }
            }
        } else {
            throw new LoginException("No OAuth login was initialized!");
        }
    }

    @Override
    public String getOAuthLoginUrl(String provider) {
        try {
            OAuthProvider oauth = Providers.valueOf(provider).getProvider();
            OAuthAccessRequest request = oauth.createRequest();
            storeSessionAttribute(OAuthCallbackServlet.OAUTH_LOGIN_ATTRIBUTE, new OAuthLogin(request));
            storeSessionAttribute(Providers.PROVIDER_NAME_ATTRIBUTE, Providers.valueOf(provider).name());
            return request.getAuthorizeUrl();
        } catch (IllegalArgumentException ex) {
            throw new UnsupportedOperationException("no support for provider: " + provider);
        }
    }

    @Override
    public void logout() {
        getThreadLocalRequest().getSession().invalidate();
    }

    public Optional<HttpSession> getSession() {
        return getSession(getThreadLocalRequest());
    }

    public static Optional<HttpSession> getSession(HttpServletRequest req) {
        return Optional.fromNullable(req.getSession(false));
    }

    public <T> Optional<T> getSessionAttribute(String attributeName) {
        return getSessionAttribute(getThreadLocalRequest(), attributeName);
    }

    public static <T> Optional<T> getSessionAttribute(HttpServletRequest request, final String attributeName) {
        return getSession(request).transform(new Function<HttpSession, T>() {
            @Override
            public T apply(HttpSession session) {
                return (T) session.getAttribute(attributeName);
            }
        });
    }

    public void storeSessionAttribute(String attributeName, Object value) {
        storeSessionAttribute(getThreadLocalRequest(), attributeName, value);
    }

    public static void storeSessionAttribute(HttpServletRequest request, String attributeName, Object value) {
        request.getSession().setAttribute(attributeName, value);
    }
}
