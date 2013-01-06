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
    
    // If pressionInPercent = 100 then given can be between 0 and solution x 2
    public static boolean isSimilar(double solution, double given, double precissionInPercent) {
        double diff = solution*(precissionInPercent/100);
        if(given < solution-diff) {
            return false;
        }
        else if(given > solution+diff) {
            return false;
        }
        else {
            return true;
        }
    }
    
    public static String textShortener(String text, int maxLength) {
        if(text.length() > maxLength) {
            String retval = text.substring(0, maxLength);
            retval = retval.substring(0, retval.lastIndexOf(" ")) + "...";
            return retval;
        }
        return text;
    }
}
