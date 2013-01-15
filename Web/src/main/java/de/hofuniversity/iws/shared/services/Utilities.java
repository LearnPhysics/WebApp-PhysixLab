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
package de.hofuniversity.iws.shared.services;

import java.sql.Timestamp;

/**
 *
 * @author Oliver Schütz 
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
