package com.magorasystems.mafmodules.authmodule.presenter;


import com.fernandocejas.frodo.annotation.RxLogObservable;
import com.magorasystems.mafmodules.authmodule.dagger.component.AuthComponent;
import com.magorasystems.mafmodules.authmodule.interactor.impl.SimpleAuthInteractor;
import com.magorasystems.mafmodules.authmodule.presenter.impl.AuthLcePresenter;
import com.magorasystems.mafmodules.authmodule.module.outpit.AuthViewOutput;
import com.magorasystems.protocolapi.auth.dto.response.StringAuthInfo;

import rx.Observable;
import rx.subjects.PublishSubject;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class SimpleAuthPresenterImpl extends AuthLcePresenter<SimpleAuthInteractor> implements SimpleAuthPresenter {

    public SimpleAuthPresenterImpl(AuthComponent component, SimpleAuthInteractor iterator) {
        super(iterator);
        inject(component);
    }

    protected PublishSubject<AuthViewOutput> outputPublisher;

    @Override
    public void inject(AuthComponent component) {
        component.inject(this);
    }

    @Override
    @RxLogObservable
    public Observable<AuthViewOutput> output() {
        if (outputPublisher == null) {
            outputPublisher = PublishSubject.create();
        }
        return outputPublisher;
    }

    @Override
    public void onNext(StringAuthInfo stringAuthInfo) {
        super.onNext(stringAuthInfo);
        if (outputPublisher != null) {
            outputPublisher.onNext(new AuthViewOutput(stringAuthInfo));
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
            outputPublisher = null;
        }
    }
}
