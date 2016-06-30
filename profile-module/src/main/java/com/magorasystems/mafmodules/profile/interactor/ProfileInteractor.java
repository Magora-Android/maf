package com.magorasystems.mafmodules.profile.interactor;

import com.magorasystems.mafmodules.common.mvp.interactor.BaseInteractor;

import rx.Subscriber;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public interface ProfileInteractor<R> extends BaseInteractor<R> {

    void executeMyProfile(Subscriber<R> subscriber);
}
