package com.magorasystems.mafmodules.common.utils;

import android.os.Build;

/**
 * @author Valentin S. Bolkonsky
 *         Developed by Magora Team (magora-systems.com). 2015.
 */
public class MiscUtils {

    public static String getHardwareId() {
        String hardwareId = "35" + // it starts with '35' for looking like IMEI
                Build.BOARD.length() % 10 +
                Build.BRAND.length() % 10 +
                Build.DEVICE.length() % 10 +
                Build.DISPLAY.length() % 10 +
                Build.HOST.length() % 10 +
                Build.ID.length() % 10 +
                Build.MANUFACTURER.length() % 10 +
                Build.MODEL.length() % 10 +
                Build.PRODUCT.length() % 10 +
                Build.TAGS.length() % 10 +
                Build.TYPE.length() % 10 +
                Build.USER.length() % 10;

        return hardwareId;
    }
}
