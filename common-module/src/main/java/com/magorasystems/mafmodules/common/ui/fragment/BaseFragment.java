package com.magorasystems.mafmodules.common.ui.fragment;

import android.app.Fragment;
import android.support.annotation.IdRes;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author karpenko
 */
public interface BaseFragment {

    String getTitle();

    String getFragmentName();

    void setFragment(@IdRes int resource, Fragment fragment, String fragmentName);
}
