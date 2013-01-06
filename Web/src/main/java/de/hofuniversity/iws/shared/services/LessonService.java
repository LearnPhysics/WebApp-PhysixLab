/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.shared.services;

import java.io.IOException;

import com.google.gwt.user.client.rpc.*;
import de.hofuniversity.iws.shared.dto.*;

/**
 *
 * @author Andreas
 */
@RemoteServiceRelativePath("lessonservice")
public interface LessonService extends RemoteService {

    public String[] getSubjects() throws IOException;
    
    public String getSubject(String name) throws IOException;

    public LessonPreview[] getLessonPreviews(String subject) throws IOException;
    
    public String getGame(String name) throws IOException;

    public String getLesson(String name) throws IOException;
}
