package com.magorasystems.mafmodules;

import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.SmallTest;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S. Bolkonsky
 */
@RunWith(AndroidJUnit4.class)
@SmallTest
public class DaggerTest extends BaseTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(DaggerTest.class);

    @Override
    public void setUp() throws Exception {
        LOGGER.info("setUp");
        super.setUp();
        getComponent().inject(this);
    }

    @Test
    public void testDaggerInitialization() {
        Assert.assertNotNull("Application ", application);
        Assert.assertNotNull("NetworkConnectionManager ", networkConnectionManager);
    }
}
