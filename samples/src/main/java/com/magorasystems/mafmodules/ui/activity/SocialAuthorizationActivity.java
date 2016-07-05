package com.magorasystems.mafmodules.ui.activity;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;

import com.magorasystems.mafmodules.R;
import com.magorasystems.mafmodules.application.SampleApplication;
import com.magorasystems.mafmodules.common.ui.activity.CommonBaseActivity;
import com.magorasystems.mafmodules.common.ui.fragment.BaseFragmentImpl;
import com.magorasystems.mafmodules.common.ui.fragment.BaseSupportFragmentImpl;
import com.magorasystems.mafmodules.dagger.component.SampleComponent;
import com.magorasystems.mafmodules.router.SocialRouter;
import com.magorasystems.mafmodules.ui.fragment.SocialAuthorizationFragment;
import com.magorasystems.protocolapi.auth.dto.response.AuthInfo;


/**
 * Developed 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class SocialAuthorizationActivity extends CommonBaseActivity<SampleComponent> implements SocialRouter<String> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFragment(SocialAuthorizationFragment.makeFragment());
    }

    @Override
    protected SampleComponent createComponent() {
        return (SampleComponent) SampleApplication.get(this).getComponent();
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

    }

    @Override
    public void onShowError(Throwable throwable) {

    }

    @Override
    public void onAfterSocialAuth(AuthInfo<String> authInfo) {

    }

    @Override
    public void inject(SampleComponent sampleComponent) {
        sampleComponent.inject(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Fragment fragment = getFragmentManager().findFragmentById(R.id.fragment_container);
        if (fragment != null) {
            fragment.onActivityResult(requestCode, resultCode, data);
        }
    }
}
