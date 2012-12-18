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
import de.hofuniversity.iws.server.dto.UserMapper;
import de.hofuniversity.iws.shared.services.UserFriendService;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author User
 */
@RemoteServiceRelativePath("userfriend")
public class UserFriendServiceImpl extends RemoteServiceServlet implements UserFriendService {
    
    public static final String USER_ATTRIBUTE = "user";
    public static final String FRIENDS_ATTRIBUTE = "friends";
    
    UserDTO actualUser ;
    
    @Override
    public UserDTO getactualUser() {
        Optional<User> loggedinuser = getSessionAttribute(USER_ATTRIBUTE);
        if(loggedinuser.isPresent())
        {
        User u = loggedinuser.get();
         UserMapper usermapper = new UserMapper();
         actualUser =  usermapper.mapUsertoDTO(u);
         return actualUser;
        }

        return null;
    }
    @Override
    public Iterable<UserDTO> getFriends(){
        Optional<Iterable<User>> friends = getSessionAttribute(FRIENDS_ATTRIBUTE);
        if(friends.isPresent())
        {
        Iterable<User> f = friends.get();
            ArrayList<UserDTO> retfriends = new ArrayList<>();
            for(User x : f)
            {
                UserDTO dtoUser = new UserDTO();
                    dtoUser.setAccountIdentificationString(x.getAccountIdentificationString());
                    dtoUser.setBirthDate(x.getBirthDate());
                    dtoUser.setCity(x.getCity());
                    dtoUser.setFirstName(x.getFirstName());
                    dtoUser.setLastName(x.getLastName());
                    dtoUser.setUserName(x.getUserName());
                    dtoUser.setUserPic(x.getUserPic());  
                retfriends.add(dtoUser);
            }
            return retfriends;
        }
        return null;
    }
    
    public String parseThemes()
    {
        return "Hello, world";
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
