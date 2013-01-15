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
package de.hofuniversity.iws.server.oauth.accessors;

import darwin.annotations.ServiceProvider;

import de.hofuniversity.iws.server.data.entities.*;
import de.hofuniversity.iws.server.oauth.Providers;
import org.json.*;
import org.scribe.model.Token;

import static de.hofuniversity.iws.server.oauth.OAuthProperties.ACCESSORS;

/**
 *
 * @author Andreas Arndt <andreas.arndt@hof-university.de>
 */
@ServiceProvider(UserDataAccessor.class)
public class GoogleUserData implements UserDataAccessor {

    private static final String USER_URL = ACCESSORS.getPropertie("GOOGLE_USER_URL");

    @Override
    public UserDBO getUserData(Token accessToken) throws AccessException {
        String response = Providers.GOOGLE.invokeGetRequest(accessToken, USER_URL);
        try {
            return parseUserJSON(response, accessToken);
        } catch (JSONException ex) {
            throw new AccessException(ex);
        }
    }

    private UserDBO parseUserJSON(String responceBody, Token accessToken) throws JSONException {
        JSONObject json = new JSONObject(responceBody);
        UserDBO user = new UserDBO();

        NetworkAccountDBO account = new NetworkAccountDBO();
        account.setNetworkName(Providers.GOOGLE.name());
        account.setOauthAccessSecret(accessToken.getSecret());
        account.setOauthAccessToken(accessToken.getToken());
        account.setUser(user);
        user.getNetworkAccountList().add(account);

        account.setAccountIdentificationString(json.optString("id"));

        if (json.has("displayName")) {
            user.setUserName(json.getString("displayName"));
        }

        JSONObject tmp = json.optJSONObject("image");
        if (tmp != null) {
            if (tmp.has("url")) {
                user.setUserPic(tmp.getString("url"));
            }
        }

        tmp = json.optJSONObject("name");
        if (tmp != null) {
            if (tmp.has("familyName")) {
                user.setLastName(tmp.getString("familyName"));
            }
            if (tmp.has("givenName")) {
                user.setFirstName(tmp.getString("givenName"));
            }
        }

        return user;
    }

    @Override
    public Providers supportedProvider() {
        return Providers.GOOGLE;
    }
}
