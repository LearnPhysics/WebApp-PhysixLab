/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.server.oauth.accessors;

import java.util.*;

import darwin.annotations.MultiServiceProvider;

import com.google.common.base.Optional;
import de.hofuniversity.iws.server.data.handler.UserHandler;
import de.hofuniversity.iws.server.oauth.Providers;
import de.hofuniversity.iws.shared.entityimpl.*;
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
    public UserDBO getUserData(Token accessToken) throws AccessException {
        String response = Providers.TWITTER.invokeGetRequest(accessToken, USER_URL);
        try {
            return parseUser(response, accessToken);
        } catch (JSONException ex) {
            throw new AccessException(ex);
        }
    }

    @Override
    public Iterable<UserDBO> getFriends(Token accessToken, UserDBO currentUser) throws AccessException {
        Optional<NetworkAccountDBO> na = UserHandler.getNetworkAccount(currentUser, Providers.TWITTER);
        if (!na.isPresent()) {
            return Collections.EMPTY_LIST;
        }

        String requestUrl = FRIENDS_ACCESS_URL + na.get().getAccountIdentificationString();

        String response = Providers.TWITTER.invokeGetRequest(accessToken, requestUrl);
        try {
            return parseFriends(response, accessToken);
        } catch (JSONException ex) {
            throw new AccessException(ex);
        }
    }

    private UserDBO parseUser(String responceBody, Token access) throws JSONException {
        JSONObject json = new JSONObject(responceBody);
        UserDBO user = new UserDBO();

        NetworkAccountDBO account = new NetworkAccountDBO();
        account.setNetworkName(Providers.TWITTER.name());
        if (access != null) {
            account.setOauthAccessSecret(access.getSecret());
            account.setOauthAccessToken(access.getToken());
        }
        account.setUser(user);
        user.getNetworkAccountList().add(account);

        account.setAccountIdentificationString(json.optString("id"));

        if (json.has("location")) {
            user.setCity(json.getString("location"));
        }

        if (json.has("profile_image_url")) {
            user.setUserPic(json.getString("profile_image_url"));
        }
        if (json.has("screen_name")) {
            user.setUserName(json.getString("screen_name"));
        }
        if (json.has("name")) {
            user.setUserName(json.getString("name"));
        }
        return user;
    }

    private Iterable<UserDBO> parseFriends(String response, Token accessToken) throws JSONException {
        JSONObject json = new JSONObject(response);
        List<UserDBO> friendsList = new ArrayList<UserDBO>();

        JSONArray array = json.optJSONArray("ids");
        if (array != null) {
            for (int i = 0; i < array.length(); i++) {
                String element = array.optString(i);
                if (element != null) {
                    String requestUrl = USER_BY_ID_URL + element + "&include_entities=true";
                    String r = Providers.TWITTER.invokeGetRequest(accessToken, requestUrl);
                    friendsList.add(parseUser(r, null));
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
