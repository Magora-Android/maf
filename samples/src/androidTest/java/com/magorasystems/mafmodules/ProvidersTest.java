package com.magorasystems.mafmodules;

import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.SmallTest;

import com.magorasystems.mafmodules.authmodule.provider.impl.SimpleAuthProvider;
import com.magorasystems.mafmodules.common.dagger.module.BaseModule;
import com.magorasystems.mafmodules.network.AuthApiClientWrapper;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
@RunWith(AndroidJUnit4.class)
@SmallTest
public class ProvidersTest extends BaseTest {

    @Inject
    @Named(BaseModule.QUALIFIER_MOCK)
    protected AuthApiClientWrapper mockAuthApiClientWrapper;

    @Inject
    @Named(BaseModule.QUALIFIER_MOCK)
    protected SimpleAuthProvider mockSimpleAuthProvider;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        getComponent().inject(this);
    }

    @Test
    public void testDaggerInit() throws Exception {
        Assert.assertNotNull("AuthApiClientWrapper ", mockAuthApiClientWrapper);
        Assert.assertNotNull("AuthProvider ", mockSimpleAuthProvider);
    }
}
