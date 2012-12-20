package de.hofuniversity.iws.shared.entityimpl;

import java.io.Serializable;

import de.hofuniversity.iws.shared.entitys.NetworkAccount;
import javax.persistence.*;

@Entity
@Table
public class NetworkAccountDBO implements Serializable, GenericEntity, NetworkAccount {

    @Transient
    private boolean detached = false;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String networkName;
    @Column
    private String accountIdentificationString;
    @Column
    private String oauthAccessToken;
    @Column
    private String oauthAccessSecret;
    @ManyToOne
    private UserDBO user;

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

    public UserDBO getUser() {
        return user;
    }

    public void setUser(UserDBO user) {
        this.user = user;
    }

    public String getOauthAccessSecret() {
        return oauthAccessSecret;
    }

    public void setOauthAccessSecret(String oauthAccessSecret) {
        this.oauthAccessSecret = oauthAccessSecret;
    }
    
}
