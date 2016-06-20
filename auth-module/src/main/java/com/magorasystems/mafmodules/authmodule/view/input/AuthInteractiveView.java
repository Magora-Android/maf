package com.magorasystems.mafmodules.authmodule.view.input;

import com.magorasystems.mafmodules.authmodule.performance.AuthViewModel;

import rx.Observable;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public interface AuthInteractiveView {

    Observable<AuthViewModel> model();

    Observable<Boolean> validation();

    Observable<Void> passwordRecover();

}
