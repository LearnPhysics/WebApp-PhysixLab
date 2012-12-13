/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.server.data.tests;

import com.google.common.base.Optional;
import de.hofuniversity.iws.server.data.entities.User;
import de.hofuniversity.iws.server.oauth.*;
import de.hofuniversity.iws.server.oauth.accessors.*;
import de.hofuniversity.iws.server.oauth.provider.OAuthProvider;
import java.awt.Desktop;
import java.io.IOException;
import java.net.*;
import java.util.Scanner;
import org.scribe.model.*;

/**
 *
 * @author User
 */
public class ProviderDateTest {

    public static void main(String[] args) throws IOException, URISyntaxException, ClassNotFoundException {

        Scanner in = new Scanner(System.in);
        Providers provider = Providers.FACEBOOK;

        OAuthProvider prov = provider.getProvider();
        OAuthAccessRequest req = prov.createRequest();

        Desktop.getDesktop().browse(new URI(req.getAuthorizeUrl()));

        System.out.println("And paste the verifier here");
        System.out.print(">>");
        Verifier v = new Verifier(in.nextLine());
        Token access = req.generateAccessToken(v);

        Optional<UserDataAccessor> userData = provider.getAccessor(UserDataAccessor.class);
        if (userData.isPresent()) {
            try {
                UserDataAccessor ac = userData.get();
                User user = ac.getUserData(access);
                System.out.println(user.getUserName() + "(" + user.getFirstName() + " " + user.getLastName() + ")");
                System.out.println("\t" + user.getBirthDate() + " - " + user.getCity());
                System.out.println("\t"+user.getUserPic());

                Optional<FriendListAccessor> friends = provider.getAccessor(FriendListAccessor.class);
                if (friends.isPresent()) {
                    FriendListAccessor ac2 = friends.get();
                    Iterable<User> friends1 = ac2.getFriends(access, user);
                    for(User f: friends1)
                    {
                        System.out.println(f.getUserName());
                    }
                }

            } catch (AccessException ex) {
                ex.printStackTrace();
            }
        }



        /* angemeldeten User in PhysixLab-Datenbank suchen */
//        NetworkAccount netACC = NetworkAccountHandler.getNetworkAccountEntity(Providers.TWITTER.name(), CurrentUser.getAccountIdentificationString(), true);
//       User user = netACC.getUser();   

        /* prüfe ob es den User schon in der PhysixLab-Datenbank gibt
         * wenn ja, dann nix tun
         * ansonsten User in Datenbank aufnehmen
         */
        /*      if(netACC==null)
         {
         UserHandler.store((User)User_obj[0]);
         netACC = new NetworkAccount();
         netACC.setNetworkName(Providers.FACEBOOK.name());
         netACC.setAccountIdentificationString((String)User_obj[1]);
         netACC.setUser((User)User_obj[0]);
         netACC.setOauthAccessToken(access.getToken());    
         NetworkAccountHandler.store(netACC);                 
         }*/
    }
}
