/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.server.dto;

import de.hofuniversity.iws.shared.dto.GameDTO;
import de.hofuniversity.iws.shared.dto.LessonDTO;
import de.hofuniversity.iws.shared.dto.SubjectAreaDTO;
import de.hofuniversity.iws.server.data.entities.GameDBO;
import de.hofuniversity.iws.server.data.entities.LessonDBO;
import de.hofuniversity.iws.server.data.entities.SubjectAreaDBO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class LessonMapper {
     public LessonDTO mapLessontoDTO(LessonDBO u)
     {
         LessonDTO ldto = new LessonDTO();
         ldto.setName(u.getName());
         
            SubjectAreaDBO subjectarea = u.getSubjectArea();
            SubjectAreaMapper subjectareamapper = new SubjectAreaMapper();
            SubjectAreaDTO sadto = subjectareamapper.mapSubjectAreatoDTO(subjectarea);
            ldto.setSubjectArea(sadto);
            
        List<GameDBO> l = u.getGameList();
        if(l!=null)
        {
            List<GameDBO> lg = new ArrayList<GameDBO>(l);
            List<GameDTO> lgdto = new ArrayList<GameDTO>();
            for(GameDBO x: lg)
            {
                GameMapper gamemapper = new GameMapper();
                lgdto.add(gamemapper.mapGametoDTO(x));
            }
            ldto.setGameList(lgdto);
        }
        
        LessonMapper lessonmapper = new LessonMapper();
        LessonDTO lpdto = lessonmapper.mapLessontoDTO(u.getParentLesson());
        ldto.setParentLesson(lpdto);         
         return ldto;
     }  
}
