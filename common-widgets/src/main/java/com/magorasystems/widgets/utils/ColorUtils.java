package com.magorasystems.widgets.utils;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.util.TypedValue;

/**
 * Created by Serega Bobrischev on 13.11.2015.
 * Proud to Code for Magora Systems/magora-systems.com/magora-systems.ru (:
 */
public class ColorUtils {

    @ColorInt
    public static int getThemeColor(@NonNull final Context context, @AttrRes final int attributeColor) {
        final TypedValue value = new TypedValue();
        context.getTheme().resolveAttribute(attributeColor, value, true);
        return value.data;
    }
}