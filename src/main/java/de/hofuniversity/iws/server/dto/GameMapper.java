/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.server.dto;

import de.hofuniversity.iws.shared.dto.GameDTO;
import de.hofuniversity.iws.shared.dto.GameResultDTO;
import de.hofuniversity.iws.shared.dto.LessonDTO;
import de.hofuniversity.iws.shared.dto.LessonProgressDTO;
import de.hofuniversity.iws.server.data.entities.GameDBO;
import de.hofuniversity.iws.server.data.entities.GameResultDBO;
import de.hofuniversity.iws.server.data.entities.LessonDBO;
import de.hofuniversity.iws.server.data.entities.LessonProgressDBO;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author User
 */
public class GameMapper {
     public GameDTO mapGametoDTO(GameDBO u)
     {
         GameDTO gdto = new GameDTO();
         gdto.setName(u.getName());
         
            List<GameResultDBO> lg = u.getGameResultList();
            if(lg!=null)
            {
                List<GameResultDBO> lgr = new ArrayList<GameResultDBO>(u.getGameResultList());
                List<GameResultDTO> lgrdto = new ArrayList<GameResultDTO>();
                for(GameResultDBO x: lgr)
                {
                    GameResultMapper gameresultmapper = new GameResultMapper();
                    lgrdto.add(gameresultmapper.mapGameResulttoDTO(x));
                }
                gdto.setGameResultList(lgrdto);
            }
            
            List<LessonDBO> lt = u.getLessonList();
            if(lt!=null)
            {
                List<LessonDBO> ll = new ArrayList<LessonDBO>(u.getLessonList());
                List<LessonDTO> lldto = new ArrayList<LessonDTO>();
                for(LessonDBO x: ll)
                {
                    LessonMapper lessonmapper = new LessonMapper();
                    lldto.add(lessonmapper.mapLessontoDTO(x));
                }
                gdto.setLessonList(lldto);
            }
         
         return gdto;
     }   
}
