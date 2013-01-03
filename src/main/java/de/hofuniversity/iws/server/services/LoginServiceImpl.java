/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.server.services;

import java.util.UUID;

import de.hofuniversity.iws.server.GameFactorys;
import de.hofuniversity.iws.server.oauth.*;
import de.hofuniversity.iws.server.oauth.provider.OAuthProvider;
import de.hofuniversity.iws.shared.dto.LoginDTO;
import de.hofuniversity.iws.shared.entityimpl.UserDBO;
import de.hofuniversity.iws.shared.services.LoginService;
import de.hofuniversity.iws.shared.services.LoginException;

import com.google.common.base.*;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import javax.servlet.http.*;

/**
 *
 * @author UserDBO
 */
@RemoteServiceRelativePath("login")
public class LoginServiceImpl extends RemoteServiceServlet implements LoginService {

    public static final String SESSION_ATTRIBUTE = "session";
    public static final String TOKEN_ATTRIBUTE = "token";
    public static final String USER_ATTRIBUTE = "user";
    public static final String FRIENDS_ATTRIBUTE = "friends";
    public static final int TIMEOUT_INTERVALL = 60000;

    @Override
    public Optional<LoginDTO> getLoginData() {
        Optional<String> token = getSessionAttribute(TOKEN_ATTRIBUTE);
        Optional<UserDBO> user = getSessionAttribute(USER_ATTRIBUTE);
        if (token.isPresent() && user.isPresent()) {
            return Optional.of(new LoginDTO(user.get(), token.get()));
        }
        return Optional.absent();
    }

    @Override
    public LoginDTO waitForOAuthVerification() throws LoginException {
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
                    Optional<LoginDTO> data = getLoginData();
                    if (data.isPresent()) {
                        return data.get();
                    } else {
                        throw new LoginException("Uncomplete login data");
                    }
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
            Providers prov = Providers.valueOf(provider);
            OAuthProvider oauth = prov.getProvider();
            OAuthAccessRequest request = oauth.createRequest();
            storeSessionAttribute(OAuthCallbackServlet.OAUTH_LOGIN_ATTRIBUTE, new OAuthLogin(prov, request));
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
        Optional<HttpSession> session = getSession(request);
        if (session.isPresent()) {
            return Optional.fromNullable((T) session.get().getAttribute(attributeName));
        }
        return Optional.absent();
    }

    public void storeSessionAttribute(String attributeName, Object value) {
        storeSessionAttribute(getThreadLocalRequest(), attributeName, value);
    }

    public static void storeSessionAttribute(HttpServletRequest request, String attributeName, Object value) {
        request.getSession().setAttribute(attributeName, value);
    }
}
