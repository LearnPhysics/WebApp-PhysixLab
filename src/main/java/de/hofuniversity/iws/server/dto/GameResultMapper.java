/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.server.dto;

import de.hofuniversity.iws.shared.dto.GameDTO;
import de.hofuniversity.iws.shared.dto.GameResultDTO;
import de.hofuniversity.iws.shared.dto.UserDTO;
import de.hofuniversity.iws.server.data.entities.GameDBO;
import de.hofuniversity.iws.server.data.entities.GameResultDBO;
import de.hofuniversity.iws.server.data.entities.UserDBO;

/**
 *
 * @author UserDBO
 */
public class GameResultMapper {
     public GameResultDTO mapGameResulttoDTO(GameResultDBO u)
     {
         GameResultDTO grdto = new GameResultDTO();
         grdto.setDate(u.getDate());
         grdto.setPoints(u.getPoints());
         
            UserDBO user = u.getUser();
            UserMapper usermapper = new UserMapper();
            UserDTO udto = usermapper.mapUsertoDTO(user);
         grdto.setUser(udto);
         
            GameDBO game = u.getGame();
            GameMapper gamemapper = new GameMapper();
            GameDTO gdto = gamemapper.mapGametoDTO(game);
            
         grdto.setGame(gdto);
         
         return grdto;
     }
}
