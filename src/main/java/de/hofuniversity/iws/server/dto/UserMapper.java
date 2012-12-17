/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.server.dto;

import de.hofuniversity.iws.shared.dto.GameResultDTO;
import de.hofuniversity.iws.shared.dto.LessonProgressDTO;
import de.hofuniversity.iws.shared.dto.NetworkAccountDTO;
import de.hofuniversity.iws.shared.dto.UserDTO;
import de.hofuniversity.iws.server.data.entities.GameResultDBO;
import de.hofuniversity.iws.server.data.entities.LessonProgressDBO;
import de.hofuniversity.iws.server.data.entities.NetworkAccountDBO;
import de.hofuniversity.iws.server.data.entities.UserDBO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author UserDBO
 */
public class UserMapper {
    public UserDTO mapUsertoDTO(UserDBO u)
    {
        UserDTO udto = new UserDTO();
        
        udto.setUserName(u.getUserName());
        udto.setLastName(u.getLastName());
        udto.setFirstName(u.getFirstName());
        udto.setBirthDate(u.getBirthDate());
        udto.setCity(u.getCity());
        udto.setUserPic(u.getUserPic());
//        udto.setAccountIdentificationString(u.getAccountIdentificationString());
        List<NetworkAccountDBO> n = u.getNetworkAccountList();
        if(n!=null)
        {
            List<NetworkAccountDBO> lna = new ArrayList<NetworkAccountDBO>(n);
            List<NetworkAccountDTO> lnadto = new ArrayList<NetworkAccountDTO>();
            for(NetworkAccountDBO x:lna)
            {
              NetworkAccountMapper networkmapper = new NetworkAccountMapper();
              lnadto.add(networkmapper.mapNetworkAccounttoDTO(x));
            }
            udto.setNetworkAccountList(lnadto);
        }
        List<GameResultDBO> g = u.getGameResultList();
        if(g!=null)
        {
            List<GameResultDBO> lgr = new ArrayList<GameResultDBO>(g);
            List<GameResultDTO> lgrdto = new ArrayList<GameResultDTO>();
            for(GameResultDBO x: lgr)
            {
                GameResultMapper gameresultmapper = new GameResultMapper();
                lgrdto.add(gameresultmapper.mapGameResulttoDTO(x));
            }
            udto.setGameResultList(lgrdto);
        }
        List<LessonProgressDBO> p = u.getLessonProgressList();
        if(p!=null)
        {
            List<LessonProgressDBO> llp = new ArrayList<LessonProgressDBO>(p);
            List<LessonProgressDTO> llpdto = new ArrayList<LessonProgressDTO>();
            for(LessonProgressDBO x: llp)
            {
                LessonProgressMapper lessonprogressmapper = new LessonProgressMapper();
                llpdto.add(lessonprogressmapper.mapLessonProgresstoDTO(x));
            }
            udto.setLessonProgressList(llpdto);
        }
        return udto;
    }
}
