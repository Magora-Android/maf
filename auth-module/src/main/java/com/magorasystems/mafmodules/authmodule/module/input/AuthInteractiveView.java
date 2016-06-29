package com.magorasystems.mafmodules.authmodule.module.input;

import com.magorasystems.mafmodules.authmodule.performance.AuthViewModel;
import com.magorasystems.mafmodules.common.module.input.InteractiveView;

import rx.Observable;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public interface AuthInteractiveView extends InteractiveView<AuthViewModel> {

    Observable<Boolean> validation();

    Observable<Void> passwordRecover();

}
