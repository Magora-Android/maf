package com.magorasystems.mafmodules.application;

import android.location.LocationManager;

import com.magorasystems.mafmodules.common.application.BaseComponentApplication;
import com.magorasystems.mafmodules.common.dagger.component.CommonModuleComponent;
import com.magorasystems.mafmodules.common.dagger.component.DaggerCommonModuleComponent;
import com.magorasystems.mafmodules.common.dagger.module.ApplicationModule;
import com.magorasystems.mafmodules.dagger.component.DaggerSampleComponent;
import com.magorasystems.mafmodules.dagger.component.SampleComponent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class SampleApplication extends BaseComponentApplication<SampleComponent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(SampleApplication.class);

    @Inject
    protected LocationManager locationManager;

    @Override
    public void onCreate() {
        super.onCreate();
        LOGGER.debug("location manager is init {}", locationManager != null);
    }

    @Override
    protected void buildGraphAndInject() {
        CommonModuleComponent component = DaggerCommonModuleComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        SampleComponent sampleComponent = DaggerSampleComponent.builder()
                .commonModuleComponent(component)
                .build();
        sampleComponent.inject(this);
        setComponent(sampleComponent);

    }
}
