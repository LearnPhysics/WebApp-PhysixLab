/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.server.services;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;

import com.google.common.io.Files;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import de.hofuniversity.iws.shared.CollectionUtils.Selector;
import de.hofuniversity.iws.shared.dto.LessonPreview;
import de.hofuniversity.iws.shared.services.LessonService;
import org.json.*;

import static de.hofuniversity.iws.shared.CollectionUtils.asIterable;
import static de.hofuniversity.iws.shared.CollectionUtils.select;

/**
 *
 * @author Andreas
 */
@RemoteServiceRelativePath("lessonservice")
public class LessonServiceImpl extends RemoteServiceServlet implements LessonService {

    private static final Map<String, String> lessons = new HashMap<String, String>();
    private static final String SUBJECTS_PATH = "/Subjects/";
    private static final String LESSONS_PATH = "/Lessons/";

    private String getResourcePath() {
        return getServletContext().getRealPath(".");
    }

    @Override
    public String[] getSubjects() {
        File[] subjects = new File(getResourcePath() + SUBJECTS_PATH).listFiles();
        return select(asIterable(subjects), new Selector<File, String>() {
            @Override
            public String select(File e) throws IOException {
                return Files.toString(e, Charset.forName("UTF-8"));
            }
        }).toArray(new String[0]);
    }

    @Override
    public String getSubject(String name) throws IOException {
        File subject = new File(getResourcePath() + SUBJECTS_PATH + name + ".json");
        return Files.toString(subject, Charset.forName("UTF-8"));
    }

    @Override
    public LessonPreview[] getLessonPreviews(String subject) throws IOException {
        File[] subjects = new File(getResourcePath() + LESSONS_PATH).listFiles();
        List<String> lessonFiles = select(asIterable(subjects), new Selector<File, String>() {
            @Override
            public String select(File e) {
                return e.getName().split("\\.")[0];
            }
        });

        List<LessonPreview> previews = new ArrayList<LessonPreview>();
        for (String name : lessonFiles) {
            try {
                JSONObject a = new JSONObject(getLesson(name));
                if (subject.equals(a.optString("thema"))) {
                    previews.add(new LessonPreview(name, a.optString("parent"),
                                                   a.optString("image")));
                }
            } catch (JSONException ex) {
                //TODO logging
            }
        }
        return previews.toArray(new LessonPreview[previews.size()]);
    }

    @Override
    public String getLesson(String name) throws IOException {
        String json = lessons.get(name);
        if (json == null) {
            synchronized (lessons) {
                json = lessons.get(name);
                if (json == null) {
                    File file = new File(getResourcePath() + LESSONS_PATH + name + ".json");
                    json = Files.toString(file, Charset.forName("UTF-8"));
                    lessons.put(name, json);
                }
            }
        }
        return json;
    }

    @Override
    public String getGame(String name) throws IOException {
        for (String subject : getSubjects()) {
            try {
                JSONObject a = new JSONObject(getSubject(subject));
                JSONArray games = a.optJSONArray("games");
                for (int i = 0; i < games.length(); i++) {
                    JSONObject game = games.optJSONObject(i);
                    if (name.equals(game.optString("name"))) {
                        return game.toString();
                    }
                }
            } catch (JSONException ex) {
            }
        }
        return null;
    }
}
