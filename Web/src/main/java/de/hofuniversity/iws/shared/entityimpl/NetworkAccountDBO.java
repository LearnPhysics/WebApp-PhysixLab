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

    @Override
    public boolean isDetached() {
        return detached;
    }

    @Override
    public void setDetached(boolean detached) {
        this.detached = detached;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getNetworkName() {
        return networkName;
    }

    @Override
    public void setNetworkName(String networkName) {
        this.networkName = networkName;
    }

    @Override
    public String getAccountIdentificationString() {
        return accountIdentificationString;
    }

    @Override
    public void setAccountIdentificationString(String accountIdentificationString) {
        this.accountIdentificationString = accountIdentificationString;
    }

    @Override
    public String getOauthAccessToken() {
        return oauthAccessToken;
    }

    @Override
    public void setOauthAccessToken(String oauthAccessToken) {
        this.oauthAccessToken = oauthAccessToken;
    }

    @Override
    public UserDBO getUser() {
        return user;
    }

    public void setUser(UserDBO user) {
        this.user = user;
    }

    @Override
    public String getOauthAccessSecret() {
        return oauthAccessSecret;
    }

    @Override
    public void setOauthAccessSecret(String oauthAccessSecret) {
        this.oauthAccessSecret = oauthAccessSecret;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof NetworkAccountDBO)) {
            return false;
        }
        NetworkAccountDBO na = (NetworkAccountDBO) obj;
        return na.getAccountIdentificationString().equals(getAccountIdentificationString())
               && na.getNetworkName().equals(getNetworkName());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + (this.networkName != null ? this.networkName.hashCode() : 0);
        hash = 89 * hash + (this.accountIdentificationString != null ? this.accountIdentificationString.hashCode() : 0);
        return hash;
    }
}
