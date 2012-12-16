package de.hofuniversity.iws.server.oauth;

/**
 *
 * @author Andreas Arndt <andreas.arndt@hof-university.de>
 */
public class OAuthLogin {
    public boolean successfull;
    public final Providers provider;
    public final OAuthAccessRequest request;

    public OAuthLogin(Providers provider, OAuthAccessRequest request) {
        this.provider = provider;
        this.request = request;
    }
}
