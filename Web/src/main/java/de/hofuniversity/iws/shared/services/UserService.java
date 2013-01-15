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

import com.google.gwt.user.client.rpc.*;
import de.hofuniversity.iws.shared.dto.User;

/**
 *
 * @author Andreas Arndt <andreas.arndt@hof-university.de> 
 */
@RemoteServiceRelativePath("user")
public interface UserService extends RemoteService {

    public Iterable<? extends User> getFriends();
    
    public void addGameResult(String game, int points);
    public void addTestResult(String lesson, String subject, int points);
}
