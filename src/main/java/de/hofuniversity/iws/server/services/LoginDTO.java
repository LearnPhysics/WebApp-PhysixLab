/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.server.services;

import java.io.Serializable;

import de.hofuniversity.iws.server.data.entities.User;

/**
 *
 * @author Daniel Heinrich <dannynullzwo@gmail.com>
 */
public class LoginDTO implements Serializable {

    public final User user;
    public final String token;

    public LoginDTO(User user, String token) {
        this.user = user;
        this.token = token;
    }
}
