package com.magorasystems.mafmodules.interactor.impl;

import com.magorasystems.mafmodules.common.utils.SchedulersUtils;
import com.magorasystems.android.module.social.interactor.AbstractSocialInteractor;
import com.magorasystems.android.module.social.interactor.SimpleSocialInteractor;
import com.magorasystems.android.module.social.provider.SimpleSocialProvider;
import com.magorasystems.android.module.social.provider.SocialProvider;
import com.magorasystems.protocolapi.auth.dto.response.StringAuthInfo;

import javax.inject.Inject;

/**
 * Developed 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class SimpleSocialInteractorImpl extends AbstractSocialInteractor<String, StringAuthInfo>
        implements SimpleSocialInteractor {

    private SimpleSocialProvider provider;

    @Inject
    public SimpleSocialInteractorImpl(SchedulersUtils.CoreScheduler scheduler, SimpleSocialProvider provider) {
        super(scheduler);
        this.provider = provider;
    }

    @Override
    protected SocialProvider<String, StringAuthInfo> getProvider() {
        return provider;
    }
}
