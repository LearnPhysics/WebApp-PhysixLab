/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.server.data.handler;

import de.hofuniversity.iws.server.data.entities.NetworkAccountDBO;
import de.hofuniversity.iws.server.oauth.Providers;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.scribe.model.OAuthConstants;
import org.scribe.model.Token;

/**
 *
 * @author Oliver
 */
public class NetworkAccountHandlerTest {
    
    public NetworkAccountHandlerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of store method, of class NetworkAccountHandler.
     */
    @Test
    public void testStore() {
        NetworkAccountDBO na = new NetworkAccountDBO();
        na.setAccountIdentificationString("testSubjectArea");
        assertNull(na.getId());
        NetworkAccountDBO result = NetworkAccountHandler.store(na);
        assertNotNull(na.getId());
        assertEquals(na, result);
    }

    /**
     * Test of getNetworkAccountEntity method, of class NetworkAccountHandler.
     */
    @Test
    public void testGetNetworkAccountEntity_long_boolean() {
        String ais = "testNetworkAccount" + Math.random()*100000;
        NetworkAccountDBO na = new NetworkAccountDBO();
        na.setAccountIdentificationString(ais);
        NetworkAccountHandler.store(na);
        
        NetworkAccountDBO result01 = NetworkAccountHandler.getNetworkAccountEntity(na.getId(), true);
        NetworkAccountDBO result02 = NetworkAccountHandler.getNetworkAccountEntity(na.getId(), false);
        
        // Detached attribute is different
        assertNotSame(result01, result02);
        assertNotSame(na, result01);
        assertNotSame(na, result02);
        
        // The actually saved attributes are identical
        assertEquals(result01.getAccountIdentificationString(), result02.getAccountIdentificationString());
        assertEquals(na.getAccountIdentificationString(), result01.getAccountIdentificationString());
        assertEquals(na.getAccountIdentificationString(), result02.getAccountIdentificationString());
    }

    /**
     * Test of getNetworkAccountEntity method, of class NetworkAccountHandler.
     */
    @Test
    public void testGetNetworkAccountEntity_3args() {
        Providers network = Providers.GOOGLE;
        String accountIdentificationString = "testNetworkAccount" + Math.random()*100000;
        NetworkAccountDBO na = new NetworkAccountDBO();
        na.setAccountIdentificationString(accountIdentificationString);
        na.setNetworkName(network.name());
        NetworkAccountHandler.store(na);
        NetworkAccountDBO result01 = NetworkAccountHandler.getNetworkAccountEntity(network, accountIdentificationString, true);
        NetworkAccountDBO result02 = NetworkAccountHandler.getNetworkAccountEntity(network, accountIdentificationString, false);
        
        // Detached attribute is different
        assertNotSame(result01, result02);
        assertNotSame(na, result01);
        assertNotSame(na, result02);
        
        // The actually saved attributes are identical
        assertEquals(result01.getAccountIdentificationString(), result02.getAccountIdentificationString());
        assertEquals(na.getAccountIdentificationString(), result01.getAccountIdentificationString());
        assertEquals(na.getAccountIdentificationString(), result02.getAccountIdentificationString());
        
        assertEquals(result01.getNetworkName(), result02.getNetworkName());
        assertEquals(na.getNetworkName(), result01.getNetworkName());
        assertEquals(na.getNetworkName(), result02.getNetworkName());
    }

    /**
     * Test of getNetworkAccountEntityByAccessToken method, of class NetworkAccountHandler.
     */
    @Test
    public void testGetNetworkAccountEntityByAccessToken() {
        Providers network = Providers.TWITTER;
        Token oauthToken = new Token("sdasdad", "afgdfhdfh");
        System.out.println(oauthToken.getToken());
        NetworkAccountDBO na = new NetworkAccountDBO();
        na.setOauthAccessToken(oauthToken.getToken());
        na.setOauthAccessSecret(oauthToken.getSecret());
        na.setNetworkName(network.name());
        NetworkAccountHandler.store(na);
        NetworkAccountDBO result01 = NetworkAccountHandler.getNetworkAccountEntityByAccessToken(network, oauthToken, true);
        NetworkAccountDBO result02 = NetworkAccountHandler.getNetworkAccountEntityByAccessToken(network, oauthToken, false);
        
        // Detached attribute is different
        assertNotNull(result01);
        assertNotNull(result02);
        assertNotSame(result01, result02);
        assertNotSame(na, result01);
        assertNotSame(na, result02);
        
        // The actually saved attributes are identical
        assertEquals(result01.getOauthAccessToken(), result02.getOauthAccessToken());
        assertEquals(na.getOauthAccessToken(), result01.getOauthAccessToken());
        assertEquals(na.getOauthAccessToken(), result02.getOauthAccessToken());
        
        assertEquals(result01.getOauthAccessSecret(), result02.getOauthAccessSecret());
        assertEquals(na.getOauthAccessSecret(), result01.getOauthAccessSecret());
        assertEquals(na.getOauthAccessSecret(), result02.getOauthAccessSecret());
        
        assertEquals(result01.getNetworkName(), result02.getNetworkName());
        assertEquals(na.getNetworkName(), result01.getNetworkName());
        assertEquals(na.getNetworkName(), result02.getNetworkName());
    }
}
