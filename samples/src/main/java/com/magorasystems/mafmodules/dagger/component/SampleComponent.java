package com.magorasystems.mafmodules.dagger.component;

import com.magorasystems.mafmodules.application.SampleApplication;
import com.magorasystems.mafmodules.common.dagger.component.CommonModuleComponent;
import com.magorasystems.mafmodules.dagger.module.SampleInteractorModule;
import com.magorasystems.mafmodules.dagger.module.SampleModulePresenterModule;
import com.magorasystems.mafmodules.dagger.module.SampleNetworkModule;
import com.magorasystems.mafmodules.dagger.module.SamplePresenterModule;
import com.magorasystems.mafmodules.dagger.module.SampleProvidersModule;
import com.magorasystems.mafmodules.dagger.module.SampleStoreModule;
import com.magorasystems.mafmodules.dagger.scope.ApplicationScope;
import com.magorasystems.mafmodules.module.SimpleRegistrationPresenterModuleImpl;
import com.magorasystems.mafmodules.provider.registration.SimpleRegistrationProvider;
import com.magorasystems.mafmodules.provider.registration.SimpleRegistrationProviderImpl;
import com.magorasystems.mafmodules.ui.activity.SampleActivity;
import com.magorasystems.mafmodules.ui.activity.SocialAuthorizationActivity;

import dagger.Component;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */

@Component(dependencies = {CommonModuleComponent.class},
           modules = {
                   SampleProvidersModule.class,
                   SampleNetworkModule.class,
                   SampleModulePresenterModule.class,
                   SampleInteractorModule.class,
                   SamplePresenterModule.class,
                   SampleStoreModule.class
           })
@ApplicationScope
public interface SampleComponent {

    SimpleRegistrationProvider getRegistrationProvider();

    void inject(SampleApplication application);

    void inject(SampleActivity activity);

    void inject(SocialAuthorizationActivity activity);

    void inject(SimpleRegistrationProviderImpl p);

    void inject(SimpleRegistrationPresenterModuleImpl m);
}
