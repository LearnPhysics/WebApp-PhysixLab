/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.server.login;

import java.util.*;

import de.hofuniversity.iws.shared.dto.SessionDTO;

/**
 *
 * @author some
 */
public class Session {

    private final static int VALID_SESSION_TIMESPAN = 1; //in  days
    private final UUID uuid;
    private Date expire;
    private final String createrAddress;

    public Session(String createrAddress) {
        this(UUID.randomUUID(), createExpireDate(), createrAddress);
    }

    public static Date createExpireDate() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, VALID_SESSION_TIMESPAN);
        return c.getTime();
    }

    public Session(UUID uuid, Date expire, String createrAddress) {
        this.uuid = uuid;
        this.expire = expire;
        this.createrAddress = createrAddress;
    }

    public String getCreaterAddress() {
        return createrAddress;
    }

    public void updateExpireDate() {
        expire = createExpireDate();
    }

    public Date getExpire() {
        return expire;
    }

    public UUID getUUID() {
        return uuid;
    }

    public SessionDTO createDTO() {
        return new SessionDTO(uuid.toString(), expire);
    }

    public boolean isValid(String address) {
        return createrAddress.equals(address)
               && expire.after(Calendar.getInstance().getTime());
    }

    public boolean isSame(String sessionID) {
        return uuid.compareTo(UUID.fromString(sessionID)) == 0;
    }
}
