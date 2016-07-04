package com.magorasystems.mafmodules.dagger.module;

import android.content.Context;

import com.magorasystems.mafmodules.common.dagger.module.BaseModule;
import com.magorasystems.mafmodules.common.utils.SchedulersUtils;
import com.magorasystems.mafmodules.dagger.scope.ProfileScope;
import com.magorasystems.mafmodules.interactor.SimpleProfileInteractor;
import com.magorasystems.mafmodules.module.UserProfilePresenterModule;
import com.magorasystems.mafmodules.module.UserProfilePresenterModuleImpl;
import com.magorasystems.mafmodules.presenter.impl.SimpleProfilePresenter;
import com.magorasystems.mafmodules.presenter.impl.SimpleProfilePresenterImpl;

import dagger.Module;
import dagger.Provides;
import rx.Observable;
import rx.Subscriber;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
@Module
public class ProfilePresenterModule implements BaseModule {

    @Provides
    @ProfileScope
    protected SimpleProfilePresenter providerProfilePresenter(Context context, SimpleProfileInteractor interactor) {
        return new SimpleProfilePresenterImpl(context, interactor);
    }

    @Provides
    @ProfileScope
    protected Observable<UserProfilePresenterModule> providerProfilePresenterModule(Context context,
                                                                                    SchedulersUtils.CoreScheduler scheduler,
                                                                                    SimpleProfilePresenter presenter) {
        return Observable.create(new Observable.OnSubscribe<UserProfilePresenterModule>() {
            @Override
            public void call(Subscriber<? super UserProfilePresenterModule> subscriber) {
                if (subscriber.isUnsubscribed()) {
                    subscriber.onCompleted();
                    return;
                }
                subscriber.onNext(new UserProfilePresenterModuleImpl(context, presenter));
            }
        }).compose(SchedulersUtils.applySchedulers(scheduler));
    }
}
