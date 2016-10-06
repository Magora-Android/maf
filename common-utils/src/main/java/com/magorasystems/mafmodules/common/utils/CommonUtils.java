package com.magorasystems.mafmodules.common.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.List;

/**
 * @author S.A.Bobrischev
 *         Developed by Magora Team (magora-systems.com). 2016.
 */
@SuppressWarnings("unused")
public class CommonUtils {

    @Nullable
    public static Uri parseUri(String toParse) {
        if (TextUtils.isEmpty(toParse)) {
            return null;
        }
        try {
            return Uri.parse(toParse);
        } catch (Exception e) {
            return null;
        }
    }

    public static String getPathFromUri(@Nullable Uri uri) {
        if (uri != null) {
            return uri.getPath();
        }
        return null;
    }

    public static String getPathFromUri(@Nullable String uriAsString) {
        Uri uri = parseUri(uriAsString);
        return getPathFromUri(uri);
    }

    public static boolean isLollipop() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }

    public static boolean isRevealSupported() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }

    public static boolean isTransitionApiSupported() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }

    public static boolean isTransitionApiSupported(@Nullable View sharedView) {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && sharedView != null;
    }

    public static boolean shouldRequestPermission() {
        return Build.VERSION.SDK_INT >= 23;
    }

    public static boolean isJobSchedulerApiSupported() {
        return isLollipop();
    }

    public static int dpPx_i(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, Resources.getSystem().getDisplayMetrics());
    }

    public static float dpPx_f(float dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, Resources.getSystem().getDisplayMetrics());
    }

    public static int spPx_i(int sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, Resources.getSystem().getDisplayMetrics());
    }

    public static float spPx_f(float sp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, Resources.getSystem().getDisplayMetrics());
    }

    public static Intent createInstalledAppDetailsIntent(Context appContext) {
        Intent result = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        result.addCategory(Intent.CATEGORY_DEFAULT);
        result.setData(Uri.parse("package:" + appContext.getPackageName()));
        result.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        result.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        result.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);

        return result;
    }

    @NonNull
    public static Intent createOpenGooglePlayIntent(Context context) {
        Intent result = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + context.getPackageName()));
        if (!queryIntent(context, result)) {
            result = new Intent(Intent.ACTION_VIEW,
                    Uri.parse(String.format(context.getString(R.string.link_to_google_play), context.getPackageName())));
        }
        return result;
    }

    @Nullable
    public static Intent createBrowseIntent(Context appContext, @NonNull String link) {
        if (!link.startsWith("http")) {
            link = "http://" + link;
        }
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
        return queryIntent(appContext, intent) ? intent : null;
    }

    public static Intent createBrowseIntent(Context appContext, @StringRes int linkResId) {
        return createBrowseIntent(appContext, appContext.getResources().getString(linkResId));
    }

    public static Intent createShareEventIntent(Context appContext, String textToShare) {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, textToShare);
        return queryIntent(appContext, shareIntent) ? shareIntent : null;
    }


    private static boolean queryIntent(Context context, Intent intent) {
        PackageManager packageManager = context.getPackageManager();
        List<ResolveInfo> list = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);

        return !list.isEmpty();
    }

    public static int getStatusBarHeight(Context appContext) {
        int result = 0;
        int resourceId = appContext.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = appContext.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public static int getActionBarHeight(Context appContext) {
        final TypedArray styledAttributes = appContext.getTheme().obtainStyledAttributes(
                new int[]{android.R.attr.actionBarSize});
        int result = (int) styledAttributes.getDimension(0, 0);
        styledAttributes.recycle();

        return result;
    }

    @SafeVarargs
    @Nullable
    public static <T> T firstNonNull(@NonNull T... items) {
        for (T item : items) {
            if (item != null) {
                return item;
            }
        }
        return null;
    }

    public static boolean allNull(@Nullable Object... objects) {
        if (objects == null) {
            return true;
        }

        for (Object o : objects) {
            if (o != null) {
                return false;
            }
        }

        return true;
    }

    public static boolean hasNonNull(@Nullable Object... objects) {
        if (objects != null) {
            for (Object o : objects) {
                if (o != null) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean allNotNull(@Nullable Object... objects) {
        return !containsNull(objects);
    }

    public static boolean containsNull(@Nullable Object... objects) {
        if (objects == null) {
            return true;
        }
        for (Object o : objects) {
            if (o == null) {
                return true;
            }
        }
        return false;
    }

    public static void fromInputStreamToOutputStream(@NonNull InputStream in, @NonNull OutputStream out, boolean closeAfter) throws IOException {
        byte[] buffer = new byte[16 * 1024];
        while (in.read(buffer) > -1) {
            out.write(buffer);
        }
        if (closeAfter) {
            in.close();
            out.close();
        }
    }

    public static boolean equals(Object a, Object b) {
        return (a == null) ? (b == null) : a.equals(b);
    }

    public static boolean notEquals(Object a, Object b) {
        return !equals(a, b);
    }

    public static boolean isNullOrEmpty(@Nullable Collection<?> list) {
        return list == null || list.isEmpty();
    }

    @Nullable
    public static <T> List<T> subList(List<T> items, int count) {
        if (items == null) {
            return null;
        }
        return items.size() < count ? items : items.subList(items.size() - count, items.size());
    }
}
