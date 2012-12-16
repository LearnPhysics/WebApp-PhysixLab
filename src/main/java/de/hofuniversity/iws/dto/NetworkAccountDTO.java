package de.hofuniversity.iws.dto;

import java.io.Serializable;


public class NetworkAccountDTO implements Serializable {

    private boolean detached = false;
    
    private Long id;

    private String networkName;

    private String accountIdentificationString;

    private String oauthAccessToken;

    private String oauthAccessSecret;

    private UserDTO user;

    public boolean isDetached() {
        return detached;
    }
    
    public void setDetached(boolean detached) {
        this.detached = detached;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getNetworkName() {
        return networkName;
    }

    public void setNetworkName(String networkName) {
        this.networkName = networkName;
    }

    public String getAccountIdentificationString() {
        return accountIdentificationString;
    }

    public void setAccountIdentificationString(String accountIdentificationString) {
        this.accountIdentificationString = accountIdentificationString;
    }

    public String getOauthAccessToken() {
        return oauthAccessToken;
    }

    public void setOauthAccessToken(String oauthAccessToken) {
        this.oauthAccessToken = oauthAccessToken;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public String getOauthAccessSecret() {
        return oauthAccessSecret;
    }

    public void setOauthAccessSecret(String oauthAccessSecret) {
        this.oauthAccessSecret = oauthAccessSecret;
    }
    
}
