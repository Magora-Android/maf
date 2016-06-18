package com.magorasystems.mafmodules.authmodule.dagger.module;

import android.content.Context;

import com.magorasystems.mafmodules.authmodule.dagger.component.AuthComponent;
import com.magorasystems.mafmodules.authmodule.network.AuthApiClientWrapper;
import com.magorasystems.mafmodules.authmodule.network.provider.AuthRestProvider;
import com.magorasystems.mafmodules.authmodule.provider.impl.SimpleAuthProvider;
import com.magorasystems.mafmodules.common.dagger.module.BaseModule;
import com.magorasystems.mafmodules.common.utils.SchedulersUtils;
import com.magorasystems.mafmodules.common.utils.component.HasComponent;

import dagger.Module;
import dagger.Provides;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
@Module
public class AuthProviderModule implements BaseModule {

    @Provides
    protected SimpleAuthProvider providerAuthRestProvider(Context application,
                                                          SchedulersUtils.CoreScheduler scheduler,
                                                          AuthApiClientWrapper clientWrapper) {
        return new AuthRestProvider(((HasComponent<AuthComponent>) ((HasComponent<?>) application).getComponent())
                , scheduler, clientWrapper);
    }
}
