package com.magorasystems.mafmodules.profile.interactor;

import com.magorasystems.mafmodules.common.mvp.interactor.CommonInteractor;
import com.magorasystems.mafmodules.common.utils.SchedulersUtils;
import com.magorasystems.mafmodules.profile.provider.ProfileDataProvider;

import rx.Subscriber;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public abstract class GenericProfileInteractor<R> extends CommonInteractor<R> implements ProfileInteractor<R> {

    protected GenericProfileInteractor(SchedulersUtils.CoreScheduler scheduler) {
        super(scheduler);
    }

    @Override
    public void executeMyProfile(Subscriber<R> subscriber) {
        execute(getProfileProvider().getMyProfile(), subscriber);
    }

    protected abstract ProfileDataProvider<? extends R> getProfileProvider();
}
