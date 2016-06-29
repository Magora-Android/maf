package com.magorasystems.mafmodules.presenter.impl;

import android.content.Context;

import com.magorasystems.mafmodules.common.utils.component.HasComponent;
import com.magorasystems.mafmodules.common.utils.component.Injectable;
import com.magorasystems.mafmodules.common.utils.rx.PresenterSubscriber;
import com.magorasystems.mafmodules.dagger.component.ProfileComponent;
import com.magorasystems.mafmodules.interactor.impl.SimpleProfileInteractor;
import com.magorasystems.mafmodules.model.UserProfile;
import com.magorasystems.mafmodules.module.output.UserProfileViewOutput;
import com.magorasystems.mafmodules.module.output.UserProfileViewOutputImpl;
import com.magorasystems.mafmodules.presenter.GenericProfilePresenter;
import com.magorasystems.mafmodules.router.ProfileRouter;
import com.magorasystems.mafmodules.view.impl.UserProfileLceView;

import rx.Observable;
import rx.subjects.PublishSubject;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class SimpleProfilePresenterImpl extends GenericProfilePresenter<UserProfile,
        UserProfileLceView,
        SimpleProfileInteractor,
        ProfileRouter<UserProfile>>
        implements SimpleProfilePresenter, Injectable<ProfileComponent> {

    private PublishSubject<UserProfileViewOutput> outputPublisher;


    public SimpleProfilePresenterImpl(Context context, SimpleProfileInteractor interactor) {
        super(interactor);
        inject((ProfileComponent) ((HasComponent<?>) context).getComponent(ProfileComponent.class.getSimpleName()));
    }

    @Override
    public void takeMyProfile() {
        showProgress();
        getIteractor().executeMyProfile(new PresenterSubscriber<>(this));
    }

    @Override
    public Observable<UserProfileViewOutput> output() {
        if (outputPublisher == null) {
            outputPublisher = PublishSubject.create();
        }
        return outputPublisher;
    }

    @Override
    public void onStart() {
        takeMyProfile();
    }

    @Override
    public void inject(ProfileComponent profileComponent) {
        profileComponent.inject(this);
    }

    @Override
    public void onNext(UserProfile profile) {
        super.onNext(profile);
        if (outputPublisher != null) {
            outputPublisher.onNext(new UserProfileViewOutputImpl(profile));
        }
    }

    @Override
    public void onError(Throwable e) {
        super.onError(e);
        if (outputPublisher != null) {
            outputPublisher.onError(e);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (outputPublisher != null) {
            outputPublisher.onCompleted();
            outputPublisher = null;
        }
    }
}
