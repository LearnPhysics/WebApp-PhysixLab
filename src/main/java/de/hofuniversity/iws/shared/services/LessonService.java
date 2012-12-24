/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.shared.services;

import com.google.common.base.Optional;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import de.hofuniversity.iws.shared.dto.LektionDTO;
import de.hofuniversity.iws.shared.dto.ThemaDTO;
import java.util.List;

/**
 *
 * @author Andreas
 */
@RemoteServiceRelativePath("lessonservice")
public interface LessonService extends RemoteService {
    public List<ThemaDTO> readThemes();
    public List<LektionDTO> readLessons(String topic_name);
}
