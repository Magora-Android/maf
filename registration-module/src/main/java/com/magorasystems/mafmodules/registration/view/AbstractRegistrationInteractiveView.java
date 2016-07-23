package com.magorasystems.mafmodules.registration.view;

import android.view.View;

import com.fernandocejas.frodo.annotation.RxLogObservable;
import com.jakewharton.rxbinding.view.RxView;
import com.magorasystems.mafmodules.registration.ui.widget.RegistrationWidget;
import com.magorasystems.widgets.model.UserViewModel;

import java.io.Serializable;

import rx.Observable;

/**
 * Developed 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public abstract class AbstractRegistrationInteractiveView<ID extends Serializable, M extends UserViewModel<ID>> implements RegistrationInteractiveView<M> {

    private View actionRegistrationView;
    private View actionGoToAuthView;
    private RegistrationWidget<ID, ? extends UserViewModel<ID>, M> widget;

    protected AbstractRegistrationInteractiveView(View actionRegistrationView, View actionGoToAuthView, RegistrationWidget<ID, ? extends UserViewModel<ID>, M> widget) {
        this.actionRegistrationView = actionRegistrationView;
        this.actionGoToAuthView = actionGoToAuthView;
        this.widget = widget;
    }

    @Override
    @RxLogObservable
    public Observable<? extends M> model() {
        return RxView.clicks(actionRegistrationView)
                .flatMap(v -> widget.model()
                        .doOnNext(o -> widget.setEnabled(false)));
    }

    @Override
    public void destroy() {
        actionRegistrationView = null;
        actionGoToAuthView = null;
    }

    @Override
    public Observable<Void> actionGoToAuthorization() {
        return RxView.clicks(actionGoToAuthView);
    }

    protected final View getActionRegistrationView() {
        return actionRegistrationView;
    }

    protected final View getActionGoToAuthView() {
        return actionGoToAuthView;
    }

    protected RegistrationWidget<ID, ? extends UserViewModel<ID>, M> getWidget() {
        return widget;
    }
}
