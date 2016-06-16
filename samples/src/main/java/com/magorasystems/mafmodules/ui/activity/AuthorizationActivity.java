package com.magorasystems.mafmodules.ui.activity;

import android.os.Bundle;

import com.magorasystems.mafmodules.R;
import com.magorasystems.mafmodules.authmodule.router.AuthRouter;
import com.magorasystems.mafmodules.common.ui.activity.CommonBaseActivity;
import com.magorasystems.mafmodules.common.ui.fragment.BaseFragmentImpl;
import com.magorasystems.mafmodules.common.ui.widget.WidgetUtils;
import com.magorasystems.mafmodules.common.utils.component.HasComponent;
import com.magorasystems.mafmodules.dagger.component.SampleComponent;
import com.magorasystems.mafmodules.ui.fragment.AuthorizationFragmentImpl;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class AuthorizationActivity extends CommonBaseActivity<SampleComponent> implements AuthRouter {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFragment(AuthorizationFragmentImpl.makeFragment());
    }

    @Override
    public void onAfterAuth() {
        WidgetUtils.hideSoftKeyboard(this);
    }

    @Override
    public void inject(HasComponent<? extends SampleComponent> hasComponent) {
        hasComponent.getComponent().inject(this);
    }

    @Override
    protected int getResourceLayout() {
        return R.layout.activity_common;
    }

    @Override
    protected void setFragment(BaseFragmentImpl fragment) {
        setFragment(R.id.fragment_container, fragment);
    }

}
