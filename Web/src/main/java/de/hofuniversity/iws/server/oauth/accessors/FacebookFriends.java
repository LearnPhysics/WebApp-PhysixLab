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

import java.util.ArrayList;

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
@ServiceProvider(FriendListAccessor.class)
public class FacebookFriends implements FriendListAccessor {

    private static final String FRIENDS_ACCESS_URL = ACCESSORS.getPropertie("FACEBOOK_FRIENDS_URL");

    @Override
    public Iterable<UserDBO> getFriends(Token accessToken, UserDBO currentUser) throws AccessException {
        String requestURL = FRIENDS_ACCESS_URL + "?access_token=" + accessToken.getToken();
        String response = Providers.FACEBOOK.invokeGetRequest(accessToken, requestURL);
        try {
            return parseFriendsJSON(response);
        } catch (JSONException ex) {
            throw new AccessException(ex);
        }
    }

    private Iterable<UserDBO> parseFriendsJSON(String response) throws JSONException {
        JSONObject json = new JSONObject(response);
        ArrayList<UserDBO> friends = new ArrayList<UserDBO>();
        JSONArray getArray = json.optJSONArray("data");
        for (int i = 0; i < getArray.length(); i++) {
            UserDBO tmpUser = new UserDBO();
            JSONObject objectInArray = getArray.optJSONObject(i);
            tmpUser.setUserName(objectInArray.optString("name"));
            
            NetworkAccountDBO a = new NetworkAccountDBO();
            a.setAccountIdentificationString(objectInArray.optString("id"));
            a.setNetworkName(Providers.FACEBOOK.name());
            tmpUser.getNetworkAccountList().add(a);
            
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
