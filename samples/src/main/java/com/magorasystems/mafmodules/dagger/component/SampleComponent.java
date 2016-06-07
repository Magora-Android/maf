package com.magorasystems.mafmodules.dagger.component;

import com.magorasystems.mafmodules.application.SampleApplication;
import com.magorasystems.mafmodules.common.dagger.component.CommonModuleComponent;
import com.magorasystems.mafmodules.dagger.scope.ApplicationScope;

import dagger.Component;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */

@Component(dependencies = CommonModuleComponent.class)
@ApplicationScope
public interface SampleComponent {

    void inject(SampleApplication application);
}
