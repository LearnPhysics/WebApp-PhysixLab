/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.shared.services;

import java.io.IOException;
import java.util.List;

import de.hofuniversity.iws.shared.dto.*;

import com.google.gwt.user.client.rpc.*;

/**
 *
 * @author Andreas
 */
@RemoteServiceRelativePath("lessonservice")
public interface LessonService extends RemoteService {

    public List<String> getSubjects();
    
    public String getSubject(String name) throws IOException;

    public List<LessonPreview> getLessonPreviews(String subject) throws IOException;
    
    public List<GameDTO> getGamesForSession(String subject);

    public String getLesson(String name) throws IOException;
}
