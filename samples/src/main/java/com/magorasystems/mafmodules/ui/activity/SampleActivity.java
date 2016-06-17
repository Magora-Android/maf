package com.magorasystems.mafmodules.ui.activity;

import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.magorasystems.mafmodules.R;
import com.magorasystems.mafmodules.application.SampleApplication;
import com.magorasystems.mafmodules.common.SomeClass;
import com.magorasystems.mafmodules.common.ui.activity.ActivityNavigator;
import com.magorasystems.mafmodules.common.utils.SchedulersUtils;
import com.magorasystems.mafmodules.dagger.component.SampleComponent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class SampleActivity extends AppCompatActivity {

    private static final Logger LOGGER = LoggerFactory.getLogger(SampleActivity.class);

    @Inject
    protected LocationManager locationManager;

    @Inject
    protected SchedulersUtils.CoreScheduler coreScheduler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);
        SampleComponent sampleComponent = ((SampleComponent) SampleApplication.get(this).getComponent());
        sampleComponent.inject(this);
        SomeClass claz = new SomeClass(sampleComponent.commonModuleComponent());
        LOGGER.debug("someclass: {} ", claz);
        LOGGER.debug("coreScheduler: {} ", coreScheduler);

        findViewById(R.id.action_go_auth).setOnClickListener(v ->
                ActivityNavigator.startGenericActivity(SampleActivity.this, AuthorizationActivity.class, null, false, 0));

    }
}
