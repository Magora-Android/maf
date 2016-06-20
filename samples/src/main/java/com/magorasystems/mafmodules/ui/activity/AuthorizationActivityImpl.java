package com.magorasystems.mafmodules.ui.activity;

import com.magorasystems.mafmodules.application.SampleApplication;
import com.magorasystems.mafmodules.authmodule.activity.AuthorizationActivity;
import com.magorasystems.mafmodules.authmodule.dagger.component.AuthComponent;
import com.magorasystems.mafmodules.authmodule.fragment.AuthorizationFragment;
import com.magorasystems.mafmodules.ui.fragment.AuthorizationFragmentImpl;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class AuthorizationActivityImpl extends AuthorizationActivity {

    @Override
    protected AuthorizationFragment makeFragment() {
        return AuthorizationFragmentImpl.makeFragment();
    }

    @Override
    protected AuthComponent createComponent() {
        return (AuthComponent) SampleApplication.get(this).getComponent(AuthComponent.class.getSimpleName());
    }
}
