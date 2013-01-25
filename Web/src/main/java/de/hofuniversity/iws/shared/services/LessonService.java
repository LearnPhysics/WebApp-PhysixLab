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

    /**
     * @return 
     * json strings of all subjects which can be found on the server
     */
    public String[] getSubjects();

    /**
     *
     * @param name
     * name of the subject to select
     * @return
     * json string of the selected subject
     * @throws IOException
     * if no subject for the name exists
     */
    public String getSubject(String name) throws IOException;

    /**
     *
     * @param subject
     * @return
     * preview data of all lessons of a subject
     * @throws IOException
     * if the named subject can't be found
     */
    public LessonPreview[] getLessonPreviews(String subject) throws IOException;

    /**
     *
     * @param name
     * name of the selected game
     * @return
     * json string of the selected game
     * @throws IOException
     * if no game for the name exists
     */
    public String getGame(String name) throws IOException;

    /**
     *
     * @param name
     * name of the selected lesson
     * @return
     * json string of the selected lesson
     * @throws IOException
     * if no lesson exists with the given name
     */
    public String getLesson(String name) throws IOException;
}
