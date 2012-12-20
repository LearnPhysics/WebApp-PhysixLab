/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.shared.entitys;

import java.io.Serializable;


/**
 *
 * @author Daniel Heinrich <dannynullzwo@gmail.com>
 */
public interface NetworkAccount extends Serializable {

    public String getNetworkName();

    public void setNetworkName(String networkName);

    public String getAccountIdentificationString();

    public void setAccountIdentificationString(String accountIdentificationString);

    public String getOauthAccessToken();

    public void setOauthAccessToken(String oauthAccessToken);

    public User getUser();

    public String getOauthAccessSecret();

    public void setOauthAccessSecret(String oauthAccessSecret);
}
