/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.server.services;

import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import de.hofuniversity.iws.dto.UserDTO;
import de.hofuniversity.iws.shared.services.UserFriendService;

/**
 *
 * @author User
 */
@RemoteServiceRelativePath("userfriend")
public class UserFriendServiceImpl extends RemoteServiceServlet implements UserFriendService {

    @Override
    public UserDTO getactualUser() {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserName("Andreas Arndt");
        return userDTO;
    }

}
