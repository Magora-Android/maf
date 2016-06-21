package com.magorasystems.mafmodules.authmodule.module.input;

import android.view.View;

import com.fernandocejas.frodo.annotation.RxLogObservable;
import com.jakewharton.rxbinding.view.RxView;
import com.magorasystems.mafmodules.authmodule.performance.AuthViewModel;
import com.magorasystems.mafmodules.authmodule.widget.AuthWidget;

import rx.Observable;
import rx.subjects.PublishSubject;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class AuthInteractiveViewImpl implements AuthInteractiveView {

    private AuthWidget authWidget;
    private View passwordRecover;
    private View actionAuthorization;
    private PublishSubject<AuthViewModel> publisher;

    public AuthInteractiveViewImpl(AuthWidget widget, View recoverPassword,
                                   View actionAuthorization,
                                   PublishSubject<AuthViewModel> publisher) {
        this.authWidget = widget;
        this.passwordRecover = recoverPassword;
        this.actionAuthorization = actionAuthorization;
        this.publisher = publisher;
    }

    @Override
    @RxLogObservable
    public Observable<AuthViewModel> model() {
        return RxView.clickEvents(actionAuthorization)
                .flatMap(viewClickEvent -> authWidget.model()
                        .doOnNext(authViewModel -> authWidget.setEnabled(false))
                        .doOnNext(authViewModel -> actionAuthorization.setEnabled(false))
                        .doOnNext(authViewModel -> publisher.onNext(authViewModel)));
    }

    @Override
    @RxLogObservable
    public Observable<Boolean> validation() {
        return authWidget.validation()
                .doOnNext(actionAuthorization::setEnabled);
    }

    @Override
    @RxLogObservable
    public Observable<Void> passwordRecover() {
        return RxView.clickEvents(passwordRecover)
                .map(viewClickEvent -> null);
    }

    @Override
    public void destroy() {
        authWidget.destroyWidget();
    }
}
