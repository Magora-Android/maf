package com.magorasystems.mafmodules.interactor;

import com.magorasystems.mafmodules.common.mvp.interactor.CommonInteractor;
import com.magorasystems.mafmodules.common.utils.SchedulersUtils;
import com.magorasystems.mafmodules.model.social.RxCommonSocial;
import com.magorasystems.mafmodules.provider.social.SocialProvider;
import com.magorasystems.protocolapi.auth.dto.response.AuthInfo;
import com.magorasystems.protocolapi.auth.dto.response.AuthResponseData;

import java.io.Serializable;

import rx.Subscriber;

/**
 * Developed 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public abstract class AbstractSocialInteractor<ID extends Serializable> extends CommonInteractor<AuthResponseData<? extends AuthInfo<ID>>> implements SocialInteractor<ID> {

    protected AbstractSocialInteractor(SchedulersUtils.CoreScheduler scheduler) {
        super(scheduler);
    }

    @Override
    public void executeSocialAuthorization(RxCommonSocial social, Subscriber<AuthResponseData<? extends AuthInfo<ID>>> subscriber) {
        execute(getProvider().authorization(social), subscriber);
    }

    protected abstract SocialProvider<ID> getProvider();
}
