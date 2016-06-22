package com.magorasystems.mafmodules.ui.activity;

import android.os.Bundle;

import com.magorasystems.mafmodules.application.SampleApplication;
import com.magorasystems.mafmodules.authmodule.activity.AuthorizationActivity;
import com.magorasystems.mafmodules.authmodule.dagger.component.AuthComponent;
import com.magorasystems.mafmodules.authmodule.fragment.AuthorizationModuleFragment;
import com.magorasystems.mafmodules.ui.fragment.AuthorizationModuleFragmentImpl;
import com.magorasystems.mafmodules.ui.fragment.AuthorizationModuleSupportFragmentImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class AuthorizationModuleActivity extends AuthorizationActivity {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorizationModuleActivity.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setFragment(makeFragment());
        setSupportFragment(AuthorizationModuleSupportFragmentImpl.makeFragment());
    }

    @Override
    protected AuthorizationModuleFragment makeFragment() {
        return AuthorizationModuleFragmentImpl.makeFragment();
    }

    @Override
    public void onRecoverPassword() {
        LOGGER.debug("onRecoverPassword");
    }

    @Override
    protected AuthComponent createComponent() {
        return (AuthComponent) SampleApplication.get(this)
                .getComponent(AuthComponent.class.getSimpleName());
    }

    @Override
    public void onShowError(Throwable throwable) {
        LOGGER.error("onShowError ", throwable);
    }
}
