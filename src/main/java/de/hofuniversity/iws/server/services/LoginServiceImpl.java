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
//TODO nochmals überprüfen was getSession mit und ohne bool flag macht
@RemoteServiceRelativePath("login")
public class LoginServiceImpl extends RemoteServiceServlet implements LoginService {

    public static final String SESSION_ATTRIBUTE = "session";
    public static final String TOKEN_ATTRIBUTE = "token";
    public static final String USER_ATTRIBUTE = "user";
    public static final int TIMEOUT_INTERVALL = 10;

    @Override
    public Optional<String> getSessionToken() {
        return getSession().transform(new Function<HttpSession, String>() {
            @Override
            public String apply(HttpSession session) {
                return (String) session.getAttribute(TOKEN_ATTRIBUTE);
            }
        });
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
            OAuthRequest request = oauth.createRequest();
            storeSessionAttribute(OAuthCallbackServlet.OAUTH_LOGIN_ATTRIBUTE, new OAuthLogin(request));
            return request.getAuthorizeUrl();
        } catch (IllegalArgumentException ex) {
            throw new UnsupportedOperationException("no support for provider: " + provider);
        }
    }

    @Override
    public void logout() {
        getThreadLocalRequest().getSession(true).invalidate();
    }

    public Optional<HttpSession> getSession() {
        return Optional.fromNullable(getThreadLocalRequest().getSession(false));
    }

    public <T> Optional<T> getSessionAttribute(String attributeName) {
        return getSessionAttribute(getThreadLocalRequest(), attributeName);
    }

    public void storeSessionAttribute(String attributeName, Object value) {
        storeSessionAttribute(getThreadLocalRequest(), attributeName, value);
    }

    public static void storeSessionAttribute(HttpServletRequest request, String attributeName, Object value) {
        request.getSession().setAttribute(attributeName, value);
    }

    public static <T> Optional<T> getSessionAttribute(HttpServletRequest request, String attributeName) {
        Object o = request.getSession().getAttribute(attributeName);
        try {
            return Optional.fromNullable((T) o);
        } catch (ClassCastException ex) {
            return Optional.absent();
        }
    }
}
