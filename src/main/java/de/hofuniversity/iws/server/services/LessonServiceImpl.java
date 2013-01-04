/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.server.services;

import java.io.*;
import java.nio.file.Path;
import java.util.*;

import de.hofuniversity.iws.server.XMLReaderParser;
import de.hofuniversity.iws.shared.dto.*;
import de.hofuniversity.iws.shared.services.LessonService;

import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 *
 * @author Andreas
 */
@RemoteServiceRelativePath("lessonservice")
public class LessonServiceImpl extends RemoteServiceServlet implements LessonService {
    
    private String getResourcePath()
    {
        return getServletContext().getRealPath(".");
    }

    @Override
    public List<ThemaDTO> readThemes() {
        File themenDir = new File(getResourcePath() + "/Subjects/");
        List<ThemaDTO> themenlist = new LinkedList<ThemaDTO>();
        XMLReaderParser parser = new XMLReaderParser();

        for (File themaFile : getSubDirs(themenDir)) {
            try {
                Path p = themaFile.toPath();
                ThemaDTO thema = parser.parseThemes(p.resolve("theme.xml").toFile());
                thema.setTopicName(p.getFileName().toString());
                themenlist.add(thema);
            } catch (IOException ex) {
                //TODO log
            }
        }
        return themenlist;
    }

    @Override
    public List<LektionDTO> readLessons(String topicName) {
        File topicLessonsDir = new File(getResourcePath() + "/Subjects/" + topicName + "/lessons/");
        List<LektionDTO> lessonlist = new LinkedList<LektionDTO>();
        XMLReaderParser parser = new XMLReaderParser();

        for (File lessonDir : getSubDirs(topicLessonsDir)) {
            try {
                Path p = lessonDir.toPath();
                LektionDTO lesson = parser.parseLessons(p.resolve("lesson.xml").toFile());
                lesson.setLesson_name(p.getFileName().toString());
                lessonlist.add(lesson);
            } catch (IOException ex) {
                //TODO log
            }
        }

        return lessonlist;
    }

    private static File[] getSubDirs(File path) {
        if (!path.isDirectory()) {
            return new File[0];
        }

        File[] subDirs = path.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory();
            }
        });
        return subDirs;
    }
}
