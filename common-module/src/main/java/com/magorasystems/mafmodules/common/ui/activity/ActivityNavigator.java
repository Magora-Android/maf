package com.magorasystems.mafmodules.common.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * @author Valentin S. Bolkonsky
 *         Developed by Magora Team (magora-systems.com). 2016.
 */
public final class ActivityNavigator {

    public static final String ARGS_BUNDLE = "com.magorasystems.mafmodules.common.ui.activity.ARGS_BUNDLE";

    private static final Map<String, Activity> activityBuffer = Maps.newHashMap();

    private ActivityNavigator() {

    }

    public static void finishActivity(String activityName) {
        Activity activity = activityBuffer.remove(activityName);
        if (activity != null && !activity.isFinishing()) {
            activity.finish();
        }
    }

    public static void startGenericActivity(final Activity parentActivity, Class<? extends Activity> activityClazz, Bundle args, boolean isFinish, int flags) {
        final Intent intent = new Intent(parentActivity, activityClazz);
        if (null != args) {
            intent.putExtra(ARGS_BUNDLE, args);
        }
        if (flags != 0) {
            intent.setFlags(flags);
        }
        parentActivity.startActivity(intent);
        if (isFinish) parentActivity.finish();
    }

    public static void startForResult(final Activity parentActivity, Intent intent, int request) {
        parentActivity.startActivityForResult(intent, request);
    }
}
