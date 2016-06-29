package com.magorasystems.mafmodules.view.impl;

import android.view.View;

import com.fernandocejas.frodo.annotation.RxLogObservable;
import com.jakewharton.rxbinding.view.RxView;
import com.magorasystems.mafmodules.module.input.ProfileInteractiveView;

import rx.Observable;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class UserProfileInteractiveView implements ProfileInteractiveView<Void> {

    private View updateView;
    private View editView;

    public UserProfileInteractiveView(View update, View editView) {
        this.updateView = update;
        this.editView = editView;
    }

    @Override
    @RxLogObservable
    public Observable<Void> model() {
        if (updateView != null) {
            return RxView.clicks(updateView);
        }
        return Observable.empty();
    }

    @Override
    public Observable<Void> edit() {
        if (editView != null) {
            return RxView.clicks(editView);
        }
        return Observable.empty();
    }

    @Override
    public void destroy() {
        updateView = null;
        editView = null;
    }
}
