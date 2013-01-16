/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.server.data.handler;

import com.google.common.base.Optional;
import de.hofuniversity.iws.server.data.entities.NetworkAccountDBO;
import de.hofuniversity.iws.server.data.entities.UserDBO;
import de.hofuniversity.iws.server.oauth.Providers;
import java.util.List;
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
public class UserHandlerTest {
    
    public UserHandlerTest() {
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
     * Test of store method, of class UserHandler.
     */
    @Test
    public void testStore() {
        UserDBO user = new UserDBO();
        user.setUserName("testUser");
        assertNull(user.getId());
        UserDBO result = UserHandler.store(user);
        assertNotNull(user.getId());
        assertEquals(user, result);
        result.setFirstName("test");
        user.setLastName("user");
        UserHandler.store(user);
        UserHandler.store(result);
        assertNotNull(result.getLastName());
        assertNotNull(user.getFirstName());
    }

    /**
     * Test of getUserEntity method, of class UserHandler.
     */
    @Test
    public void testGetUserEntity() {
        UserDBO user = new UserDBO();
        user.setUserName("testUser");
        UserHandler.store(user);
        
        UserDBO result01 = UserHandler.getUserEntity(user.getId(), true);
        UserDBO result02 = UserHandler.getUserEntity(user.getId(), false);
        
        // Detached attribute is different
        assertNotSame(result01, result02);
        assertNotSame(user, result01);
        assertNotSame(user, result02);
        
        // The actually saved attributes are identical
        assertEquals(result01.getUserName(), result02.getUserName());
        assertEquals(user.getUserName(), result01.getUserName());
        assertEquals(user.getUserName(), result02.getUserName());
    }

    /**
     * Test of getAllUsers method, of class UserHandler.
     */
    @Test
    public void testGetAllUsers() {
        UserDBO user = new UserDBO();
        user.setUserName("testUser" + Math.random()*100000);
        UserHandler.store(user);
        List<UserDBO> result = UserHandler.getAllUsers();
        System.out.println(result.size());
        assertEquals(true, containsUser(result, user));
    }

    private boolean containsUser(List<UserDBO> list, UserDBO user) {
        for(UserDBO us : list) {
            if(user.getUserName().equals(us.getUserName())) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Test of getUser method, of class UserHandler.
     */
    @Test
    public void testGetUser() {
        Token oauthAccessToken = OAuthConstants.EMPTY_TOKEN ;
        Providers networkName = Providers.FACEBOOK;
        UserDBO user = new UserDBO();
        user.setUserName("testUser");
        NetworkAccountDBO na = new NetworkAccountDBO();
        na.setOauthAccessToken(oauthAccessToken.getToken());
        na.setOauthAccessSecret(oauthAccessToken.getSecret());
        na.setNetworkName(networkName.name());
        na.setUser(UserHandler.store(user));
        NetworkAccountHandler.store(na);
        
        UserDBO result = UserHandler.getUser(oauthAccessToken, networkName);
        
        assertNotSame(user, result);     
        assertEquals(user.getUserName(), result.getUserName());
    }

    /**
     * Test of getUserByAIDString method, of class UserHandler.
     */
    @Test
    public void testGetUserByAIDString() {
        Providers network = Providers.GOOGLE;
        String accountIdentificationString = "testNetworkAccount" + Math.random()*100000;
        NetworkAccountDBO na = new NetworkAccountDBO();
        na.setAccountIdentificationString(accountIdentificationString);
        na.setNetworkName(network.name());
        UserDBO user = new UserDBO();
        user.setUserName("testUser");
        na.setUser(UserHandler.store(user));
        NetworkAccountHandler.store(na);
        UserDBO result = UserHandler.getUserByAIDString(accountIdentificationString, network);
        
        assertNotSame(user, result);
        assertEquals(user.getUserName(), result.getUserName());
    }

    /**
     * Test of getNetworkAccount method, of class UserHandler.
     */
    @Test
    public void testGetNetworkAccount() {
        Providers network = Providers.GOOGLE;
        String accountIdentificationString = "testNetworkAccount" + Math.random()*100000;
        NetworkAccountDBO na = new NetworkAccountDBO();
        na.setAccountIdentificationString(accountIdentificationString);
        na.setNetworkName(network.name());
        UserDBO user = new UserDBO();
        user.setUserName("testUser");
        na.setUser(UserHandler.store(user));
        NetworkAccountHandler.store(na);
        Optional result = UserHandler.getNetworkAccount(user, network);
        assertEquals(true, result.isPresent());
        assertEquals(na ,(NetworkAccountDBO)result.get());
        assertEquals(na.getAccountIdentificationString() ,((NetworkAccountDBO)result.get()).getAccountIdentificationString());
    }
}
