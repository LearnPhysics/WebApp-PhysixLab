/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.server.oauth.accessors;

/**
 *
 * @author Daniel Heinrich <dannynullzwo@gmail.com>
 */
public class AccessException extends Exception {

    public AccessException(Throwable cause) {
        super(cause);
    }

    public AccessException(String message) {
        super(message);
    }

    public AccessException(String message, Throwable cause) {
        super(message, cause);
    }
}
