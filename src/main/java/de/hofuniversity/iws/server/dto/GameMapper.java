/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.server.dto;

import de.hofuniversity.iws.shared.dto.GameDTO;
import de.hofuniversity.iws.shared.dto.GameResultDTO;
import de.hofuniversity.iws.shared.dto.LessonDTO;
import de.hofuniversity.iws.shared.dto.LessonProgressDTO;
import de.hofuniversity.iws.server.data.entities.Game;
import de.hofuniversity.iws.server.data.entities.GameResult;
import de.hofuniversity.iws.server.data.entities.Lesson;
import de.hofuniversity.iws.server.data.entities.LessonProgress;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author User
 */
public class GameMapper {
     public GameDTO mapGametoDTO(Game u)
     {
         GameDTO gdto = new GameDTO();
         gdto.setName(u.getName());
         
            List<GameResult> lg = u.getGameResultList();
            if(lg!=null)
            {
                List<GameResult> lgr = new ArrayList<>(u.getGameResultList());
                List<GameResultDTO> lgrdto = new ArrayList<>();
                for(GameResult x: lgr)
                {
                    GameResultMapper gameresultmapper = new GameResultMapper();
                    lgrdto.add(gameresultmapper.mapGameResulttoDTO(x));
                }
                gdto.setGameResultList(lgrdto);
            }
            
            List<Lesson> lt = u.getLessonList();
            if(lt!=null)
            {
                List<Lesson> ll = new ArrayList<>(u.getLessonList());
                List<LessonDTO> lldto = new ArrayList<>();
                for(Lesson x: ll)
                {
                    LessonMapper lessonmapper = new LessonMapper();
                    lldto.add(lessonmapper.mapLessontoDTO(x));
                }
                gdto.setLessonList(lldto);
            }
         
         return gdto;
     }   
}
