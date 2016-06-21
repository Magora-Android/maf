package com.magorasystems.mafmodules.common.ui.fragment;

import android.app.Fragment;
import android.content.DialogInterface;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.common.base.Preconditions;

import java.util.concurrent.atomic.AtomicInteger;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S. Bolkonsky
 */
public abstract class BaseFragmentImpl extends Fragment implements BaseFragment {

    private static final AtomicInteger lastFragmentId = new AtomicInteger(0);
    private final int fragmentId;

    @LayoutRes
    protected abstract int getResourceLayout();

    private Unbinder unbinder;

    public BaseFragmentImpl() {
        fragmentId = lastFragmentId.incrementAndGet();
    }

    public String getFragmentName() {
        return Long.toString(fragmentId);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int layout = getResourceLayout();
        if (0 != layout) {
            return inflater.inflate(getResourceLayout(), container, false);
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        detachView();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }

    @Override
    public void setFragment(@IdRes int resource, Fragment fragment, String fragmentName) {
        Preconditions.checkNotNull(fragment);
        if (!isActivityDetached()) {
            getFragmentManager()
                    .beginTransaction()
                    .replace(resource, fragment, fragmentName)
                    .commitAllowingStateLoss();
        }
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

    protected final boolean isActivityDetached() {
        return getActivity() == null || getActivity().isFinishing();
    }

    protected final void updateDecorView(final Paint paint) {
        if (!isActivityDetached()) {
            getActivity().getWindow().getDecorView()
                    .setLayerType(View.LAYER_TYPE_HARDWARE, paint);
        }
    }


    protected final void onSuperStart() {
        super.onStart();
    }

    protected final void onSuperStop() {
        super.onStart();
    }

    protected abstract View getProgressView();


}
