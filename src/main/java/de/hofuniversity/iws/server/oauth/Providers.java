/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.server.oauth;

import java.util.*;

import de.hofuniversity.iws.server.oauth.accessors.*;
import de.hofuniversity.iws.server.oauth.provider.*;

import com.google.common.base.Optional;
import org.scribe.builder.api.TwitterApi;
import org.scribe.model.*;

import static de.hofuniversity.iws.server.oauth.OAuthProperties.APP;

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
    private final static Set<Class<? extends Accessor>> discoverd = new HashSet<>();
    private final Map<Class<? extends Accessor>, Accessor> accessors = new IdentityHashMap<>();
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
        if (!discoverd.contains(c)) {
            discover(c);
        }
        return (Optional<T>) Optional.fromNullable(accessors.get(c));
    }

    private static <T extends Accessor> void discover(Class<T> c) {
        for (Accessor a : ServiceLoader.load(c)) {
            a.supportedProvider().accessors.put(c, a);
        }
        discoverd.add(c);
    }

    protected abstract OAuthProvider createProvider(String key, String secret);

    public String invokeGetRequest(Token accessToken, String url) {
        OAuthRequest request = new OAuthRequest(Verb.GET, url);

        provider.getService().signRequest(accessToken, request);
        return request.send().getBody();
    }
}
