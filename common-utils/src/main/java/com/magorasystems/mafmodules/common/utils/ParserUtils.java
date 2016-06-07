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

    public static Uri parseToUri(String src) {
        if (src == null) {
            return null;
        }
        return Uri.parse(src);
    }
}
