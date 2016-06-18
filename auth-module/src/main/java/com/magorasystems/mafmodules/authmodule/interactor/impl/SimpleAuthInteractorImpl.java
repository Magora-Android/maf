package com.magorasystems.mafmodules.authmodule.interactor.impl;

import com.magorasystems.mafmodules.authmodule.dagger.component.AuthComponent;
import com.magorasystems.mafmodules.authmodule.interactor.BaseAuthInteractor;
import com.magorasystems.mafmodules.authmodule.provider.impl.SimpleAuthProvider;
import com.magorasystems.mafmodules.common.utils.SchedulersUtils;
import com.magorasystems.mafmodules.common.utils.component.HasComponent;
import com.magorasystems.protocolapi.auth.dto.response.StringAuthInfo;

import javax.inject.Inject;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class SimpleAuthInteractorImpl extends BaseAuthInteractor<StringAuthInfo> implements SimpleAuthInteractor {

    @Inject
    protected SimpleAuthProvider authProvider;

    @Inject
    public SimpleAuthInteractorImpl(HasComponent<? extends AuthComponent> hasComponent, SchedulersUtils.CoreScheduler scheduler) {
        super(scheduler);
        inject(hasComponent);
    }

    @Override
    protected SimpleAuthProvider getAuthProvider() {
        return authProvider;
    }

    @Override
    public void inject(HasComponent<? extends AuthComponent> hasComponent) {
        hasComponent.getComponent().inject(this);
    }
}
