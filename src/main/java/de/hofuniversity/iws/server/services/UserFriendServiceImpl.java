/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.server.services;

import java.util.ArrayList;
import de.hofuniversity.iws.shared.entityimpl.UserDBO;
import de.hofuniversity.iws.shared.entitys.User;
import de.hofuniversity.iws.shared.services.UserFriendService;

import com.google.common.base.*;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import javax.servlet.http.*;

/**
 *
 * @author UserDBO
 */
@RemoteServiceRelativePath("userfriend")
public class UserFriendServiceImpl extends RemoteServiceServlet implements UserFriendService {
    
    public static final String USER_ATTRIBUTE = "user";
    public static final String FRIENDS_ATTRIBUTE = "friends";
    
    @Override
    public Iterable<User> getFriends(){
        Optional<Iterable<UserDBO>> friends = getSessionAttribute(FRIENDS_ATTRIBUTE);
        if(friends.isPresent())
        {
        Iterable<UserDBO> f = friends.get();
            ArrayList<User> retfriends = new ArrayList<User>();
            for(UserDBO x : f)
            {
//                UserDTO dtoUser = new UserDTO();
//                    dtoUser.setAccountIdentificationString(x.getAccountIdentificationString());
//                    dtoUser.setBirthDate(x.getBirthDate());
//                    dtoUser.setCity(x.getCity());
//                    dtoUser.setFirstName(x.getFirstName());
//                    dtoUser.setLastName(x.getLastName());
//                    dtoUser.setUserName(x.getUserName());
//                    dtoUser.setUserPic(x.getUserPic());  
//                retfriends.add(dtoUser);
            }
            return retfriends;
        }
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
