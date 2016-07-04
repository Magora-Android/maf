package com.magorasystems.mafmodules.dagger.module;

import android.content.Context;

import com.magorasystems.mafmodules.common.dagger.module.BaseModule;
import com.magorasystems.mafmodules.common.utils.SchedulersUtils;
import com.magorasystems.mafmodules.dagger.scope.ProfileScope;
import com.magorasystems.mafmodules.interactor.SimpleProfileInteractor;
import com.magorasystems.mafmodules.interactor.impl.SimpleProfileInteractorImpl;
import com.magorasystems.mafmodules.provider.SimpleRestProfileProvider;

import dagger.Module;
import dagger.Provides;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
@Module
public class ProfileInteractorModule implements BaseModule {

    @Provides
    @ProfileScope
    protected SimpleProfileInteractor providerSimpleProfileInteractor(Context application,
                                                                      SchedulersUtils.CoreScheduler scheduler,
                                                                      SimpleRestProfileProvider profileProvider) {
        return new SimpleProfileInteractorImpl(application, scheduler, profileProvider);
    }

}
