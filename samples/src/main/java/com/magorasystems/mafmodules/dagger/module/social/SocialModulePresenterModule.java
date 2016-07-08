package com.magorasystems.mafmodules.dagger.module.social;

import android.content.Context;

import com.fernandocejas.frodo.annotation.RxLogObservable;
import com.magorasystems.mafmodules.common.dagger.module.BaseModule;
import com.magorasystems.mafmodules.dagger.scope.SocialScope;
import com.magorasystems.android.module.social.module.SimpleSocialPresenterModule;
import com.magorasystems.mafmodules.module.SimpleSocialPresenterModuleImpl;
import com.magorasystems.android.module.social.presenter.SimpleSocialPresenter;

import dagger.Module;
import dagger.Provides;
import rx.Observable;
import rx.Subscriber;

/**
 * Developed 2016.
 *
 * @author Valentin S.Bolkonsky
 */
@Module
public class SocialModulePresenterModule implements BaseModule {

    @Provides
    @SocialScope
    @RxLogObservable
    protected Observable<SimpleSocialPresenterModule> providerPresenterModule(Context context, SimpleSocialPresenter presenter) {
        return Observable.create(new Observable.OnSubscribe<SimpleSocialPresenterModule>() {
            @Override
            public void call(Subscriber<? super SimpleSocialPresenterModule> subscriber) {
                if (subscriber.isUnsubscribed()) {
                    subscriber.onCompleted();
                    return;
                }
                subscriber.onNext(new SimpleSocialPresenterModuleImpl(context, presenter));
            }
        });

    }
}
