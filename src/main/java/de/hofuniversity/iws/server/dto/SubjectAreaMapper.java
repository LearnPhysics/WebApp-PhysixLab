/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.server.dto;


import de.hofuniversity.iws.dto.LessonDTO;
import de.hofuniversity.iws.dto.SubjectAreaDTO;
import de.hofuniversity.iws.server.data.entities.Lesson;
import de.hofuniversity.iws.server.data.entities.SubjectArea;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class SubjectAreaMapper {
        public SubjectAreaDTO mapSubjectAreatoDTO(SubjectArea u)
        {
           SubjectAreaDTO sadto = new SubjectAreaDTO();
           sadto.setName(u.getName());
           
            List<Lesson> ll = new ArrayList<>(u.getLessonList());
            List<LessonDTO> lldto = new ArrayList<>();
            for(Lesson x: ll)
            {
                LessonMapper lessonmapper = new LessonMapper();
                lldto.add(lessonmapper.mapLessontoDTO(x));
            }
            sadto.setLessonList(lldto);
           return sadto;
        }
              
}
