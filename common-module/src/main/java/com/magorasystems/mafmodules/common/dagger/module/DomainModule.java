package com.magorasystems.mafmodules.common.dagger.module;

import com.magorasystems.mafmodules.common.utils.SchedulersUtils;
import com.magorasystems.mafmodules.common.utils.rx.ApplicationScheduler;

import dagger.Module;
import dagger.Provides;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
@Module
public class DomainModule implements BaseModule {

    @Provides
    public SchedulersUtils.CoreScheduler providerScheduler() {
        return new ApplicationScheduler();
    }
}
