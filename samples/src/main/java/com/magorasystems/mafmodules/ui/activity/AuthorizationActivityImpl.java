package com.magorasystems.mafmodules.ui.activity;

import com.magorasystems.mafmodules.authmodule.activity.AuthorizationActivity;
import com.magorasystems.mafmodules.authmodule.fragment.AuthorizationFragment;
import com.magorasystems.mafmodules.dagger.component.SampleComponent;
import com.magorasystems.mafmodules.ui.fragment.AuthorizationFragmentImpl;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class AuthorizationActivityImpl extends AuthorizationActivity<SampleComponent> {

    @Override
    protected AuthorizationFragment<SampleComponent> makeFragment() {
        return AuthorizationFragmentImpl.makeFragment();
    }

    @Override
    public void inject(SampleComponent sampleComponent) {
        sampleComponent.inject(this);
    }
}
