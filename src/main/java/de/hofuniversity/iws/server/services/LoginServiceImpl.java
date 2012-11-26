/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.server.services;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.google.common.base.Optional;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import de.hofuniversity.iws.client.PhysixLab;
import de.hofuniversity.iws.server.OAuthCallbackServlet;
import de.hofuniversity.iws.server.login.Session;
import de.hofuniversity.iws.shared.dto.SessionDTO;
import de.hofuniversity.iws.shared.services.LoginService;
import de.hofuniversity.iws.shared.services.login.LoginException;
import de.hofuniversity.iws.shared.services.login.LoginProvider;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author User
 */
@RemoteServiceRelativePath("login")
public class LoginServiceImpl extends RemoteServiceServlet implements LoginService {

    private static Map<String, Session> sessions = new ConcurrentHashMap<>();
    public static final String SESSION_ATTRIBUTE = "session";
    public static final int TIMEOUT_INTERVALL = 10_000;

    @Override
    public Optional<SessionDTO> getSessionFromId(String sessionId) {
        if (checkSessionID(sessionId)) {
            Session s = sessions.get(sessionId);
            setStoredSession(s);

            if (s.isValid(getThreadLocalRequest().getRemoteAddr())) {
                s.updateExpireDate();
                return Optional.of(s.createDTO());
            } else {
                logout(sessionId);
            }
        }
        return Optional.absent();
    }

    @Override
    public SessionDTO waitForOAuthSession() throws LoginException {
        Optional<Session> session = Optional.absent();
        long time = System.currentTimeMillis();
        do {
            if (getSessionAttribute(OAuthCallbackServlet.OAUTH_FAIL).isPresent()) {
                invalidate();
                throw new LoginException("OAuth failed!");
            }

            if (System.currentTimeMillis() - time > TIMEOUT_INTERVALL) {
                invalidate();
                throw new LoginException("Login timed out!");
            }

            session = getSessionAttribute(SESSION_ATTRIBUTE);

            try {
                Thread.sleep(50);
            } catch (InterruptedException ex) {
            }
        } while (!session.isPresent());

        sessions.put(session.get().toString(), session.get());
        return session.get().createDTO();
    }

    @Override
    public String getOAuthLoginUrl(LoginProvider provider) {
        invalidate();
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void logout(String sessionID) {
        if (checkSessionID(sessionID)) {
            sessions.remove(sessionID);

            Optional<Session> session = getStoredSession();
            if (session.isPresent()) {
                sessions.remove(session.get().toString());
            }

            invalidate();
        }
    }

    private void invalidate() {
        getThreadLocalRequest().getSession().invalidate();
    }

    private void setStoredSession(Session sess) {
        getThreadLocalRequest().getSession().setAttribute(SESSION_ATTRIBUTE, sess);
    }

    private boolean checkSessionID(String sid) {
        for (Cookie c : getThreadLocalRequest().getCookies()) {
            if (PhysixLab.SESSION_ID_COOKIE.equals(c.getName())) {
                return sid.equals(c.getValue());
            }
        }
        return false;
    }

    private Optional<Session> getStoredSession() {
        return getStoredSession(getThreadLocalRequest());
    }

    public static Optional<Session> getStoredSession(final HttpServletRequest request) {
        Optional<Session> os = getSessionAttribute(request, SESSION_ATTRIBUTE);
        if (os.isPresent()) {
            if (!os.get().isValid(request.getRemoteAddr())) {
                sessions.remove(os.get().toString());
                request.getSession().invalidate();
            } else {
                return os;
            }
        }
        return Optional.absent();
    }

    public <T> Optional<T> getSessionAttribute(String attributeName) {
        return getSessionAttribute(getThreadLocalRequest(), attributeName);
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
