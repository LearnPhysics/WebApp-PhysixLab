/*
 * Copyright (C) 2013 Daniel Heinrich <dannynullzwo@gmail.com>
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * (version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library.  If not, see <http://www.gnu.org/licenses/> 
 * or write to the Free Software Foundation, Inc., 51 Franklin Street,
 * Fifth Floor, Boston, MA 02110-1301  USA.
 */
package de.hofuniversity.iws.shared.services;

import java.io.IOException;
import java.lang.reflect.Method;

import com.google.gwt.core.client.*;
import com.google.gwt.user.server.rpc.AbstractRemoteServiceServlet;
import com.googlecode.gwt.test.*;
import com.googlecode.gwt.test.rpc.ServletMockProviderAdapter;
import com.googlecode.gwt.test.web.*;
import de.hofuniversity.iws.client.jsonbeans.*;
import de.hofuniversity.iws.server.services.LessonServiceImpl;
import de.hofuniversity.iws.shared.dto.LessonPreview;
import javax.servlet.ServletConfig;
import org.json.*;
import org.junit.*;
import org.junit.Test;

import static junit.framework.Assert.*;

/**
 *
 * @author Daniel Heinrich <dannynullzwo@gmail.com>
 */
@GwtModule("de.hofuniversity.iws.PhysixLabTest")
public class LessonServiceTest extends GwtTest {

    private LessonService service;

    @Before
    public void setup() {
//        final LessonServiceAsync async = GWT.create(LessonService.class);
//        service = SequentialAsyncCallback.makeSequential(async, LessonService.class);
        service = new LessonServiceImpl();

        MockServletContext context = new MockServletContext() {
            @Override
            public String getRealPath(String path) {
                return "src/main/webapp/" + path;
            }
        };
        final MockServletConfig mockConfig = new MockServletConfig(context);

        setServletMockProvider(new ServletMockProviderAdapter() {
            @Override
            public ServletConfig getMockedConfig(AbstractRemoteServiceServlet remoteService) {
                return mockConfig;
            }
        });
    }

    /**
     * Test of getSubjects method, of class LessonService.
     */
    @Test
    public void testGetSubjects() throws Exception {
        String[] subjects = service.getSubjects();
        assertNotNull(subjects);
        assertTrue(subjects.length == 3);

        for (String string : subjects) {
            checkJsonBean(string, SubjectJson.class);
        }
    }

    /**
     * Test of getSubject method, of class LessonService.
     */
    @Test
    public void testGetSubject() throws Exception {
        for (String string : service.getSubjects()) {
            JSONObject s = new JSONObject(string);
            String subject = service.getSubject(s.getString("name"));
            assertEquals(subject, string);
        }
    }

    /**
     * Test of getLessonPreviews method, of class LessonService.
     */
    @Test
    public void testGetLessonPreviews() throws IOException {
        for (String string : service.getSubjects()) {
            LessonPreview[] lessonPreviews = service.getLessonPreviews(string);
            assertNotNull(lessonPreviews);
        }
    }

    /**
     * Test of getGame method, of class LessonService.
     */
    @Test
    public void testGetGame() throws Exception {
        for (String subject : service.getSubjects()) {
            JSONObject s = new JSONObject(subject);
            JSONArray jsonArray = s.getJSONArray("games");
            for (int i = 0; i < jsonArray.length(); i++) {
                checkJsonBean(jsonArray.getJSONObject(i), GameJson.class);
            }
        }
    }

    /**
     * Test of getLesson method, of class LessonService.
     */
    @Test
    public void testGetLesson() throws Exception {
        for (String string : service.getSubjects()) {
            LessonPreview[] lessonPreviews = service.getLessonPreviews(string);
            for (LessonPreview lessonPreview : lessonPreviews) {
                assertEquals(lessonPreview.getParent(), string);
                String lesson = service.getLesson(lessonPreview.getName());
                checkJsonBean(lesson, LessonJson.class);
            }
        }
    }

    private void checkJsonBean(String bean, Class beanClass) throws JSONException {
        checkJsonBean(new JSONObject(bean), beanClass);
    }

    private void checkJsonBean(JSONObject bean, Class beanClass) throws JSONException {
        for (Method method : beanClass.getMethods()) {
            String name = method.getName();
            if (name.startsWith("get")
                && method.getParameterTypes().length == 0
                && !method.getName().equals("getClass")) {
                String attr = name.substring(3);
                Class r = method.getReturnType();
                if (r.isPrimitive()) {
                    if (r == Boolean.TYPE) {
                        assertNotNull(bean.optBoolean(attr));
                    } else if (r == Float.TYPE || r == Double.TYPE) {
                        assertNotNull(bean.optDouble(attr));
                    } else if (r == Long.TYPE) {
                        assertNotNull(bean.optLong(attr));
                    } else if (r == Integer.TYPE) {
                        assertNotNull(bean.optInt(attr));
                    }
                } else if (r == String.class) {
                    assertNotNull(bean.optString(attr));
                } else if (r == JsArray.class) {
                    assertNotNull(bean.optJSONArray(attr));
                } else if (JavaScriptObject.class.isAssignableFrom(r)) {
                    assertNotNull(bean.optJSONObject(attr));
                }
            }
        }
    }
}
