package com.magorasystems.mafmodules.interactor.impl;

import com.magorasystems.mafmodules.common.utils.SchedulersUtils;
import com.magorasystems.mafmodules.interactor.AbstractSocialInteractor;
import com.magorasystems.mafmodules.provider.social.SimpleSocialProvider;
import com.magorasystems.mafmodules.provider.social.SocialProvider;

/**
 * Developed 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class SimpleSocialInteractorImpl extends AbstractSocialInteractor<String> {

    private SimpleSocialProvider provider;

    public SimpleSocialInteractorImpl(SchedulersUtils.CoreScheduler scheduler, SimpleSocialProvider provider) {
        super(scheduler);
        this.provider = provider;
    }

    @Override
    protected SocialProvider<String> getProvider() {
        return provider;
    }
}
