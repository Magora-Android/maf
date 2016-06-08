package com.magorasystems.mafmodules.dagger.component;

import com.magorasystems.mafmodules.AuthRestProvider;
import com.magorasystems.mafmodules.application.SampleApplication;
import com.magorasystems.mafmodules.common.dagger.component.CommonModuleComponent;
import com.magorasystems.mafmodules.dagger.module.AuthNetworkModule;
import com.magorasystems.mafmodules.dagger.module.SampleModule;
import com.magorasystems.mafmodules.dagger.scope.ApplicationScope;
import com.magorasystems.mafmodules.ui.activity.SampleActivity;

import dagger.Component;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */

@Component(dependencies = CommonModuleComponent.class,
           modules = {SampleModule.class, AuthNetworkModule.class})
@ApplicationScope
public interface SampleComponent {

    CommonModuleComponent commonModuleComponent();

    void inject(SampleApplication application);

    void inject(SampleActivity activity);

    void inject(AuthRestProvider provider);
}
