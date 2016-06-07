package com.magorasystems.mafmodules.common.utils;

import android.content.Context;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;

/**
 * Created by Serega Bobrischev on 13.11.2015.
 * Proud to Code for Magora Systems/magora-systems.com/magora-systems.ru (:
 */
public class ColorUtils {

    @ColorInt
    public static int getColorFromResources(Context context, @ColorRes int colorId) {
        if (Build.VERSION.SDK_INT >= 23) {
            return context.getResources().getColor(colorId, context.getTheme());
        } else {
            return context.getResources().getColor(colorId);
        }
    }
}