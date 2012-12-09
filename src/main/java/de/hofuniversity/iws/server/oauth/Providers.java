/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.server.oauth;

import java.io.IOException;
import java.util.*;

import de.hofuniversity.iws.server.oauth.accessors.Accessor;
import de.hofuniversity.iws.server.oauth.accessors.FriendListAccessor;
import de.hofuniversity.iws.server.oauth.accessors.UserDataAccessor;
import de.hofuniversity.iws.server.oauth.provider.*;

import com.google.common.base.Optional;
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
    private static final Class<? extends Accessor>[] ACCESSOR_TYPES = new Class[]{
        UserDataAccessor.class, FriendListAccessor.class
    };

    static {
        for (Class<? extends Accessor> ac : ACCESSOR_TYPES) {
            for (Accessor a : ServiceLoader.load(ac)) {
                a.supportedProvider().accessors.put(ac, a);
            }
        }
    }
    
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
    
    public <T extends Accessor> Optional<T> getAccessor(Class<T> c)
    {
        return (Optional<T>) Optional.fromNullable(accessors.get(c));
    }

    protected abstract OAuthProvider createProvider(String key, String secret);

    public String invokeGetRequest(Token accessToken, String url) {
        OAuthRequest request = new OAuthRequest(Verb.GET, url);

        provider.getService().signRequest(accessToken, request);
        return request.send().getBody();
    }
}
