/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.server.oauth;

import de.hofuniversity.iws.server.oauth.provider.GoogleProvider;
import de.hofuniversity.iws.server.oauth.provider.FacebookProvider;
import de.hofuniversity.iws.server.oauth.provider.OAuthProvider;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.scribe.builder.api.GoogleApi;
import org.scribe.builder.api.TwitterApi;

/**
 *
 * @author Daniel Heinrich <dannynullzwo@gmail.com>
 */
public enum Providers {

    GOOGLE {
        protected OAuthProvider createProvider(String key, String secret) {
            return new GoogleProvider(key, secret);
        }
    },
    TWITTER {
        protected OAuthProvider createProvider(String key, String secret) {
            return new OAuthProvider(key, secret, TwitterApi.class);
        }
    },
    FACEBOOK {
        protected OAuthProvider createProvider(String key, String secret) {
            return new FacebookProvider(key, secret);
        }
    };
    private final OAuthProvider provider;

    private Providers() {
        String key = getProp(name() + ".Key");
        String secret = getProp(name() + ".Secret");
        this.provider = createProvider(key, secret);
    }

    public OAuthProvider getProvider() {
        return provider;
    }

    protected abstract OAuthProvider createProvider(String key, String secret);

    public static String getProp(String name)
    {
        return Static.properties.getProperty(name);
    }
    
    private static class Static {

        private static final String PROPERTIE_FILE = "/META-INF/oauth.properties";
        private static final Properties properties = new Properties();

        static {
            try {
                properties.load(Providers.class.getResourceAsStream(PROPERTIE_FILE));
            } catch (IOException ex) {
                throw new RuntimeException("OAuth Configuration can't be read!");
            }
        }
    }
}
