/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.server.dto;

import de.hofuniversity.iws.dto.GameResultDTO;
import de.hofuniversity.iws.dto.LessonProgressDTO;
import de.hofuniversity.iws.dto.NetworkAccountDTO;
import de.hofuniversity.iws.dto.UserDTO;
import de.hofuniversity.iws.server.data.entities.GameResult;
import de.hofuniversity.iws.server.data.entities.LessonProgress;
import de.hofuniversity.iws.server.data.entities.NetworkAccount;
import de.hofuniversity.iws.server.data.entities.User;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class UserMapper {
    public UserDTO mapUsertoDTO(User u)
    {
        UserDTO udto = new UserDTO();
        
        udto.setUserName(u.getUserName());
        udto.setLastName(u.getLastName());
        udto.setFirstName(u.getFirstName());
        udto.setBirthDate(u.getBirthDate());
        udto.setCity(u.getCity());
        udto.setUserPic(u.getUserPic());
        udto.setAccountIdentificationString(u.getAccountIdentificationString());
        
        List<NetworkAccount> lna = new ArrayList<>(u.getNetworkAccountList());
        List<NetworkAccountDTO> lnadto = new ArrayList<>();
        for(NetworkAccount x:lna)
        {
          NetworkAccountMapper networkmapper = new NetworkAccountMapper();
          lnadto.add(networkmapper.mapNetworkAccounttoDTO(x));
        }
        udto.setNetworkAccountList(lnadto);
        
        List<GameResult> lgr = new ArrayList<>(u.getGameResultList());
        List<GameResultDTO> lgrdto = new ArrayList<>();
        for(GameResult x: lgr)
        {
            GameResultMapper gameresultmapper = new GameResultMapper();
            lgrdto.add(gameresultmapper.mapGameResulttoDTO(x));
        }
        udto.setGameResultList(lgrdto);
        
        List<LessonProgress> llp = new ArrayList<>(u.getLessonProgressList());
        List<LessonProgressDTO> llpdto = new ArrayList<>();
        for(LessonProgress x: llp)
        {
            LessonProgressMapper lessonprogressmapper = new LessonProgressMapper();
            llpdto.add(lessonprogressmapper.mapLessonProgresstoDTO(x));
        }
        udto.setLessonProgressList(llpdto);
        return udto;
    }
}
