/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.server.services;

import com.google.common.base.Optional;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import de.hofuniversity.iws.server.XMLReaderParser;
import de.hofuniversity.iws.shared.dto.*;
//import de.hofuniversity.iws.shared.dto.ThemaDTO;
//import de.hofuniversity.iws.shared.dto.ThemeDatabase;
import de.hofuniversity.iws.shared.services.LessonService;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.ServletException;

/**
 *
 * @author Andreas
 */
@RemoteServiceRelativePath("lessonservice")
public class LessonServiceImpl extends RemoteServiceServlet implements LessonService {
    XMLReaderParser xmlReaderParser = XMLReaderParser.getInstance();

    static boolean only_once = true;
    static int zahl = 0;
    String resourcePath = "";
    @Override
    public void init() throws ServletException
    {   
        super.init();
        resourcePath = getServletContext().getRealPath(".");
    }
    
    @Override
    public List<ThemaDTO> readThemes() {
        List<ThemaDTO> themenlist = new LinkedList<ThemaDTO>();
                File[] subDirs = xmlReaderParser.getPaths(resourcePath+"/Subjects");
                for (File subDir : subDirs) { 
                    ThemaDTO thema = xmlReaderParser.parseThemes(subDir.getPath()+"/theme.xml");
                    int lastindex = subDir.toString().lastIndexOf("\\");
                    thema.setTopic_name(subDir.toString().substring(lastindex+1));
                    themenlist.add(thema);
           //         System.out.println(subDir.getPath()+"/theme.xml");
           /*                   File[] f =  xmlReaderParser.getPaths(subDir.toString()); 
                                for (File s : f)
                                {
                                    
                                    File[] t = xmlReaderParser.getPaths(s.toString());
                                    for(File x : t)
                                    {
                                        LektionDTO lesson  = xmlReaderParser.parseLessons(x.getAbsoluteFile()+"/lesson.xml");
                                        lessonList = new LinkedList();
                                        lessonList.add(lesson);
                                        TestDTO test = xmlReaderParser.parseTest(x.getAbsoluteFile()+"/test.xml");
                                        testList = new LinkedList();
                                        testList.add(test);
                                    }
                                }*/
                } 
        return themenlist;
    }
    @Override 
    public List<LektionDTO> readLessons(String topic_name)
    {
        List<LektionDTO> lessonlist = new LinkedList<LektionDTO>();
                   File[] f =  xmlReaderParser.getPaths(resourcePath+"/Subjects/"+topic_name); 
                                for (File s : f)
                                {                                    
                                    File[] t = xmlReaderParser.getPaths(s.toString());
                                    for(File x : t)
                                    {
                                        LektionDTO lesson  = xmlReaderParser.parseLessons(x.getAbsoluteFile()+"/lesson.xml");
                                        int lastindex = x.toString().lastIndexOf("\\");
                                        lesson.setLesson_name(x.getAbsolutePath().toString().substring(lastindex+1));
                                        lessonlist.add(lesson);
                          /*              lessonList = new LinkedList();
                                        lessonList.add(lesson);*/
                           //             TestDTO test = xmlReaderParser.parseTest(x.getAbsoluteFile()+"/test.xml");
                           /*             testList = new LinkedList();
                                        testList.add(test);*/
                                    }
                                }       
        return lessonlist;
    }  

    
}
