package com.magorasystems.mafmodules.dagger.component;

import com.magorasystems.mafmodules.common.dagger.component.CommonModuleComponent;
import com.magorasystems.mafmodules.dagger.module.ProfileNetworkModule;
import com.magorasystems.mafmodules.dagger.scope.ProfileScope;
import com.magorasystems.mafmodules.provider.impl.SimpleRestProfileProvider;

import dagger.Component;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
@Component(dependencies = {CommonModuleComponent.class},
           modules = {
                   ProfileNetworkModule.class
           })
@ProfileScope
public interface ProfileComponent {

    void inject(ProfileComponentProvider provider);

    void inject(SimpleRestProfileProvider provider);
}
