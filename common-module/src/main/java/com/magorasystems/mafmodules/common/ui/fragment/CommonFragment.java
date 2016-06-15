package com.magorasystems.mafmodules.common.ui.fragment;

import android.content.DialogInterface;

import com.magorasystems.mafmodules.common.mvp.router.BaseRouter;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S. Bolkonsky
 */
public interface CommonFragment<T extends BaseRouter> extends BaseFragment {

    void showErrorDialog(final String message, final DialogInterface.OnClickListener clickListener);
}