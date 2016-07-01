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

    protected abstract BasePresenter<? extends BaseView, ROUTER,?, ? extends ViewOutput<?>> getPresenter();

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


}
