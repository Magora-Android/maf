package com.magorasystems.mafmodules.application;

import com.magorasystems.mafmodules.dagger.DaggerTestInner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class MockSampleApplication extends SampleApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(MockSampleApplication.class);

    @Override
    public Object getComponent(String key) {
        return null;
    }

    @Override
    public void onCreate() {
        LOGGER.info("Mock application is running...");
        super.onCreate();
    }

    @Override
    protected void buildGraphAndInject() {
        setComponent(DaggerTestInner.buildGraph(this));
        //getComponent().inject(this);

    }
}
