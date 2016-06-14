package com.magorasystems.mafmodules.common.dagger.component;

import android.content.Context;
import android.location.LocationManager;

import com.magorasystems.mafmodules.common.SomeClass;
import com.magorasystems.mafmodules.common.dagger.module.ApplicationModule;
import com.magorasystems.mafmodules.common.dagger.module.DomainModule;
import com.magorasystems.mafmodules.common.dagger.module.StorableModule;
import com.magorasystems.mafmodules.common.utils.SchedulersUtils;
import com.magorasystems.mafmodules.network.manager.NetworkConnectionManager;
import com.magorasystems.mafmodules.network.store.StringApiTokenStorage;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
@Component(
        modules = {
                ApplicationModule.class,
                DomainModule.class,
                StorableModule.class
        }
)
@Singleton
public interface CommonModuleComponent {

    LocationManager locationManager();

    NetworkConnectionManager networkConnectionManager();

    Context context();

    SchedulersUtils.CoreScheduler coreScheduler();

    StringApiTokenStorage memoryTokenStorable();

    void inject(SomeClass someClass);


}
