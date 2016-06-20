package com.magorasystems.mafmodules.authmodule.view.input;

import android.view.View;

import com.google.common.collect.Lists;
import com.jakewharton.rxbinding.view.RxView;
import com.magorasystems.mafmodules.authmodule.performance.AuthViewModel;
import com.magorasystems.mafmodules.authmodule.widget.AuthWidget;
import com.magorasystems.mafmodules.common.ui.widget.ValidationTextRule;

import java.util.Collection;
import java.util.List;

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
    private List<ValidationTextRule> rules;
    private PublishSubject<AuthViewModel> publisher;

    public AuthInteractiveViewImpl(AuthWidget widget, View recoverPassword,
                                   PublishSubject<AuthViewModel> publisher,
                                   Collection<ValidationTextRule> rules) {
        this.authWidget = widget;
        this.passwordRecover = recoverPassword;
        this.rules = rules != null ? Lists.newArrayList(rules) : Lists.newArrayList();
        this.publisher = publisher;
    }

    @Override
    public Observable<AuthViewModel> model() {
        return authWidget.model()
                .doOnNext(authViewModel -> authWidget.setEnabled(false))
                .doOnNext(authViewModel -> publisher.onNext(authViewModel));
    }

    @Override
    public Observable<Boolean> validation() {
        authWidget.updateRules(rules);
        return authWidget.validation();
    }

    @Override
    public Observable<Void> passwordRecover() {
        return RxView.clickEvents(passwordRecover)
                .map(viewClickEvent -> null);
    }
}
