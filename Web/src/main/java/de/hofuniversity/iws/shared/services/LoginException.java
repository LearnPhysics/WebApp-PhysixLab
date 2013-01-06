/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.shared.services;

import java.io.Serializable;

/**
 *
 * @author some
 */
public class LoginException extends Exception implements Serializable{

    public LoginException() {
    }

    public LoginException(String message) {
        super(message);
    }
}
