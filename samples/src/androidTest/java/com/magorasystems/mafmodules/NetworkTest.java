package com.magorasystems.mafmodules;

import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.magorasystems.mafmodules.network.config.ServerEndpoint;
import com.magorasystems.mafmodules.network.interceptor.HeaderInterceptor;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class NetworkTest extends BaseTest {

    @Inject
    protected ServerEndpoint serverEndpoint;

    @Inject
    protected HeaderInterceptor headerInterceptor;


    @Override
    public void setUp() throws Exception {
        super.setUp();
        getComponent().inject(this);
    }

    @Test
    public void testDaggerInit() throws Exception {
        Assert.assertNotNull("ServerEndpoint ", serverEndpoint);
        Assert.assertNotNull("HeaderInterceptor ", headerInterceptor);
    }


}
