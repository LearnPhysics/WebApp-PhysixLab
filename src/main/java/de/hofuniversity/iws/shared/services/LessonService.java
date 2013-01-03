/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.shared.services;

import java.util.List;

import de.hofuniversity.iws.shared.dto.*;

import com.google.gwt.user.client.rpc.*;

/**
 *
 * @author Andreas
 */
@RemoteServiceRelativePath("lessonservice")
public interface LessonService extends RemoteService {
    public List<ThemaDTO> readThemes();
    public List<LektionDTO> readLessons(String topic_name);
}
