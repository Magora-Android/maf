package com.magorasystems.mafmodules.common.ui.activity;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.IdRes;

import com.magorasystems.mafmodules.common.application.ComponentApplication;
import com.magorasystems.mafmodules.common.mvp.router.BaseRouter;
import com.magorasystems.mafmodules.common.ui.fragment.BaseFragmentImpl;
import com.magorasystems.mafmodules.common.ui.widget.WidgetUtils;
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


    @Override
    @SuppressWarnings("unchecked")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        unbinder = ButterKnife.bind(this);
        inject(((ComponentApplication<COMPONENT>) getApplication()));
    }

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

    @Override
    public void onBack() {
        onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }

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


}
