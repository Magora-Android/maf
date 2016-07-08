package com.magorasystems.android.module.social.interactor;

import com.magorasystems.mafmodules.common.mvp.interactor.CommonInteractor;
import com.magorasystems.mafmodules.common.utils.SchedulersUtils;
import com.magorasystems.android.module.social.model.RxCommonSocial;
import com.magorasystems.android.module.social.provider.SocialProvider;
import com.magorasystems.protocolapi.auth.dto.response.AuthInfo;

import java.io.Serializable;

import rx.Observable;
import rx.Subscriber;

/**
 * Developed 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public abstract class AbstractSocialInteractor<ID extends Serializable,M  extends AuthInfo<ID>> extends CommonInteractor<M> implements SocialInteractor<ID, M> {

    protected AbstractSocialInteractor(SchedulersUtils.CoreScheduler scheduler) {
        super(scheduler);
    }

    @Override
    public void executeSocialAuthorization(RxCommonSocial social, Subscriber<M> subscriber) {
        execute(getProvider().authorization(social), subscriber);
    }

    @Override
    public void execute(Observable<? extends M> observer, Subscriber<M> subscriber) {
        super.execute(observer, subscriber);
    }

    protected abstract SocialProvider<ID,M> getProvider();
}
