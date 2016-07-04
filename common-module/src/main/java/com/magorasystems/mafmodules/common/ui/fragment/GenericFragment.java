package com.magorasystems.mafmodules.common.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.magorasystems.mafmodules.common.module.output.ViewOutput;
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

    protected abstract BasePresenter<? extends BaseView, ROUTER, ?, ? extends ViewOutput<?>> getPresenter();

    /**
     * {@code setRetaineInstance(true)} and cast and set router from activity
     *
     * @param savedInstanceState saved instance state
     * @throws IllegalArgumentException if activity not implement {@link BaseRouter} interface
     */
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

    /**
     * Call {@link #onSuperStart()} <br>
     * and if presenter not null and it`s instance of {@link BaseLifecyclePresenter} <br>
     * and call {@link BaseLifecyclePresenter#onStart()}
     */
    @Override
    public void onStart() {
        onSuperStart();
        final BasePresenter presenter = getPresenter();
        if (presenter != null && presenter instanceof BaseLifecyclePresenter) {
            ((BaseLifecyclePresenter) presenter).onStart();
        }
    }

    /**
     * Call {@link #onSuperStop()} <br>
     * and if presenter not null and it`s instance of {@link BaseLifecyclePresenter} <br>
     * and call {@link BaseLifecyclePresenter#onStart()}
     */
    @Override
    public void onStop() {
        onSuperStop();
        final BasePresenter presenter = getPresenter();
        if (presenter != null && presenter instanceof BaseLifecyclePresenter) {
            ((BaseLifecyclePresenter) presenter).onStart();
        }
    }

    /**
     * If presenter not null, remove router and detach view from it
     */
    @Override
    public void onDestroyView() {
        final BasePresenter presenter = getPresenter();
        if (presenter != null) {
            presenter.removeRouter();
            presenter.detachView(false);
        }
        super.onDestroyView();
    }


}
