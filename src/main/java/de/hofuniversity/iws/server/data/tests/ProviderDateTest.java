/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.server.data.tests;

import de.hofuniversity.iws.server.OAuthLogin;
import de.hofuniversity.iws.server.ProviderDataHandler;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;
import org.scribe.model.Verifier;

/**
 *
 * @author User
 */
public class ProviderDateTest {
        public static void main(String[] args) throws IOException, URISyntaxException {
            
            Scanner in = new Scanner(System.in);

            OAuthLogin oauth = new OAuthLogin();
            oauth.Login("Twitter");           
            oauth.get_AUTHORIZE_URL();
                        
            ProviderDataHandler pdh = new ProviderDataHandler(oauth.get_OAUTH_SERVICE());
            
            Desktop.getDesktop().browse(new URI(oauth.get_AUTHORIZE_URL()));
            
                System.out.println("And paste the verifier here");
                System.out.print(">>");
                oauth.set_OAUTH_VERIFIER(in.nextLine());     
                
                System.out.println(oauth.get_accessToken().getToken());
                pdh.get_TwitterUser(oauth.get_accessToken());
        }
}
