/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.server.oauth.accessors;

import de.hofuniversity.iws.shared.entityimpl.UserDBO;
import de.hofuniversity.iws.shared.entityimpl.NetworkAccountDBO;
import darwin.annotations.ServiceProvider;

import de.hofuniversity.iws.server.data.handler.UserHandler;
import de.hofuniversity.iws.server.oauth.Providers;

import com.google.common.base.Optional;
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
            return parseUserJSON(response);
        } catch (JSONException ex) {
            throw new AccessException(ex);
        }
    }

    private UserDBO parseUserJSON(String responceBody) throws JSONException {
        JSONObject json = new JSONObject(responceBody);
        UserDBO user = new UserDBO();
        if (json.has("id")) {
            Optional<NetworkAccountDBO> na = UserHandler.getNetworkAccount(user, Providers.GOOGLE);
            if(na.isPresent())
            {
                na.get().setAccountIdentificationString(json.getString("id"));
            }
        }

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
