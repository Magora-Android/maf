package com.magorasystems.mafmodules.authmodule.dagger.module;

import android.content.Context;

import com.magorasystems.mafmodules.authmodule.dagger.scope.AuthScope;
import com.magorasystems.mafmodules.authmodule.module.impl.AuthModulePresenter;
import com.magorasystems.mafmodules.authmodule.module.impl.AuthModulePresenterImp;
import com.magorasystems.mafmodules.common.dagger.module.BaseModule;
import com.magorasystems.mafmodules.common.utils.SchedulersUtils;
import com.magorasystems.mafmodules.common.utils.rx.ApplicationScheduler;

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
public class AuthModulePresenterModule implements BaseModule {

    @Provides
    @AuthScope
    public Observable<AuthModulePresenter> providerAuthModulePresenter(final Context application) {
        return Observable.create(new Observable.OnSubscribe<AuthModulePresenter>() {
            @Override
            public void call(Subscriber<? super AuthModulePresenter> subscriber) {
                if (subscriber.isUnsubscribed()) {
                    subscriber.onCompleted();
                    return;
                }
                subscriber.onNext(new AuthModulePresenterImp(application));
            }
        }).compose(SchedulersUtils.applySchedulers(new ApplicationScheduler()));
    }
}
