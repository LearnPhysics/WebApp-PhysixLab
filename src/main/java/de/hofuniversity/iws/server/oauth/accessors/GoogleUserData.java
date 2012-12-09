/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.server.oauth.accessors;

import darwin.annotations.ServiceProvider;

import de.hofuniversity.iws.server.data.entities.User;
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
    public User getUserData(Token accessToken) throws AccessException {
        String response = Providers.GOOGLE.invokeGetRequest(accessToken, USER_URL);
        try {
            return parseUserJSON(response);
        } catch (JSONException ex) {
            throw new AccessException(ex);
        }
    }

    private User parseUserJSON(String responceBody) throws JSONException {
        JSONObject json = new JSONObject(responceBody);
        User user = new User();
        if (json.has("id")) {
            user.setAccountIdentificationString(json.optString("id"));
        }

        if (json.has("displayName")) {
            user.setUserName(json.optString("displayName"));
        }

        JSONObject tmp = json.optJSONObject("image");
        if (tmp != null) {
            if (tmp.has("url")) {
                user.setUserPic(tmp.optString("url"));
            }
        }

        tmp = json.optJSONObject("name");
        if (tmp != null) {
            if (tmp.has("familyName")) {
                user.setLastName(tmp.optString("familyName"));
            }
            if (tmp.has("givenName")) {
                user.setFirstName(tmp.optString("givenName"));
            }
        }

        return user;
    }

    @Override
    public Providers supportedProvider() {
        return Providers.GOOGLE;
    }
}
