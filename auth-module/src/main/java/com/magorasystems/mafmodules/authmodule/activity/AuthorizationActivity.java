package com.magorasystems.mafmodules.authmodule.activity;

import android.os.Bundle;

import com.magorasystems.mafmodules.authmodule.R;
import com.magorasystems.mafmodules.authmodule.dagger.component.AuthComponent;
import com.magorasystems.mafmodules.authmodule.router.AuthRouter;
import com.magorasystems.mafmodules.common.ui.activity.CommonBaseActivity;
import com.magorasystems.mafmodules.common.ui.fragment.BaseFragmentImpl;
import com.magorasystems.mafmodules.common.ui.fragment.BaseSupportFragmentImpl;
import com.magorasystems.mafmodules.common.ui.widget.WidgetUtils;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public abstract class AuthorizationActivity extends CommonBaseActivity<AuthComponent> implements AuthRouter {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAfterAuth() {
        WidgetUtils.hideSoftKeyboard(this);
    }

    @Override
    protected int getResourceLayout() {
        return R.layout.activity_common;
    }

    @Override
    protected void setFragment(BaseFragmentImpl fragment) {
        setFragment(R.id.fragment_container, fragment);
    }

    @Override
    protected void setSupportFragment(BaseSupportFragmentImpl fragment) {
        setSupportFragment(R.id.fragment_container, fragment);
    }

    @Override
    public void inject(AuthComponent component) {
        component.inject(this);
    }

    protected abstract BaseFragmentImpl makeFragment();

}
