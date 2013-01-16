/*
  * Copyright (C) 2012 Andreas Arndt
  *
  * This program is free software: you can redistribute it and/or modify
  * it under the terms of the GNU General Public License as published by
  * the Free Software Foundation, either version 3 of the License, or
  * (at your option) any later version.
  *
  * This program is distributed in the hope that it will be useful,
  * but WITHOUT ANY WARRANTY; without even the implied warranty of
  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  * GNU General Public License for more details.
  *
  * You should have received a copy of the GNU General Public License
  * along with this program.  If not, see <http://www.gnu.org/licenses/>.
  */
package de.hofuniversity.iws.server.oauth;

import java.util.*;

import com.google.common.base.Optional;
import de.hofuniversity.iws.server.oauth.accessors.*;
import de.hofuniversity.iws.server.oauth.provider.*;
import org.scribe.builder.api.TwitterApi;
import org.scribe.model.*;

import static de.hofuniversity.iws.server.oauth.OAuthProperties.APP;

/**
 *
 * @author Andreas Arndt <andreas.arndt@hof-university.de>
 * @author Daniel Heinrich <dannynullzwo@gmail.com>
 */

public enum Providers {

    GOOGLE {
        @Override
        protected OAuthProvider createProvider(String key, String secret) {
            return new GoogleProvider(key, secret);
        }

        @Override
        public UserDataAccessor getUserDataAccessor() {
            return new GoogleUserData();
        }
    },
    TWITTER {
        @Override
        protected OAuthProvider createProvider(String key, String secret) {
            return new OAuthProvider(key, secret, TwitterApi.class);
        }

        @Override
        public UserDataAccessor getUserDataAccessor() {
            return new TwitterAccessor();
        }
    },
    FACEBOOK {
        @Override
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
