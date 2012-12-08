package de.hofuniversity.iws.server.oauth.provider;

import de.hofuniversity.iws.server.oauth.Providers;
import java.io.IOException;
import java.util.Properties;
import org.scribe.oauth.OAuthService;

/**
 *
 * @author Andreas Arndt <andreas.arndt@hof-university.de>
 */
public class ProviderDataFactory {
    
   
    public static IProviderData getProvider(String provider,OAuthService s)
    {
        if(provider.equals("FACEBOOK"))
        {
            return new FacebookProviderUserData(s);
        }
        if(provider.equals("TWITTER"))
        {
            return new TwitterProviderUserData(s);
        }
        if(provider.equals("GOOGLE"))
        {
            return new GoogleProviderUserData(s);
        }
        return null;
    }
    
    private static class Static {

        private static final String PROPERTIE_FILE = "/META-INF/providerdata.properties";
        private static final Properties properties = new Properties();

        static {
            try {
                properties.load(Providers.class.getResourceAsStream(PROPERTIE_FILE));
            } catch (IOException ex) {
                throw new RuntimeException("OAuth Configuration can't be read!");
            }
        }
    }
    
    public static String getProp(String name)
    {
        return ProviderDataFactory.Static.properties.getProperty(name);
    }
}
