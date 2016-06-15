package com.magorasystems.mafmodules.common.ui.widget;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S. Bolkonsky
 */
public final class WidgetUtils {

    private WidgetUtils() {

    }

    public static void showAlertDialog(Context context, DialogInterface.OnClickListener onOkClick,
                                       DialogInterface.OnClickListener onCancelClick, String title,
                                       String message, boolean isOkShow, boolean isCancelClick) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context, android.R.attr.dialogTheme);
        builder.setTitle(title);
        builder.setMessage(message);
        if (isOkShow)
            builder.setPositiveButton(context.getString(android.R.string.yes), onOkClick);
        if (isCancelClick)
            builder.setNegativeButton(context.getString(android.R.string.cancel), onCancelClick);
        builder.show();
    }

    public static void showAlertDialog(Context context, String title, String message, String buttonName,
                                       DialogInterface.OnClickListener onOkClick) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context, android.R.attr.dialogTheme);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton(buttonName, onOkClick);
        builder.show();
    }

    public static void hideSoftKeyboard(Activity activity) {
        if (activity == null) {
            return;
        }
        View view = activity.getCurrentFocus();
        if (view == null) {
            view = new View(activity);
        }
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
