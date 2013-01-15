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
package de.hofuniversity.iws.server.services;

import java.sql.Timestamp;

import com.google.common.base.Optional;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import de.hofuniversity.iws.server.data.entities.*;
import de.hofuniversity.iws.server.data.handler.*;
import de.hofuniversity.iws.shared.CollectionUtils;
import de.hofuniversity.iws.shared.CollectionUtils.Selector;
import de.hofuniversity.iws.shared.dto.User;
import de.hofuniversity.iws.shared.services.UserService;

import static de.hofuniversity.iws.server.services.LoginServiceImpl.USER_ATTRIBUTE;
import static de.hofuniversity.iws.server.services.LoginServiceImpl.getSessionAttribute;

/**
 *
 * @author Andreas Arndt <andreas.arndt@hof-university.de>
 */
@RemoteServiceRelativePath("user")
public class UserServiceImpl extends RemoteServiceServlet implements UserService {

    @Override
    public Iterable<? extends User> getFriends() {
        return CollectionUtils.select(getSessionUser().getFriends(), new Selector<UserDBO, User>() {

            @Override
            public User select(UserDBO e) throws Exception {
                return e.getDTO();
            }
        });
    }

    @Override
    public void addGameResult(String game, int points) {
        GameResultDBO result = new GameResultDBO();
        result.setDate(new Timestamp(System.currentTimeMillis()));
        result.setUser(getSessionUser());
        result.setPoints(points);
        result.setGame(GameHandler.getGameEntity(game, false));
        
        GameResultHandler.store(result);
    }
    
    @Override
    public void addTestResult(String name,String subject, int points) {
        LessonProgressDBO result = new LessonProgressDBO();
        result.setDate(new Timestamp(System.currentTimeMillis()));
        result.setUser(getSessionUser());
        result.setPoints(1);        
        LessonDBO lesson = LessonHandler.getGameEntityOrCreateTemplate(name, false);  
      //  result.setLesson(lesson);
        LessonProgressHandler.store(result);        
    }

    private UserDBO getSessionUser() {
        Optional<UserDBO> user = getSessionAttribute(getThreadLocalRequest(), USER_ATTRIBUTE);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new RuntimeException("No loged in user!");
        }
    }
}
