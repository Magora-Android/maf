package com.magorasystems.mafmodules.common.utils;

import java.util.regex.Pattern;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S. Bolkonsky
 */
public final class PatternsUtils {

    private PatternsUtils() {

    }

    public final static Pattern patternPassword = Pattern.compile("(^([A-Za-z]|[0-9]|[_@#%!^*&])+$)");
}
