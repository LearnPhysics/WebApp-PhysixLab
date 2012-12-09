/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.server.oauth.accessors;

import java.util.*;

import darwin.annotations.MultiServiceProvider;
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
@MultiServiceProvider({UserDataAccessor.class, FriendListAccessor.class})
public class TwitterAccessor implements UserDataAccessor, FriendListAccessor {

    private static final String FRIENDS_ACCESS_URL = ACCESSORS.getPropertie("TWITTER_FOLLOWERS_URL");
    private static final String USER_BY_ID_URL = ACCESSORS.getPropertie("TWITTER_USER_BY_ID_URL");
    private static final String USER_URL = ACCESSORS.getPropertie("TWITTER_USER_URL");

    @Override
    public User getUserData(Token accessToken) throws AccessException {
        String response = Providers.TWITTER.invokeGetRequest(accessToken, USER_URL);
        try {
            return parseUser(response);
        } catch (JSONException ex) {
            throw new AccessException(ex);
        }
    }

    @Override
    public Iterable<User> getFriends(Token accessToken, User currentUser) throws AccessException {

        String requestUrl = FRIENDS_ACCESS_URL + currentUser.getAccountIdentificationString();

        String response = Providers.TWITTER.invokeGetRequest(accessToken, requestUrl);
        try {
            return parseFriends(response, accessToken);
        } catch (JSONException ex) {
            throw new AccessException(ex);
        }
    }

    private User parseUser(String responceBody) throws JSONException {
        JSONObject json = new JSONObject(responceBody);
        User user = new User();
        if (json.has("id")) {
            user.setAccountIdentificationString(json.optString("id"));
        }

        if (json.has("location")) {
            user.setCity(json.optString("location"));
        }

        if (json.has("profile_image_url")) {
            user.setUserPic(json.optString("profile_image_url"));
        }
        if (json.has("screen_name")) {
            user.setUserName(json.optString("screen_name"));
        }
        if (json.has("name")) {
            user.setUserName(json.optString("name"));
        }
        return user;
    }

    private Iterable<User> parseFriends(String response, Token accessToken) throws JSONException {
        JSONObject json = new JSONObject(response);
        List<User> friendsList = new ArrayList<>();

        JSONArray array = json.optJSONArray("ids");
        if (array != null) {
            for (int i = 0; i < array.length(); i++) {
                String element = array.optString(i);
                if (element != null) {
                    String requestUrl = USER_BY_ID_URL + element + "&include_entities=true";
                    String r = Providers.TWITTER.invokeGetRequest(accessToken, requestUrl);
                    friendsList.add(parseUser(r));
                }
            }
        }
        return friendsList;
    }

    @Override
    public Providers supportedProvider() {
        return Providers.TWITTER;
    }
}
