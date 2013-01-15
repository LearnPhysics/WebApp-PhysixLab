/*
 * Copyright (C) 2012 Oliver Schütz
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package de.hofuniversity.iws.server.data.entities;

import java.io.Serializable;

import javax.persistence.*;

/**
 *
 * @author Oliver Schütz
 */
@Entity
@Table
public class NetworkAccountDBO implements Serializable, GenericEntity {

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
