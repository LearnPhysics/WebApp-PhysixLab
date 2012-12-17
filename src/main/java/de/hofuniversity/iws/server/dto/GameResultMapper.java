/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.server.dto;

import de.hofuniversity.iws.shared.dto.GameDTO;
import de.hofuniversity.iws.shared.dto.GameResultDTO;
import de.hofuniversity.iws.shared.dto.UserDTO;
import de.hofuniversity.iws.server.data.entities.Game;
import de.hofuniversity.iws.server.data.entities.GameResult;
import de.hofuniversity.iws.server.data.entities.User;

/**
 *
 * @author User
 */
public class GameResultMapper {
     public GameResultDTO mapGameResulttoDTO(GameResult u)
     {
         GameResultDTO grdto = new GameResultDTO();
         grdto.setDate(u.getDate());
         grdto.setPoints(u.getPoints());
         
            User user = u.getUser();
            UserMapper usermapper = new UserMapper();
            UserDTO udto = usermapper.mapUsertoDTO(user);
         grdto.setUser(udto);
         
            Game game = u.getGame();
            GameMapper gamemapper = new GameMapper();
            GameDTO gdto = gamemapper.mapGametoDTO(game);
            
         grdto.setGame(gdto);
         
         return grdto;
     }
}
