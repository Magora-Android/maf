package com.magorasystems.mafmodules.application;

import android.content.Context;
import android.location.LocationManager;

import com.magorasystems.mafmodules.BuildConfig;
import com.magorasystems.mafmodules.common.application.ApplicationSettings;
import com.magorasystems.mafmodules.common.application.BaseComponentApplication;
import com.magorasystems.mafmodules.common.application.CommonApplicationSettings;
import com.magorasystems.mafmodules.common.dagger.component.CommonModuleComponent;
import com.magorasystems.mafmodules.common.dagger.component.DaggerCommonModuleComponent;
import com.magorasystems.mafmodules.common.dagger.module.ApplicationModule;
import com.magorasystems.mafmodules.dagger.component.DaggerSampleComponent;
import com.magorasystems.mafmodules.dagger.component.SampleComponent;

import org.apache.commons.lang3.StringUtils;
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

    @Inject
    protected Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        LOGGER.debug("location manager is init {}", locationManager != null);
        LOGGER.debug("Inject Context {}", context);
        LOGGER.debug("{}", StringUtils.isBlank(""));
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

    @Override
    protected ApplicationSettings buildApplicationSettings() {
        return new CommonApplicationSettings.Builder()
                .apiHost(BuildConfig.REST_API_HOST)
                .apiPath(BuildConfig.REST_API_PATH)
                .apiVersion(BuildConfig.REST_API_VERSION)
                .maxRequestCount(BuildConfig.REQUESTS_COUNT)
                .networkDelayAttempt(BuildConfig.NETWORK_DELAY_ATTEMPT)
                .networkRetryCount(BuildConfig.NETWORK_RETRY_COUNT)
                .build();

    }
}
