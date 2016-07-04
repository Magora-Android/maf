package com.magorasystems.mafmodules.interactor.impl;

import com.magorasystems.mafmodules.common.utils.SchedulersUtils;
import com.magorasystems.mafmodules.interactor.AbstractSocialInteractor;
import com.magorasystems.mafmodules.provider.social.SimpleSocialProvider;
import com.magorasystems.mafmodules.provider.social.SocialProvider;
import com.magorasystems.protocolapi.auth.dto.response.StringAuthInfo;

/**
 * Developed 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class SimpleSocialInteractorImpl extends AbstractSocialInteractor<String, StringAuthInfo> {

    private SimpleSocialProvider provider;

    public SimpleSocialInteractorImpl(SchedulersUtils.CoreScheduler scheduler, SimpleSocialProvider provider) {
        super(scheduler);
        this.provider = provider;
    }

    @Override
    protected SocialProvider<String, StringAuthInfo> getProvider() {
        return provider;
    }
}
