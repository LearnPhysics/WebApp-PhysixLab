/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.client;

import de.hofuniversity.iws.client.widgets.LoginPage;
import de.hofuniversity.iws.client.widgets.UserHome.UserHome;
import de.hofuniversity.iws.shared.dto.LoginDTO;
import de.hofuniversity.iws.shared.entitys.User;
import de.hofuniversity.iws.shared.services.LoginServiceAsync;

import com.google.common.base.Optional;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.rpc.AsyncCallback;
import javax.inject.Inject;

/**
 *
 * @author Daniel Heinrich <dannynullzwo@gmail.com>
 */
public class PhysixLab {

    public static final HistoryPageController PAGE_CONTROLLER = new HistoryPageController();
    private static LoginDTO loginData;
    @Inject
    private LoginServiceAsync loginService;

    public void init() {
        History.addValueChangeHandler(PAGE_CONTROLLER);
        
//        PlayNWidget w = new PlayNWidget(new KineticWars());
//        w.setWidth(800 + "px");
//        RootPanel.get().add(w);
        loginService.getLoginData(new AsyncCallback<Optional>() {
            @Override
            public void onFailure(Throwable caught) {
                PAGE_CONTROLLER.changePage(LoginPage.NAME);
            }

            @Override
            public void onSuccess(Optional result) {
                if (result.isPresent()) {
                    loginData = (LoginDTO) result.get();
                    if (History.getToken().isEmpty()) {
                        PAGE_CONTROLLER.changePage(UserHome.NAME);
                    } else {
                        PAGE_CONTROLLER.changePage(History.getToken());
                    }

                } else {
                    PAGE_CONTROLLER.changePage(LoginPage.NAME);
                }
            }
        });
    }

    public static String getSessionToken() {
        if (loginData == null) {
            throw new RuntimeException("PhysixLab is uninitialized!");
        }
        return loginData.getToken();
    }

    public static User getSessionUser() {
        if (loginData == null) {
            throw new RuntimeException("PhysixLab is uninitialized!");
        }
        return loginData.getUser();
    }

    public static void setLoginData(LoginDTO ld) {
        loginData = ld;
    }
}
