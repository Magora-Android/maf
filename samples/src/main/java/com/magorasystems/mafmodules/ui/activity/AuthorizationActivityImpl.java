package com.magorasystems.mafmodules.ui.activity;

import android.os.Bundle;

import com.magorasystems.mafmodules.application.SampleApplication;
import com.magorasystems.mafmodules.authmodule.activity.AuthorizationActivity;
import com.magorasystems.mafmodules.authmodule.dagger.component.AuthComponent;
import com.magorasystems.mafmodules.authmodule.fragment.AuthorizationFragment;
import com.magorasystems.mafmodules.ui.fragment.AuthorizationFragmentImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class AuthorizationActivityImpl extends AuthorizationActivity {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorizationActivityImpl.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFragment(makeFragment());
    }

    @Override
    protected AuthorizationFragment makeFragment() {
        return AuthorizationFragmentImpl.makeFragment();
    }

    @Override
    protected AuthComponent createComponent() {
        return (AuthComponent) SampleApplication.get(this)
                .getComponent(AuthComponent.class.getSimpleName());
    }

    @Override
    public void onRecoverPassword() {
        LOGGER.debug("onRecoverPassword");
    }

    @Override
    public void onShowError(Throwable throwable) {
        LOGGER.error("onShowError ", throwable);
    }
}
