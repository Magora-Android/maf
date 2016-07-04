package com.magorasystems.mafmodules.common.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * Use this for manage activities.
 *
 * @author Valentin S. Bolkonsky
 *         Developed by Magora Team (magora-systems.com). 2016.
 */
public final class ActivityNavigator {

    public static final String ARGS_BUNDLE = "com.magorasystems.mafmodules.common.ui.activity.ARGS_BUNDLE";

    private static final Map<String, Activity> activityBuffer = Maps.newHashMap();

    private ActivityNavigator() {
        // empty private constructor
    }

    /**
     * Finish activity by name and remove from activityBeffer, if exists
     *
     * @param activityName name for searchin in HashMap {@code activity Buffer}
     */
    public static void finishActivity(String activityName) {
        Activity activity = activityBuffer.remove(activityName);
        if (activity != null && !activity.isFinishing()) {
            activity.finish();
        }
    }

    /**
     * Start generic activity with bundle and flags (optional)
     *
     * @param parentActivity parent activity
     * @param activityClazz  destination activity class
     * @param args           bundle with args, set <b>null</b> if not needed
     * @param isFinish       if true, your {@code parentActivity} will be finished
     * @param flags          flags for activity, set <b>0</b> if not needed
     */
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

    /**
     * Start activity for result
     *
     * @param parentActivity parent activity
     * @param intent         intent to start
     * @param request        if >= 0, this code will be returned in {@link Activity#onActivityResult(int, int, Intent)} when the activity exits.
     */
    public static void startForResult(final Activity parentActivity, Intent intent, int request) {
        parentActivity.startActivityForResult(intent, request);
    }
}
