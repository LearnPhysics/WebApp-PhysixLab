package de.hofuniversity.iws.server.oauth;

import de.hofuniversity.iws.server.data.entities.User;

/**
 *
 * @author Andreas Arndt <andreas.arndt@hof-university.de>
 */
public class OAuthLogin {

    public User user;
    public boolean successfull;
    public final OAuthAccessRequest request;

    public OAuthLogin(OAuthAccessRequest request) {
        this.request = request;
    }
}
