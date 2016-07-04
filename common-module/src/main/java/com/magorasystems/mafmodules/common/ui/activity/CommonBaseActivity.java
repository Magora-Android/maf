package com.magorasystems.mafmodules.common.ui.activity;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.IdRes;

import com.magorasystems.mafmodules.common.mvp.router.BaseRouter;
import com.magorasystems.mafmodules.common.ui.fragment.BaseFragmentImpl;
import com.magorasystems.mafmodules.common.ui.fragment.BaseSupportFragmentImpl;
import com.magorasystems.widgets.WidgetUtils;
import com.magorasystems.mafmodules.common.utils.component.Injectable;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S. Bolkonsky
 */
public abstract class CommonBaseActivity<COMPONENT> extends BaseActivity<COMPONENT> implements Injectable<COMPONENT>, CommonActivity, BaseRouter {

    private Unbinder unbinder;


    protected abstract COMPONENT createComponent();

    /**
     * Bind ButterKnife
     * Set and inject component
     * @param savedInstanceState saved instance state
     */
    @Override
    @SuppressWarnings("unchecked")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        unbinder = ButterKnife.bind(this);
        ButterKnife.setDebug(true);
        setComponent(createComponent());
        inject(getComponent());
    }

    /**
     * If in backstack <= 1 fragment call {@link #supportFinishAfterTransition()} <br>
     * else if {@code popBackStackImmediate()} == true, than just {@link WidgetUtils#hideSoftKeyboard(Activity)} <br>
     * else just call {@code super.onBackPressed()}
     */
    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() <= 1) {
            supportFinishAfterTransition();
            return;
        }
        if (getFragmentManager().popBackStackImmediate()) {
            WidgetUtils.hideSoftKeyboard(this);
            return;
        }
        super.onBackPressed();
    }

    /**
     * Call {@link #onBackPressed()}
     */
    @Override
    public void onBack() {
        onBackPressed();
    }

    /**
     * Unbind ButterKnife and destroy
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }

    /**
     * Replace fragment to container and add to backstack, if activity is not finished
     * @param resource container for fragment
     * @param fragment fragment to replace
     */
    protected void setFragment(@IdRes int resource, final BaseFragmentImpl fragment) {
        if (isFinishing()) {
            return;
        }
        final FragmentTransaction ft = getFragmentManager()
                .beginTransaction();
        ft.replace(resource, fragment)
                .addToBackStack(fragment.getFragmentName())
                .commit();
    }

    /**
     * Replace support fragment to container and add to backstack, if activity is not finished
     * @param resource container for fragment
     * @param fragment fragment to replace
     */
    protected void setSupportFragment(@IdRes int resource, BaseSupportFragmentImpl fragment) {
        if (isFinishing()) {
            return;
        }
        final android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager()
                .beginTransaction();
        ft.replace(resource, fragment)
                .addToBackStack(fragment.getFragmentName())
                .commit();
    }
}
