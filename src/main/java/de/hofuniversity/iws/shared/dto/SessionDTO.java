/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.shared.dto;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author some
 */
public class SessionDTO implements Serializable {

    private final String sessionID;
    private final Date expireDate;

    public SessionDTO(String sessionID, Date expireDate) {
        this.sessionID = sessionID;
        this.expireDate = expireDate;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public String getSessionID() {
        return sessionID;
    }
}
