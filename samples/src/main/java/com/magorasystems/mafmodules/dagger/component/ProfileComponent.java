package com.magorasystems.mafmodules.dagger.component;

import com.magorasystems.mafmodules.common.dagger.component.CommonModuleComponent;
import com.magorasystems.mafmodules.dagger.module.ProfileInteractorModule;
import com.magorasystems.mafmodules.dagger.module.ProfileNetworkModule;
import com.magorasystems.mafmodules.dagger.module.ProfileProviderModule;
import com.magorasystems.mafmodules.dagger.scope.ProfileScope;
import com.magorasystems.mafmodules.interactor.impl.SimpleProfileInteractor;
import com.magorasystems.mafmodules.interactor.impl.SimpleProfileInteractorImpl;
import com.magorasystems.mafmodules.provider.SimpleRestProfileProvider;
import com.magorasystems.mafmodules.provider.impl.SimpleRestProfileProviderImpl;

import dagger.Component;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
@Component(dependencies = {CommonModuleComponent.class},
           modules = {
                   ProfileNetworkModule.class,
                   ProfileProviderModule.class,
                   ProfileInteractorModule.class
           })
@ProfileScope
public interface ProfileComponent {

    SimpleRestProfileProvider getRestApiProvider();

    SimpleProfileInteractor getProfileInteractor();

    void inject(ProfileComponentProvider provider);

    void inject(SimpleRestProfileProviderImpl provider);

    void inject(SimpleProfileInteractorImpl i);
}
