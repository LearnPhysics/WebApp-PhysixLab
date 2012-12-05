/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.server.data.tests;

import de.hofuniversity.iws.server.OAuthLogin;
import de.hofuniversity.iws.server.ProviderDataHandler;
import de.hofuniversity.iws.server.data.entities.NetworkAccount;
import de.hofuniversity.iws.server.data.entities.User;
import de.hofuniversity.iws.server.data.handler.NetworkAccountHandler;
import de.hofuniversity.iws.server.data.handler.UserHandler;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;

/**
 *
 * @author User
 */
public class ProviderDateTest {
        public static void main(String[] args) throws IOException, URISyntaxException {
            
            Scanner in = new Scanner(System.in);
            
            String NetworkName = "Facebook";
            
            OAuthLogin oauth = new OAuthLogin();
            oauth.Login(NetworkName);           
                        
            ProviderDataHandler pdh = new ProviderDataHandler(oauth.get_OAUTH_SERVICE());
            
            Desktop.getDesktop().browse(new URI(oauth.get_AUTHORIZE_URL()));
            
                System.out.println("And paste the verifier here");
                System.out.print(">>");
                oauth.set_OAUTH_VERIFIER(in.nextLine());     
                
                Object[] User_obj = pdh.get_UserData(oauth.get_accessToken(),NetworkName);
                
                /* angemeldeten User in PhysixLab-Datenbank suchen */
                NetworkAccount netACC = NetworkAccountHandler.getNetworkAccountEntity(NetworkName, User_obj[1].toString(), true);
         //       User user = netACC.getUser();   
                
                 /* prüfe ob es den User schon in der PhysixLab-Datenbank gibt
                 * wenn ja, dann nix tun
                 * ansonsten User in Datenbank aufnehmen
                 */
                if(netACC==null)
                {
                    UserHandler.store((User)User_obj[0]);
                    netACC = new NetworkAccount();
                    netACC.setNetworkName(NetworkName);
                    netACC.setAccountIdentificationString((String)User_obj[1]);
                    netACC.setUser((User)User_obj[0]);
                    netACC.setOauthAccessToken(oauth.get_accessToken().getToken());                
                    NetworkAccountHandler.store(netACC);                  
                }
        }
}
