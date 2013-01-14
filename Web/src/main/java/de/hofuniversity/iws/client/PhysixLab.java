/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client;

import com.google.common.base.Optional;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.rpc.AsyncCallback;
import de.hofuniversity.iws.client.util.*;
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
    private final HistoryPageController pageControler;
    private final LoginServiceAsync loginService;

    @Inject
    public PhysixLab(HistoryPageController pageControler, LoginServiceAsync loginService) {
        this.pageControler = pageControler;
        this.loginService = loginService;
    }

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
