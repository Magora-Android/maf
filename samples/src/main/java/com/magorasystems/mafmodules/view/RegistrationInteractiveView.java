package com.magorasystems.mafmodules.view;

import com.magorasystems.mafmodules.common.module.input.InteractiveView;
import com.magorasystems.widgets.model.UserViewModel;

import java.io.Serializable;

import rx.Observable;

/**
 * Developed 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public interface RegistrationInteractiveView<M extends UserViewModel<? extends Serializable>> extends InteractiveView<M> {

    Observable<Boolean> validation();

    Observable<Void> actionGoToAuthorization();

}
