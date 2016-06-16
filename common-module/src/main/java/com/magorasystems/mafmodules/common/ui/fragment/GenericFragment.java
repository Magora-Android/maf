package com.magorasystems.mafmodules.common.ui.fragment;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.magorasystems.mafmodules.common.mvp.presenter.BaseLifecyclePresenter;
import com.magorasystems.mafmodules.common.mvp.presenter.BasePresenter;
import com.magorasystems.mafmodules.common.mvp.router.BaseRouter;
import com.magorasystems.mafmodules.common.mvp.view.BaseView;

/**
 * @author Valentin S. Bolkonsky
 *         Developed by Magora Team (magora-systems.com). 2016.
 */
public abstract class GenericFragment<ROUTER extends BaseRouter> extends BaseFragmentImpl implements CommonFragment<ROUTER> {


    public GenericFragment() {
        super();
    }

    @NonNull
    protected ROUTER router;

    protected abstract BasePresenter<? extends BaseView, ROUTER> getPresenter();

    @Override
    @SuppressWarnings("unchecked")
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setRetainInstance(true);
        final Activity activity = getActivity();
        if (!(activity instanceof BaseRouter)) {
            throw new IllegalArgumentException("Activity \'" + activity.getClass().getName() +
                    "\' must be implement \'BaseRouter\' interface");
        }
        router = (ROUTER) activity;
    }

    @Override
    public void showErrorDialog(String message, DialogInterface.OnClickListener clickListener) {
        if (getActivity().isFinishing()) {
            return;
        }
        new AlertDialog.Builder(getActivity())
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton(android.R.string.ok, clickListener)
                .show();
    }

    @Override
    public void onStart() {
        onSuperStart();
        final BasePresenter presenter = getPresenter();
        if (presenter != null && presenter instanceof BaseLifecyclePresenter) {
            ((BaseLifecyclePresenter) presenter).onStart();
        }
    }

    @Override
    public void onStop() {
        onSuperStop();
        final BasePresenter presenter = getPresenter();
        if (presenter != null && presenter instanceof BaseLifecyclePresenter) {
            ((BaseLifecyclePresenter) presenter).onStart();
        }
    }

    @Override
    public void onDestroyView() {
        final BasePresenter presenter = getPresenter();
        if (presenter != null) {
            presenter.removeRouter();
            presenter.detachView(false);
        }
        super.onDestroyView();
    }

    protected final void onSuperStart() {
        super.onStart();
    }

    protected final void onSuperStop() {
        super.onStart();
    }

    protected abstract View getProgressView();


}
