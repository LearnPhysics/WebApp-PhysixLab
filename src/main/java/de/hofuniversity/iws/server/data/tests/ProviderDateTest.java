/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.server.data.tests;

import de.hofuniversity.iws.server.data.entities.NetworkAccount;
import de.hofuniversity.iws.server.data.entities.User;
import de.hofuniversity.iws.server.data.handler.NetworkAccountHandler;
import de.hofuniversity.iws.server.oauth.OAuthRequest;
import de.hofuniversity.iws.server.oauth.Providers;
import de.hofuniversity.iws.server.oauth.provider.IProviderData;
import de.hofuniversity.iws.server.oauth.provider.OAuthProvider;
import de.hofuniversity.iws.server.oauth.provider.ProviderDataFactory;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Scanner;
import org.scribe.model.Token;
import org.scribe.model.Verifier;

/**
 *
 * @author User
 */
public class ProviderDateTest {
        public static void main(String[] args) throws IOException, URISyntaxException, ClassNotFoundException {
            
            Scanner in = new Scanner(System.in);
            
            OAuthProvider prov = Providers.FACEBOOK.getProvider();         
            OAuthRequest req = prov.createRequest();
            
            Desktop.getDesktop().browse(new URI(req.getAuthorizeUrl()));
            
                System.out.println("And paste the verifier here");
                System.out.print(">>");
                Verifier v = new Verifier(in.nextLine());     
                Token access = req.generateAccessToken(v);
            IProviderData userdata = ProviderDataFactory.getProvider(Providers.FACEBOOK.name(), req.getService());  
            
            User CurrentUser = userdata.get_UserData(access);
            List<User> Friends =  userdata.get_Friends(access,CurrentUser);

            /* angemeldeten User in PhysixLab-Datenbank suchen */
            NetworkAccount netACC = NetworkAccountHandler.getNetworkAccountEntity(Providers.TWITTER.name(),CurrentUser.getAccountIdentificationString() , true);
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
