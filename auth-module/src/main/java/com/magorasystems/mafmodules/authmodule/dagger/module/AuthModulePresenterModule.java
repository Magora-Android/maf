package com.magorasystems.mafmodules.authmodule.dagger.module;

import android.content.Context;

import com.magorasystems.mafmodules.authmodule.dagger.scope.AuthScope;
import com.magorasystems.mafmodules.authmodule.module.impl.AuthModulePresenter;
import com.magorasystems.mafmodules.authmodule.module.impl.AuthModulePresenterImp;
import com.magorasystems.mafmodules.common.dagger.module.BaseModule;

import dagger.Module;
import dagger.Provides;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
@Module
public class AuthModulePresenterModule implements BaseModule {

    @Provides
    @AuthScope
    public AuthModulePresenter providerAuthModulePresenter(Context application) {
        return new AuthModulePresenterImp(application);
    }
}
