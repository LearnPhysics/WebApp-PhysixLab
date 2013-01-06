/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.server.services;

import java.sql.Timestamp;

import com.google.common.base.Optional;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import de.hofuniversity.iws.server.data.handler.*;
import de.hofuniversity.iws.shared.entityimpl.*;
import de.hofuniversity.iws.shared.entitys.User;
import de.hofuniversity.iws.shared.services.UserService;

import static de.hofuniversity.iws.server.services.LoginServiceImpl.USER_ATTRIBUTE;
import static de.hofuniversity.iws.server.services.LoginServiceImpl.getSessionAttribute;

/**
 *
 * @author UserDBO
 */
@RemoteServiceRelativePath("user")
public class UserServiceImpl extends RemoteServiceServlet implements UserService {

    @Override
    public Iterable<? extends User> getFriends() {
        return getSessionUser().getFriends();
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

    private UserDBO getSessionUser() {
        Optional<UserDBO> user = getSessionAttribute(getThreadLocalRequest(), USER_ATTRIBUTE);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new RuntimeException("No loged in user!");
        }
    }
}
