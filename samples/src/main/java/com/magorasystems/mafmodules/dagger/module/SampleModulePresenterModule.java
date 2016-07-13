package com.magorasystems.mafmodules.dagger.module;

import android.content.Context;

import com.fernandocejas.frodo.annotation.RxLogObservable;
import com.magorasystems.mafmodules.common.dagger.module.BaseModule;
import com.magorasystems.mafmodules.dagger.scope.ApplicationScope;
import com.magorasystems.mafmodules.module.SimpleRegistrationPresenterModule;
import com.magorasystems.mafmodules.module.SimpleRegistrationPresenterModuleImpl;
import com.magorasystems.mafmodules.presenter.SimpleRegistrationPresenter;

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
public class SampleModulePresenterModule implements BaseModule {

    @Provides
    @ApplicationScope
    @RxLogObservable
    protected Observable<SimpleRegistrationPresenterModule> providerRegistrationPresenterModule(Context context, SimpleRegistrationPresenter presenter) {
        return Observable.create(new Observable.OnSubscribe<SimpleRegistrationPresenterModule>() {
            @Override
            public void call(Subscriber<? super SimpleRegistrationPresenterModule> subscriber) {
                if (subscriber.isUnsubscribed()) {
                    subscriber.onCompleted();
                    return;
                }
                subscriber.onNext(new SimpleRegistrationPresenterModuleImpl(context, presenter));
            }
        });

    }
}
