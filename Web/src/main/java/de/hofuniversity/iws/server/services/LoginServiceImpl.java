/*
  * Copyright (C) 2012 Andreas Arndt
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
package de.hofuniversity.iws.server.services;

import java.util.UUID;

import com.google.common.base.Optional;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import de.hofuniversity.iws.server.data.entities.UserDBO;
import de.hofuniversity.iws.server.oauth.*;
import de.hofuniversity.iws.server.oauth.provider.OAuthProvider;
import de.hofuniversity.iws.shared.dto.LoginDTO;
import de.hofuniversity.iws.shared.services.*;
import javax.servlet.http.*;

/**
 *
 * @author Andreas Arndt <andreas.arndt@hof-university.de>
 */
@RemoteServiceRelativePath("login")
public class LoginServiceImpl extends RemoteServiceServlet implements LoginService {

    public static final String SESSION_ATTRIBUTE = "session";
    public static final String TOKEN_ATTRIBUTE = "token";
    public static final String USER_ATTRIBUTE = "user";
    public static final int TIMEOUT_INTERVALL = 60000;

    @Override
    public Optional<LoginDTO> getLoginData() {
        Optional<String> token = getSessionAttribute(TOKEN_ATTRIBUTE);
        Optional<UserDBO> user = getSessionAttribute(USER_ATTRIBUTE);
        if (token.isPresent() && user.isPresent()) {
            return Optional.of(new LoginDTO(user.get().getDTO(), token.get()));
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
