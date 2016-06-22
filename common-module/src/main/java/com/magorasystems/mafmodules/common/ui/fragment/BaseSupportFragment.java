package com.magorasystems.mafmodules.common.ui.fragment;

import android.content.DialogInterface;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;

import com.magorasystems.mafmodules.common.mvp.view.BaseView;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public interface BaseSupportFragment extends BaseView {

    String getTitle();

    String getFragmentName();

    void setFragment(@IdRes int resource, Fragment fragment, String fragmentName);

    void showErrorDialog(final String message, final DialogInterface.OnClickListener clickListener);
}
