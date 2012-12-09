/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.server.oauth.accessors;

import java.util.ArrayList;

import darwin.annotations.ServiceProvider;

import de.hofuniversity.iws.server.data.entities.User;
import de.hofuniversity.iws.server.oauth.Providers;

import org.json.*;
import org.scribe.model.Token;

import static de.hofuniversity.iws.server.oauth.OAuthProperties.ACCESSORS;

/**
 *
 * @author Daniel Heinrich <dannynullzwo@gmail.com>
 */
@ServiceProvider(FriendListAccessor.class)
public class FacebookFriends implements FriendListAccessor {

    private static final String FRIENDS_ACCESS_URL = ACCESSORS.getPropertie("FACEBOOK_FRIENDS_URL");

    @Override
    public Iterable<User> getFriends(Token accessToken, User currentUser) throws AccessException {
        String requestURL = FRIENDS_ACCESS_URL + "?access_token=" + accessToken.getToken();
        String response = Providers.FACEBOOK.invokeGetRequest(accessToken, requestURL);
        try {
            return parseFriendsJSON(response);
        } catch (JSONException ex) {
            throw new AccessException(ex);
        }
    }

    private Iterable<User> parseFriendsJSON(String response) throws JSONException {
        JSONObject json = new JSONObject(response);
        ArrayList<User> friends = new ArrayList<>();
        JSONArray getArray = json.optJSONArray("data");
        for (int i = 0; i < getArray.length(); i++) {
            User tmpUser = new User();
            JSONObject objectInArray = getArray.optJSONObject(i);
            tmpUser.setUserName(objectInArray.optString("name"));
            tmpUser.setAccountIdentificationString(objectInArray.optString("id"));
            tmpUser.setUserPic("https://graph.facebook.com/" + objectInArray.optString("id") + "/picture&type=normal");
            friends.add(tmpUser);
        }
        return friends;
    }

    @Override
    public Providers supportedProvider() {
        return Providers.FACEBOOK;
    }
}
