package com.cboehle.lightspeed.demo.util;

import java.text.SimpleDateFormat;

/**
 * Class for static utlity methods 
 * @author chboeh
 */
public class Util {
    public static final SimpleDateFormat SDF = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");

    /**
     * Converts an integer to its position in the alphabet. For example:
     * 1-->A,2-->B, etc.
     * @param i an integer
     * @return A string representation of an integer
     */
    public static String getCharForNumber(int i) {
        return i > 0 && i < 27 ? String.valueOf((char) (i + 'A' - 1)) : null;
    }

    /**
     * Checks to ensure that the answer the user provided is a single character 
     * only and is actually a letter.
     * @param answer
     * @return true is answer is valid
     */
    public static boolean isValidAnswer(String answer) {
        char[] c = answer.toCharArray();
        return(c.length == 1 && isAlpha(c[0]));
    }
    
    /**
     * Checks if character is a letter.
     * @param c
     * @return true if character is a letter
     */
    private static boolean isAlpha(char c) {
        return Character.isLetter(c);
    }
}
