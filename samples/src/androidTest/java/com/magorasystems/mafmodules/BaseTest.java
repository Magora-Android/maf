package com.magorasystems.mafmodules;

import android.app.Instrumentation;
import android.support.test.InstrumentationRegistry;

import com.magorasystems.mafmodules.application.SampleApplication;
import com.magorasystems.mafmodules.common.utils.SchedulersUtils;
import com.magorasystems.mafmodules.common.utils.component.HasComponent;
import com.magorasystems.mafmodules.dagger.DaggerTestInner;
import com.magorasystems.mafmodules.dagger.TestSampleComponent;
import com.magorasystems.mafmodules.network.manager.NetworkConnectionManager;

import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S. Bolkonsky
 */
public class BaseTest implements HasComponent<TestSampleComponent> {

    protected static final Logger LOGGER = LoggerFactory.getLogger(BaseTest.class);

    @Inject
    protected SampleApplication application;

    @Inject
    protected SchedulersUtils.CoreScheduler coreScheduler;

    @Inject
    protected NetworkConnectionManager networkConnectionManager;

    private TestSampleComponent component;

    @Override
    public TestSampleComponent getComponent() {
        return component;
    }

    @Before
    public void setUp() throws Exception {
        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        SampleApplication app = (SampleApplication) instrumentation.getTargetContext().getApplicationContext();
        component = (TestSampleComponent) DaggerTestInner.buildGraph(app);
        app.setComponent(component);
        component.inject(this);
    }
}
