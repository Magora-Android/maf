package com.magorasystems.mafmodules.application;

import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.magorasystems.mafmodules.BuildConfig;
import com.magorasystems.mafmodules.authmodule.dagger.AuthDaggerInner;
import com.magorasystems.mafmodules.authmodule.dagger.component.AuthComponent;
import com.magorasystems.mafmodules.common.application.ApplicationSettings;
import com.magorasystems.mafmodules.common.application.BaseComponentApplication;
import com.magorasystems.mafmodules.common.application.CommonApplicationSettings;
import com.magorasystems.mafmodules.common.dagger.component.CommonModuleComponent;
import com.magorasystems.mafmodules.dagger.component.DaggerProfileComponent;
import com.magorasystems.mafmodules.dagger.component.DaggerSampleComponent;
import com.magorasystems.mafmodules.dagger.component.DaggerSocialComponent;
import com.magorasystems.mafmodules.dagger.component.ProfileComponent;
import com.magorasystems.mafmodules.dagger.component.ProfileComponentProvider;
import com.magorasystems.mafmodules.dagger.component.SampleComponent;
import com.magorasystems.mafmodules.dagger.component.SampleComponents;
import com.magorasystems.mafmodules.dagger.component.SocialComponent;
import com.magorasystems.mafmodules.dagger.module.profile.ProfileNetworkModule;
import com.magorasystems.rx.images.RxImage;
import com.magorasystems.rx.permission.RxResult;

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
    protected Context context;

    private ProfileComponentProvider profileComponentProvider;

    @Override
    public void onCreate() {
        super.onCreate();
        RxResult.register(this);
        RxImage.register(this);
        Fresco.initialize(this);
        LOGGER.debug("Inject Context {}", context);
        LOGGER.debug("{}", StringUtils.isBlank(""));
        LOGGER.debug("ProfileApiClientWrapper {}", profileComponentProvider.getApiClientWrapper());
    }

    @Override
    public Object getComponent(String key) {
        switch (SampleComponents.find(key)) {
            case AUTH_COMPONENT:
            case COMMON_COMPONENT:
            case PROFILE_COMPONENT:
            case SOCIAL_COMPONENT:
                return super.getComponent(key);
            default:
                return getComponent();
        }
    }

    @Override
    protected void buildGraphAndInject() {
        final CommonModuleComponent component = AuthDaggerInner.buildCommonModuleComponent(this);
        final AuthComponent authComponent = AuthDaggerInner.buildGraph(component);
        final SampleComponent sampleComponent = DaggerSampleComponent.builder()
                .commonModuleComponent(component)
                .build();
        final ProfileComponent profileComponent = DaggerProfileComponent.builder()
                .commonModuleComponent(component)
                .profileNetworkModule(new ProfileNetworkModule())
                .build();
        final SocialComponent socialComponent = DaggerSocialComponent.builder()
                .commonModuleComponent(component)
                .build();
        sampleComponent.inject(this);
        setComponent(sampleComponent);
        putComponent(CommonModuleComponent.class.getSimpleName(), component);
        putComponent(AuthComponent.class.getSimpleName(), authComponent);
        putComponent(SampleComponent.class.getSimpleName(), sampleComponent);
        putComponent(ProfileComponent.class.getSimpleName(), profileComponent);
        putComponent(SocialComponent.class.getSimpleName(), socialComponent);
        profileComponentProvider = new ProfileComponentProvider(this);
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
