/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.server;

import com.google.common.base.Optional;
import com.google.gwt.xml.client.XMLParser;
import de.hofuniversity.iws.shared.dto.LektionDTO;
import de.hofuniversity.iws.shared.dto.TestDTO;

import de.hofuniversity.iws.shared.dto.ThemaDTO;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import org.w3c.flute.util.Encoding;

/**
 *
 * @author Andreas
 */
public class XMLReaderParser {
    private static XMLReaderParser mySingelton = null;
    String xml = "";
 
    private XMLReaderParser(){
    }
 
    public static XMLReaderParser getInstance(){
        if (mySingelton == null) {
            mySingelton = new XMLReaderParser();
        }
        return mySingelton;
    }    
   
    public File[] getPaths(String path)
    {
        File dir = new File(path);
        File[] subDirs = dir.listFiles(new FileFilter() {  
            @Override
            public boolean accept(File pathname) {
                    return pathname.isDirectory();  
            }  
        });  
        return subDirs;
    }
    
    
    
    private String readFile(String f) throws FileNotFoundException
    {
            StringBuilder text = new StringBuilder();
            String NL = System.getProperty("line.separator");
            Scanner scanner = null;
                    scanner = new Scanner(new FileInputStream(f),Encoding.getJavaEncoding("UTF-8"));

            try {
              while (scanner.hasNextLine()){
                  text.append(scanner.nextLine()).append(NL);
              }
            }
            finally{
              scanner.close();
            }
            return text.toString();
    }
    
    private Document JDOMParser(String xml)
    {
       SAXBuilder sb = new SAXBuilder();
            StringReader sr = new StringReader(xml);
            Document doc = null;
        try {
            doc = sb.build(sr);
        } catch (JDOMException ex) {
            Logger.getLogger(XMLReaderParser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(XMLReaderParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return doc;
    }
    
    
    private void parseThema(String xml,ThemaDTO theme) 
    {
            Document doc = JDOMParser(xml);
            Element root = doc.getRootElement();
            List h = root.getChildren("headline");
                    Element column = (Element) h.get(0);
                    theme.setTitle(column.getText());
      
            h = root.getChildren("text");
                    column = (Element) h.get(0);
                    theme.setDescription(column.getText());

            h = root.getChildren("image");
                    column = (Element) h.get(0);
                    theme.setImageURL(column.getText());
    }
    
    private void parseLesson(String xml, LektionDTO lektion)
    {
            Document doc = JDOMParser(xml);
            Element root = doc.getRootElement();
            List h = root.getChildren("title");
                    Element column = (Element) h.get(0);
                    lektion.setTitle(column.getText());     
            h = root.getChildren("lessontext");
                    column = (Element) h.get(0);
                    lektion.setLessonText(column.getText());
            h = root.getChildren("pictogram");
                    column = (Element) h.get(0);
                    lektion.setPreviewURL(column.getText());  
             h = root.getChildren("parentlesson");
                    column = (Element) h.get(0);
                    lektion.setParent(column.getText());
             h = root.getChildren("id");
                    column = (Element) h.get(0);
                    lektion.setId(column.getText());
              h = root.getChildren("widget");
                    column = (Element) h.get(0);
                    lektion.setWidget(column.getText());
              h = root.getChildren("math");
                    column = (Element) h.get(0);
                    lektion.setFormular(column.getText());
    }
    
    public ThemaDTO parseThemes(String file) 
    {   
        ThemaDTO thema = new ThemaDTO();
        try {
            xml = readFile(file);
        } catch (FileNotFoundException ex) {
          //  Logger.getLogger(XMLReaderParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        parseThema(xml,thema);
        return thema;
    }     
    
    public LektionDTO parseLessons(String file)
    {
        LektionDTO lesson = new LektionDTO();
        try {
           xml = readFile(file);
        } catch (FileNotFoundException ex) {
         //   Logger.getLogger(XMLReaderParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        parseLesson(xml,lesson);
        return lesson;
    }
    public TestDTO parseTest(String file)
    {   
        TestDTO test = new TestDTO();
         try {
           xml = readFile(file);
        } catch (FileNotFoundException ex) {
         //   Logger.getLogger(XMLReaderParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        parseTests(xml,test);
        return test;
    }

    private void parseTests(String xml, TestDTO test) {
            Document doc = JDOMParser(xml);
            Element root = doc.getRootElement();
            List h = root.getChildren("title");
                    Element column = (Element) h.get(0);
                    test.setTitle(column.getText());
      
            h = root.getChildren("problem");
                    column = (Element) h.get(0);
                    test.setDescription(column.getText());

            h = root.getChildren("solution");
                    column = (Element) h.get(0);
                    test.setSolution(column.getText()); 
                    
             h = root.getChildren("illustration");
                    column = (Element) h.get(0);
                    test.setIllustration(column.getText()); 
    }
}
