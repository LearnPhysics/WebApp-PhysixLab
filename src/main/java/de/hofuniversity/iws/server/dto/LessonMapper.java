/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.server.dto;

import de.hofuniversity.iws.dto.GameDTO;
import de.hofuniversity.iws.dto.LessonDTO;
import de.hofuniversity.iws.dto.SubjectAreaDTO;
import de.hofuniversity.iws.server.data.entities.Game;
import de.hofuniversity.iws.server.data.entities.Lesson;
import de.hofuniversity.iws.server.data.entities.SubjectArea;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class LessonMapper {
     public LessonDTO mapLessontoDTO(Lesson u)
     {
         LessonDTO ldto = new LessonDTO();
         ldto.setName(u.getName());
         
            SubjectArea subjectarea = u.getSubjectArea();
            SubjectAreaMapper subjectareamapper = new SubjectAreaMapper();
            SubjectAreaDTO sadto = subjectareamapper.mapSubjectAreatoDTO(subjectarea);
            ldto.setSubjectArea(sadto);
            
        List<Game> lg = new ArrayList<>(u.getGameList());
        List<GameDTO> lgdto = new ArrayList<>();
        for(Game x: lg)
        {
            GameMapper gamemapper = new GameMapper();
            lgdto.add(gamemapper.mapGametoDTO(x));
        }
        ldto.setGameList(lgdto);
        
        LessonMapper lessonmapper = new LessonMapper();
        LessonDTO lpdto = lessonmapper.mapLessontoDTO(u.getParentLesson());
        ldto.setParentLesson(lpdto);         
         return ldto;
     }  
}
