package com.magorasystems.mafmodules.common.utils;

import android.net.Uri;

import java.math.BigDecimal;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S. Bolkonsky
 */
public final class ParserUtils {

    private ParserUtils() {

    }

    /**
     * Parsing String to BigDecimal
     *
     * @param src a String containing the BigDecimal representation to be parsed
     * @return <b>null</b> if {@code src} == null <br>
     * <b>null</b> if throws NumberFormatException <br>
     * <b>instance</b> of BigDecimal with your parameters, otherwise
     * @throws NumberFormatException if {@code src} does not contain a valid string representation of a big decimal.
     */
    public static BigDecimal parseToDecimal(String src) {
        if (src == null) {
            return null;
        }
        try {
            return new BigDecimal(src);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Parsing String to Long
     *
     * @param src a string containing the long representation to be parsed
     * @return <b>null</b> if {@code src} == null <br>
     * <b>null</b> if throws NumberFormatException <br>
     * <b>instance</b> of Long with your parameters, otherwise
     * @throws NumberFormatException if {@code src} does not contain a valid string representation of a long.
     */
    public static Long parseToLong(String src) {
        if (src == null) {
            return null;
        }
        try {
            return Long.parseLong(src);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Parsing String to Double
     *
     * @param src the double value represented by the string argument.
     * @return <b>null</b> if {@code src} == null <br>
     * <b>null</b> if throws NumberFormatException <br>
     * <b>instance</b> of Double with your parameters, otherwise
     * @throws NumberFormatException if {@code src} does not contain a valid string representation of a double.
     */
    public static Double parseToDouble(String src) {
        if (src == null) {
            return null;
        }
        try {
            return Double.parseDouble(src);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Parsing String to Float
     *
     * @param src the float value represented by the string argument.
     * @return <b>null</b> if {@code src} == null <br>
     * <b>null</b> if throws NumberFormatException <br>
     * <b>instance</b> of Float with your parameters, otherwise
     * @throws NumberFormatException if {@code src} does not contain a valid string representation of a float.
     */
    public static Float parseToFloat(String src) {
        if (src == null) {
            return null;
        }
        try {
            return Float.parseFloat(src);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Parsing String to Integer
     *
     * @param src string containing the int representation to be parsed
     * @return <b>null</b> if {@code src} == null <br>
     * <b>null</b> if throws NumberFormatException <br>
     * <b>instance</b> of Integer with your parameters, otherwise
     * @throws NumberFormatException if {@code src} does not contain a valid string representation of a integer.
     */
    public static Integer parseToInteger(String src) {
        if (src == null) {
            return null;
        }
        try {
            return Integer.parseInt(src);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Parsing String to Uri
     *
     * @param src string with Uri
     * @return <b>null</b> if {@code src} == null <br>
     * <b>Uri</b> for this given uri string, otherwise
     */
    public static Uri parseToUri(String src) {
        if (src == null) {
            return null;
        }
        return Uri.parse(src);
    }
}
