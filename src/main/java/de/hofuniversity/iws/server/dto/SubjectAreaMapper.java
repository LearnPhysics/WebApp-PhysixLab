/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.server.dto;


import de.hofuniversity.iws.server.data.entities.SubjectAreaDBO;
import de.hofuniversity.iws.server.data.entities.LessonDBO;
import java.util.*;

import de.hofuniversity.iws.shared.dto.*;

/**
 *
 * @author User
 */
public class SubjectAreaMapper {
        public SubjectAreaDTO mapSubjectAreatoDTO(SubjectAreaDBO u)
        {
           SubjectAreaDTO sadto = new SubjectAreaDTO();
           sadto.setName(u.getName());
           
           List<LessonDBO> l = u.getLessonList();
           if(l!=null)
           {
            List<LessonDBO> ll = new ArrayList<LessonDBO>(l);
            List<LessonDTO> lldto = new ArrayList<LessonDTO>();
            for(LessonDBO x: ll)
            {
                LessonMapper lessonmapper = new LessonMapper();
                lldto.add(lessonmapper.mapLessontoDTO(x));
            }
            sadto.setLessonList(lldto);
           }
           return sadto;
        }
              
}
