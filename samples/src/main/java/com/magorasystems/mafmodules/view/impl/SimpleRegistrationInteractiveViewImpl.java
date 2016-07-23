package com.magorasystems.mafmodules.view.impl;

import android.view.View;

import com.magorasystems.mafmodules.registration.view.AbstractRegistrationInteractiveView;
import com.magorasystems.mafmodules.ui.widget.SimpleRegistrationWidget;
import com.magorasystems.mafmodules.ui.widget.model.SimpleUserPasswordViewModel;
import com.magorasystems.mafmodules.view.SimpleRegistrationInteractiveView;

import rx.Observable;

/**
 * Developed 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class SimpleRegistrationInteractiveViewImpl extends AbstractRegistrationInteractiveView<String, SimpleUserPasswordViewModel>
        implements SimpleRegistrationInteractiveView {

    public SimpleRegistrationInteractiveViewImpl(View actionRegistrationView, View actionGoToAuthView,
                                                 SimpleRegistrationWidget widget) {
        super(actionRegistrationView, actionGoToAuthView, widget);
    }

    @Override
    protected SimpleRegistrationWidget getWidget() {
        return (SimpleRegistrationWidget) super.getWidget();
    }

    @Override
    public Observable<Boolean> validation() {
        return getWidget().validation()
                .doOnNext(v -> getActionRegistrationView().setEnabled(true));
    }
}
