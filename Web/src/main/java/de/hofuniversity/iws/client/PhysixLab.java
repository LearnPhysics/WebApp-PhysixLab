/*
  * Copyright (C) 2012 Daniel Heinrich
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
package de.hofuniversity.iws.client;

import com.google.common.base.Optional;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.rpc.AsyncCallback;
import de.hofuniversity.iws.client.util.VoidCallback;
import de.hofuniversity.iws.client.widgets.LoginPage;
import de.hofuniversity.iws.client.widgets.UserHome.UserHome;
import de.hofuniversity.iws.shared.dto.*;
import de.hofuniversity.iws.shared.services.LoginServiceAsync;
import javax.inject.*;

/**
 *
 * @author Daniel Heinrich <dannynullzwo@gmail.com>
 */
@Singleton
public class PhysixLab {

    private LoginDTO loginData;
    @Inject private HistoryPageController pageControler;
    @Inject private LoginServiceAsync loginService;

    public void init() {
        History.addValueChangeHandler(pageControler);

        loginService.getLoginData(new AsyncCallback<Optional<LoginDTO>>() {
            @Override
            public void onFailure(Throwable caught) {
                pageControler.changePage(LoginPage.NAME);
            }

            @Override
            public void onSuccess(Optional<LoginDTO> result) {
                if (result.isPresent()) {
                    loginData = result.get();
                    if (History.getToken().isEmpty()) {
                        pageControler.changePage(UserHome.NAME);
                    } else {
                        pageControler.changePage(History.getToken());
                    }

                } else {
                    pageControler.changePage(LoginPage.NAME);
                }
            }
        });
    }

    public String getSessionToken() {
        if (loginData == null) {
            throw new RuntimeException("PhysixLab is uninitialized!");
        }
        return loginData.getToken();
    }

    public User getSessionUser() {
        if (loginData == null) {
            throw new RuntimeException("PhysixLab is uninitialized!");
        }
        return loginData.getUser();
    }

    public void setLoginData(LoginDTO ld) {
        loginData = ld;
    }

    public void logout() {
        setLoginData(null);
        pageControler.changePage(LoginPage.NAME);
        loginService.logout(new VoidCallback());
    }
}
