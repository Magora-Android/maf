package com.magorasystems.mafmodules.mvp.interactor;

import com.magorasystems.mafmodules.authmodule.interactor.BaseAuthInteractor;
import com.magorasystems.mafmodules.common.utils.SchedulersUtils;
import com.magorasystems.mafmodules.common.utils.component.HasComponent;
import com.magorasystems.mafmodules.dagger.component.SampleComponent;
import com.magorasystems.mafmodules.mvp.provider.impl.SimpleAuthProvider;
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
    public SimpleAuthInteractorImpl(HasComponent<? extends SampleComponent> hasComponent, SchedulersUtils.CoreScheduler scheduler) {
        super(scheduler);
        inject(hasComponent);
    }

    @Override
    protected SimpleAuthProvider getAuthProvider() {
        return authProvider;
    }

    @Override
    public void inject(HasComponent<? extends SampleComponent> hasComponent) {
        hasComponent.getComponent().inject(this);
    }
}
