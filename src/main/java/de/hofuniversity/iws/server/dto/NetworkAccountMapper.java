/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.server.dto;

import de.hofuniversity.iws.shared.dto.NetworkAccountDTO;
import de.hofuniversity.iws.shared.dto.UserDTO;
import de.hofuniversity.iws.server.data.entities.NetworkAccount;
import de.hofuniversity.iws.server.data.entities.User;

/**
 *
 * @author User
 */
public class NetworkAccountMapper {
        public NetworkAccountDTO mapNetworkAccounttoDTO(NetworkAccount u)
        {
            NetworkAccountDTO nadto = new NetworkAccountDTO();
            
            nadto.setNetworkName(u.getNetworkName());
            nadto.setAccountIdentificationString(u.getAccountIdentificationString());
            nadto.setOauthAccessSecret(u.getOauthAccessSecret());
            nadto.setOauthAccessToken(u.getOauthAccessToken());
            
            User user = u.getUser();
            UserMapper usermapper = new UserMapper();
            UserDTO udto = usermapper.mapUsertoDTO(user);
            nadto.setUser(udto);
            
            return nadto;
        }
}
