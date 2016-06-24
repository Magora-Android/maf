package com.magorasystems.mafmodules.ui.activity;

import android.os.Bundle;

import com.magorasystems.mafmodules.R;
import com.magorasystems.mafmodules.common.ui.activity.CommonBaseActivity;
import com.magorasystems.mafmodules.common.ui.fragment.BaseFragmentImpl;
import com.magorasystems.mafmodules.common.ui.fragment.BaseSupportFragmentImpl;
import com.magorasystems.mafmodules.dagger.component.SampleComponent;
import com.magorasystems.mafmodules.ui.fragment.ShowProfileFragment;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class ProfileActivity extends CommonBaseActivity<SampleComponent> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFragment(new ShowProfileFragment());
    }

    @Override
    protected SampleComponent createComponent() {
        return null;
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
    public void inject(SampleComponent sampleComponent) {

    }
}
