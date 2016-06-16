package com.magorasystems.mafmodules.common.ui.fragment;

import android.app.Fragment;
import android.support.annotation.IdRes;

import com.magorasystems.mafmodules.common.mvp.view.BaseView;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author karpenko
 */
public interface BaseFragment extends BaseView {

    String getTitle();

    String getFragmentName();

    void setFragment(@IdRes int resource, Fragment fragment, String fragmentName);
}
