/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.server.dto;

import de.hofuniversity.iws.shared.dto.LessonDTO;
import de.hofuniversity.iws.shared.dto.LessonProgressDTO;
import de.hofuniversity.iws.shared.dto.UserDTO;
import de.hofuniversity.iws.server.data.entities.Lesson;
import de.hofuniversity.iws.server.data.entities.LessonProgress;
import de.hofuniversity.iws.server.data.entities.User;

/**
 *
 * @author User
 */
public class LessonProgressMapper {
        public LessonProgressDTO mapLessonProgresstoDTO(LessonProgress u)
        {
           LessonProgressDTO lpdto = new LessonProgressDTO();
           
           lpdto.setDate(u.getDate());
           lpdto.setPoints(u.getPoints());
           
            User user = u.getUser();
            UserMapper usermapper = new UserMapper();
            UserDTO udto = usermapper.mapUsertoDTO(user);
            lpdto.setUser(udto);
            
            Lesson lesson = u.getLesson();
            LessonMapper lessonmapper = new LessonMapper();
            LessonDTO ldto = lessonmapper.mapLessontoDTO(lesson);
            lpdto.setLesson(ldto);            
            
           return lpdto;
        }
}
