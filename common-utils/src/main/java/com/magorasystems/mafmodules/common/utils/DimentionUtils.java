package com.magorasystems.mafmodules.common.utils;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Point;
import android.util.DisplayMetrics;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class DimentionUtils {

    private DimentionUtils() {

    }

    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    public static int pxToDp(int px) {
        return (int) (px / Resources.getSystem().getDisplayMetrics().density);
    }

    public static int getScreenWidth(Activity context, float factor) {
        final DisplayMetrics metrics = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return Float.valueOf(factor * metrics.widthPixels).intValue();
    }

    public static int getScreenHeight(Activity context, float factor) {
        final DisplayMetrics metrics = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return Float.valueOf(factor * metrics.heightPixels).intValue();
    }

    public static Point getCenter(Activity context) {
        final DisplayMetrics metrics = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return new Point(metrics.widthPixels / 2, metrics.heightPixels / 2);
    }
}
