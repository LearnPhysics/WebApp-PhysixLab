/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.server.oauth.accessors;

import darwin.annotations.ServiceProvider;

import de.hofuniversity.iws.server.data.entities.*;
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
public class FacebookUserData implements UserDataAccessor {

    private static final String USER_URL = ACCESSORS.getPropertie("FACEBOOK_USER_URL");

    @Override
    public User getUserData(Token accessToken) throws AccessException {
        String requestUrl = USER_URL + "?access_token=" + accessToken.getToken();
        String respons = Providers.FACEBOOK.invokeGetRequest(accessToken, requestUrl);
        try {
            return parseUserJSON(respons, accessToken);
        } catch (JSONException ex) {
            throw new AccessException(ex);
        }
    }

    private User parseUserJSON(String responceBody, Token accessToken) throws JSONException {
        JSONObject json = new JSONObject(responceBody);

        User user = new User();

        if (json.has("name")) {
            user.setUserName(json.getString("name"));
        }

        if (json.has("last_name")) {
            user.setLastName(json.getString("last_name"));
        }

        if (json.has("first_name")) {
            user.setFirstName(json.getString("first_name"));
        }

        JSONObject tmp = json.optJSONObject("hometown");
        if (tmp != null) {
            if (tmp.has("name")) {
                user.setCity(tmp.getString("name"));
            }
        }

        if (json.has("id")) {
            String id = json.getString("id");
            Optional<NetworkAccount> na = user.getNetworkAccount(Providers.FACEBOOK);
            if(na.isPresent())
            {
                na.get().setAccountIdentificationString(id);
            }
            user.setUserPic("https://graph.facebook.com/" + id + "/picture&type=normal");
        }

        return user;
    }

    @Override
    public Providers supportedProvider() {
        return Providers.FACEBOOK;
    }
}
