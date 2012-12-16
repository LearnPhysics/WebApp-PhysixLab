/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.iws.shared.services;

import java.sql.Timestamp;

/**
 *
 * @author Oliver
 */
public class Utilities {
    
    public static int getAge(Timestamp time) {
        Timestamp current = new Timestamp(System.currentTimeMillis());
        int difference = current.compareTo(time);
        return (int)(difference/(60*60*24*365.25));
    }  
}
