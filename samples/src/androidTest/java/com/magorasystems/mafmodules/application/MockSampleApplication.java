package com.magorasystems.mafmodules.application;

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
    public void onCreate() {
        LOGGER.info("Mock application is running...");
        super.onCreate();
    }

    @Override
    protected void buildGraphAndInject() {
        super.buildGraphAndInject();
    }

    public void putComponent(String name, Object component) {
        if (subcomponents.containsKey(name)) {
            subcomponents.remove(name);
        }
        subcomponents.put(name, component);
    }
}
