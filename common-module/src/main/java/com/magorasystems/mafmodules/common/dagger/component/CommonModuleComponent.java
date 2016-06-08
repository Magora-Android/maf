package com.magorasystems.mafmodules.common.dagger.component;

import android.content.Context;
import android.location.LocationManager;

import com.magorasystems.mafmodules.common.SomeClass;
import com.magorasystems.mafmodules.common.dagger.module.ApplicationModule;
import com.magorasystems.mafmodules.common.dagger.module.DomainModule;
import com.magorasystems.mafmodules.common.dagger.scope.CommonScope;
import com.magorasystems.mafmodules.common.utils.SchedulersUtils;

import dagger.Component;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
@CommonScope
@Component(
        modules = {
                ApplicationModule.class,
                DomainModule.class
        }
)
public interface CommonModuleComponent {

    LocationManager locationManager();

    Context context();

    SchedulersUtils.CoreScheduler coreScheduler();

    void inject(SomeClass someClass);


}
