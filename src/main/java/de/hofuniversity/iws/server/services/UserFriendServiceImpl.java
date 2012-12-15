/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.server.services;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import de.hofuniversity.iws.dto.UserDTO;
import de.hofuniversity.iws.server.data.entities.User;
import de.hofuniversity.iws.shared.services.UserFriendService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author User
 */
@RemoteServiceRelativePath("userfriend")
public class UserFriendServiceImpl extends RemoteServiceServlet implements UserFriendService {
    public static final String USER_ATTRIBUTE = "user";

    @Override
    public UserDTO getactualUser() {
        Optional<User> loggedinuser = getSessionAttribute(USER_ATTRIBUTE);
        return null;
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
        public Optional<HttpSession> getSession() {
        return getSession(getThreadLocalRequest());
    }

    public static Optional<HttpSession> getSession(HttpServletRequest req) {
        return Optional.fromNullable(req.getSession(false));
    }
}
