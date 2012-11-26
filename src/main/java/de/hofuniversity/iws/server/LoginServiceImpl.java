/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.server;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import de.hofuniversity.iws.shared.dto.SessionDTO;
import de.hofuniversity.iws.shared.services.LoginService;
import de.hofuniversity.iws.shared.services.login.*;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author User
 */
@RemoteServiceRelativePath("login")
public class LoginServiceImpl extends RemoteServiceServlet implements LoginService {

    private static Map<String, Session> sessions = new ConcurrentHashMap<>();
    public static final String SESSION_ATTRIBUTE = "session";
    public static final String USER_ATTRIBUTE = "user";

    @Override
    public boolean isSessionValid(String sessionId) {
        Session s = sessions.get(sessionId);
        if (s.isValid(getThreadLocalRequest().getRemoteAddr()) && s.isSame(sessionId)) {
            s.updateExpireDate();
            setSession(s);
            return true;
        }
        return false;
    }

    @Override
    public SessionDTO waitForOAuthSessionID() throws OAuthException {
        Optional<User> user = getSessionAttribute(getThreadLocalRequest(), USER_ATTRIBUTE);
        while(!user.isPresent()){
            user = getSessionAttribute(getThreadLocalRequest(), USER_ATTRIBUTE);
        }
        Session session = createSession();
        return session.createDTO();
    }

    @Override
    public String getOAuthLoginUrl(LoginProvider provider) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void logout() {
        Optional<Session> session = getSession(getThreadLocalRequest());
        if (session.isPresent()) {
            sessions.remove(session.get().toString());
            getThreadLocalRequest().getSession().invalidate();
        }
    }

    private Session createSession() {
        Session s = new Session(getThreadLocalRequest().getRemoteAddr());
        setSession(s);
        return s;
    }

    private void setSession(Session sess) {
        sessions.put(sess.toString(), sess);
        getThreadLocalRequest().getSession().setAttribute(SESSION_ATTRIBUTE, sess);
    }

    public static Optional<Session> getSession(final HttpServletRequest request) {
        Optional<Session> os = getSessionAttribute(request, SESSION_ATTRIBUTE);
        return filter(os, new Predicate<Session>() {
            @Override
            public boolean apply(Session input) {
                return input.isValid(request.getRemoteAddr());
            }
        });
    }

    public static <T> Optional<T> getSessionAttribute(HttpServletRequest request, String attributeName) {
        Object o = request.getSession().getAttribute(attributeName);
        try {
            return Optional.fromNullable((T) o);
        } catch (ClassCastException ex) {
            return Optional.absent();
        }
    }

    public static <T> Optional<T> filter(Optional<T> o, Predicate<T> pred) {
        if (o.isPresent() && !pred.apply(o.get())) {
            return Optional.absent();
        } else {
            return o;
        }
    }
}
