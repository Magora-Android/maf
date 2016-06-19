package com.magorasystems.mafmodules.authmodule.application;

import com.magorasystems.mafmodules.authmodule.dagger.DaggerTestInner;
import com.magorasystems.mafmodules.authmodule.dagger.component.AuthComponent;
import com.magorasystems.mafmodules.common.application.ApplicationSettings;
import com.magorasystems.mafmodules.common.application.BaseComponentApplication;
import com.magorasystems.mafmodules.common.application.CommonApplicationSettings;
import com.magorasystems.mafmodules.common.dagger.component.CommonModuleComponent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class MockAuthApplication extends BaseComponentApplication<AuthComponent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(MockAuthApplication.class);

    @Override
    public Object getComponent(String key) {
        if (key.equals(AuthComponent.class.getSimpleName())) {
            return getComponent();
        }
        if (key.equals(CommonModuleComponent.class.getSimpleName())) {
            return getComponent().commonModuleComponent();
        }
        return null;
    }

    @Override
    public void onCreate() {
        LOGGER.info("Mock application is running...");
        super.onCreate();
    }

    @Override
    protected ApplicationSettings buildApplicationSettings() {
        return new CommonApplicationSettings.Builder()
                .apiHost("http://test.ru")
                .apiPath("path")
                .apiVersion("v2")
                .maxRequestCount(2)
                .networkDelayAttempt(2000L)
                .networkRetryCount(1)
                .build();
    }

    @Override
    protected void buildGraphAndInject() {
        setComponent(DaggerTestInner.buildGraph(this));

    }
}
