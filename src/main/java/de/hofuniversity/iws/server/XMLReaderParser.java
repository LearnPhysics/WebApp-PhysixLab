/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.server;

import java.io.*;
import java.util.List;

import de.hofuniversity.iws.shared.dto.*;

import org.jdom.*;
import org.jdom.input.SAXBuilder;

/**
 *
 * @author Andreas
 */
public class XMLReaderParser {

    private Element readFile(File f) throws IOException {
        try {
            Document doc = new SAXBuilder().build(f);
            return doc.getRootElement();
        } catch (JDOMException ex) {
            throw new IOException("XML file could not be parsed: " + f);
        }
    }

    public ThemaDTO parseThemes(File file) throws IOException {
        Element root = readFile(file);
        ThemaDTO theme = new ThemaDTO();

        List h = root.getChildren("headline");
        Element column = (Element) h.get(0);
        theme.setTitle(column.getText());

        h = root.getChildren("text");
        column = (Element) h.get(0);
        theme.setDescription(column.getText());

        h = root.getChildren("image");
        column = (Element) h.get(0);
        theme.setImageURL(column.getText());

        return theme;
    }

    public LektionDTO parseLessons(File file) throws IOException {
        Element root = readFile(file);
        LektionDTO lektion = new LektionDTO();
        lektion.setTitle(root.getChildText("title"));
        lektion.setLessonText(root.getChildText("lessontext"));
        lektion.setPreviewURL(root.getChildText("pictogram"));
        lektion.setParent(root.getChildText("parentlesson"));
        lektion.setId(root.getChildText("id"));
        lektion.setFormular(root.getChildText("formular"));
        lektion.setWidget(root.getChildText("widget"));

        return lektion;
    }

    public TestDTO parseTest(File file) throws IOException {
        Element root = readFile(file);
        TestDTO test = new TestDTO();

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

        return test;
    }
}
