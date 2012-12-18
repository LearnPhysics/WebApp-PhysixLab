/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.shared.services;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import de.hofuniversity.iws.dto.UserDTO;

/**
 *
 * @author User
 */
@RemoteServiceRelativePath("userfriend")
public interface UserFriendService extends RemoteService {
    public UserDTO getactualUser();  
    public Iterable<UserDTO> getFriends();
    public String parseThemes();
    
}
