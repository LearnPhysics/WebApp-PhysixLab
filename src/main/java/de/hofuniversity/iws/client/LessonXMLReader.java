package de.hofuniversity.iws.client;
/**
 *
 * @author Andreas Arndt
 */
public class LessonXMLReader {
    
    private static LessonXMLReader instance = null;
    
    private LessonXMLReader() {}
    
    
    public static LessonXMLReader getInstance() {
        if (instance == null) {
            synchronized(LessonXMLReader.class) {
                instance = new LessonXMLReader();
            }
        }
        return instance;
    }
    
    public String readXML(String url)
    {
        return "";
    }
   
}
