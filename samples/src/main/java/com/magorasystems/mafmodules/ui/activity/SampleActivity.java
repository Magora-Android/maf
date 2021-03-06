package com.magorasystems.mafmodules.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.magorasystems.mafmodules.R;
import com.magorasystems.mafmodules.application.SampleApplication;
import com.magorasystems.mafmodules.common.dagger.component.CommonComponentProvider;
import com.magorasystems.mafmodules.common.dagger.component.CommonModuleComponent;
import com.magorasystems.mafmodules.common.ui.activity.ActivityNavigator;
import com.magorasystems.mafmodules.dagger.component.SampleComponent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class SampleActivity extends AppCompatActivity {

    private static final Logger LOGGER = LoggerFactory.getLogger(SampleActivity.class);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);
        SampleComponent sampleComponent = ((SampleComponent) SampleApplication.get(this).getComponent());
        sampleComponent.inject(this);
        final CommonComponentProvider provider = new CommonComponentProvider((CommonModuleComponent)
                SampleApplication.get(this).getComponent(CommonModuleComponent.class.getSimpleName()));
        LOGGER.debug("coreScheduler: {} ", provider.getCoreScheduler());
        LOGGER.debug("LocationManager: {}", provider.getLocationManager());

        findViewById(R.id.action_go_auth).setOnClickListener(v ->
                ActivityNavigator.startGenericActivity(SampleActivity.this, AuthorizationActivityImpl.class, null, false, 0));
        findViewById(R.id.action_go_module_auth).setOnClickListener(v ->
                ActivityNavigator.startGenericActivity(SampleActivity.this, AuthorizationModuleActivity.class, null, false, 0));

        findViewById(R.id.action_go_take_photo).setOnClickListener(v ->
                ActivityNavigator.startGenericActivity(SampleActivity.this, RegistrationActivity.class, null, false, 0));

        findViewById(R.id.action_go_profile).setOnClickListener(v ->
                ActivityNavigator.startGenericActivity(SampleActivity.this, ProfileActivity.class, null, false, 0));

        findViewById(R.id.action_go_digital_keyboard).setOnClickListener(v ->
                ActivityNavigator.startGenericActivity(SampleActivity.this, DigitalKeyboardActivity.class, null, false, 0));

        findViewById(R.id.action_go_social).setOnClickListener(v ->
                ActivityNavigator.startGenericActivity(SampleActivity.this, SocialAuthorizationActivity.class, null, false, 0));
    }
}
