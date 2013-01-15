package de.hofuniversity.iws;

import com.google.gwt.core.client.*;
import de.hofuniversity.iws.client.widgets.LoginPage;

import com.google.gwt.user.client.ui.RootPanel;

/*
  * Copyright (C) 2012 daniel
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
/**
 *
 * @author Daniel Heinrich <dannynullzwo@gmail.com>
 */
public class LoginModul implements EntryPoint {

//    private LoginPage login;

    public LoginModul() {
        TestInjector.INSTANCE.get().create("asd");
    }

    @Override
    public void onModuleLoad() {
//        RootPanel.get().add(login);
    }

//    public LoginPage getLogin() {
//        return login;
//    }
}
