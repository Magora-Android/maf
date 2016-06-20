package com.magorasystems.mafmodules.application;

import com.magorasystems.mafmodules.dagger.DaggerTestInner;
import com.magorasystems.mafmodules.dagger.component.SampleComponent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class MockAuthApplication extends SampleApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(MockAuthApplication.class);


    @Override
    public void onCreate() {
        LOGGER.info("Mock application is running...");
        super.onCreate();
    }

    @Override
    protected void buildGraphAndInject() {
        //final CommonModuleComponent commonModuleComponent = AuthDaggerInner.buildCommonModuleComponent(this);
        final SampleComponent sampleComponent = DaggerTestInner.buildGraph(this);
        subcomponents.put(SampleComponent.class.getSimpleName(), sampleComponent);
        //subcomponents.put(CommonModuleComponent.class.getSimpleName(), commonModuleComponent);
        //subcomponents.put(AuthComponent.class.getSimpleName(), AuthDaggerInner.buildGraph(commonModuleComponent));
        setComponent(sampleComponent);
        getComponent().inject(this);
    }

    public void putComponent(String name, Object component) {
        if (subcomponents.containsKey(name)) {
            subcomponents.remove(name);
        }
        subcomponents.put(name, component);
    }
}
