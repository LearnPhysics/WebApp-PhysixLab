/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.server.oauth;

import java.util.*;

import de.hofuniversity.iws.server.oauth.accessors.Accessor;
import de.hofuniversity.iws.server.oauth.accessors.UserDataAccessor;
import de.hofuniversity.iws.server.oauth.provider.*;

import com.google.common.base.Optional;
import de.hofuniversity.iws.server.oauth.accessors.FacebookUserData;
import de.hofuniversity.iws.server.oauth.accessors.GoogleUserData;
import de.hofuniversity.iws.server.oauth.accessors.TwitterAccessor;
import org.scribe.builder.api.TwitterApi;
import org.scribe.model.*;
import static de.hofuniversity.iws.server.oauth.OAuthProperties.*;

/**
 *
 * @author Daniel Heinrich <dannynullzwo@gmail.com>
 */
public enum Providers {

    GOOGLE {
        protected OAuthProvider createProvider(String key, String secret) {
            return new GoogleProvider(key, secret);
        }

        @Override
        public UserDataAccessor getUserDataAccessor() {
            return new GoogleUserData();
        }
    },
    TWITTER {
        protected OAuthProvider createProvider(String key, String secret) {
            return new OAuthProvider(key, secret, TwitterApi.class);
        }

        @Override
        public UserDataAccessor getUserDataAccessor() {
            return new TwitterAccessor();
        }
    },
    FACEBOOK {
        protected OAuthProvider createProvider(String key, String secret) {
            return new FacebookProvider(key, secret);
        }

        @Override
        public UserDataAccessor getUserDataAccessor() {
            return new FacebookUserData();
        }
    };
    private final static Set<Class> initialized = new HashSet<Class>();
    private final Map<Class<? extends Accessor>, Accessor> accessors = new IdentityHashMap<Class<? extends Accessor>, Accessor>();
    private final OAuthProvider provider;

    private Providers() {
        String key = APP.getPropertie(name() + ".Key");
        String secret = APP.getPropertie(name() + ".Secret");
        this.provider = createProvider(key, secret);
    }

    public OAuthProvider getProvider() {
        return provider;
    }

    public <T extends Accessor> Optional<T> getAccessor(Class<T> c) {
        if (!initialized.contains(c)) {
            for (Accessor a : ServiceLoader.load(c)) {
                a.supportedProvider().accessors.put(c, a);
            }
            initialized.add(c);
        }
        return (Optional<T>) Optional.fromNullable(accessors.get(c));
    }

    protected abstract OAuthProvider createProvider(String key, String secret);

    public abstract UserDataAccessor getUserDataAccessor();

    public String invokeGetRequest(Token accessToken, String url) {
        OAuthRequest request = new OAuthRequest(Verb.GET, url);

        provider.getService().signRequest(accessToken, request);
        return request.send().getBody();
    }
}
