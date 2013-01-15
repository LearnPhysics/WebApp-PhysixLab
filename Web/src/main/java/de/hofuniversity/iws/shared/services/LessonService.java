/*
  * Copyright (C) 2012 Andreas Arndt
  *
  * This program is free software: you can redistribute it and/or modify
  * it under the terms of the GNU General Public License as published by
  * the Free Software Foundation, either version 3 of the License, or
  * (at your option) any later version.
  *
  * This program is distributed in the hope that it will be useful,
  * but WITHOUT ANY WARRANTY; without even the implied warranty of
  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  * GNU General Public License for more details.
  *
  * You should have received a copy of the GNU General Public License
  * along with this program.  If not, see <http://www.gnu.org/licenses/>.
  */
package de.hofuniversity.iws.shared.services;

import java.io.IOException;

import com.google.gwt.user.client.rpc.*;
import de.hofuniversity.iws.shared.dto.LessonPreview;

/**
 *
 * @author Andreas Arndt <andreas.arndt@hof-university.de>
 */
@RemoteServiceRelativePath("lessonservice")
public interface LessonService extends RemoteService {

    public String[] getSubjects() throws IOException;
    
    public String getSubject(String name) throws IOException;

    public LessonPreview[] getLessonPreviews(String subject) throws IOException;
    
    public String getGame(String name) throws IOException;

    public String getLesson(String name) throws IOException;
}
