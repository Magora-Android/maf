package com.magorasystems.mafmodules.common.utils;

import android.content.Context;
import android.os.Build;
import android.support.annotation.AttrRes;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.util.TypedValue;

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

    @ColorInt
    public static int getThemeColor(@NonNull final Context context, @AttrRes final int attributeColor) {
        final TypedValue value = new TypedValue();
        context.getTheme().resolveAttribute(attributeColor, value, true);
        return value.data;
    }
}