/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.server.oauth;

import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author Daniel Heinrich <dannynullzwo@gmail.com>
 */
public enum OAuthProperties {

    APP("/META-INF/oauth.properties"),
    ACCESSORS("/META-INF/accessors.properties");
    
    private final Properties properties = new Properties();

    private OAuthProperties(String file) {
        try {
            properties.load(Providers.class.getResourceAsStream(file));
        } catch (IOException ex) {
            throw new RuntimeException("Can not read Configuration: " + name());
        }
    }

    public String getPropertie(String name) {
        String prop = properties.getProperty(name);
        if (prop == null) {
            String[] t = name.split("\\.");
            throw new RuntimeException("Can not read propertie(\"" + t[1] + "\") of provider: " + t[0]);
        }
        return prop;
    }
}
