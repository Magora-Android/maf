package com.magorasystems.mafmodules.authmodule.presenter;

import com.magorasystems.mafmodules.authmodule.interactor.AuthInteractor;
import com.magorasystems.mafmodules.authmodule.model.AuthViewModel;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public interface AuthPresenter<R, I extends AuthInteractor<R>> {

    void authorization(AuthViewModel view);

}
