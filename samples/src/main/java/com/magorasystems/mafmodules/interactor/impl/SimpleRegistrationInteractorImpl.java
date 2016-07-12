package com.magorasystems.mafmodules.interactor.impl;

import com.magorasystems.mafmodules.common.utils.SchedulersUtils;
import com.magorasystems.mafmodules.interactor.SimpleRegistrationInteractor;
import com.magorasystems.mafmodules.provider.registration.SimpleRegistrationProvider;
import com.magorasystems.protocolapi.auth.dto.response.StringAuthInfo;

/**
 * Developed 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class SimpleRegistrationInteractorImpl extends AbstractRegistrationInteractor<String, StringAuthInfo>
        implements SimpleRegistrationInteractor {

    private SimpleRegistrationProvider provider;

    public SimpleRegistrationInteractorImpl(SchedulersUtils.CoreScheduler scheduler,
                                            SimpleRegistrationProvider provider) {
        super(scheduler);
        this.provider = provider;
    }

    @Override
    protected SimpleRegistrationProvider getProfileProvider() {
        return provider;
    }
}
