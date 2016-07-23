package com.magorasystems.mafmodules.registration.module;

import com.magorasystems.mafmodules.common.module.base.ModuleInput;
import com.magorasystems.mafmodules.common.module.base.ModulePresenter;
import com.magorasystems.mafmodules.registration.module.input.RegistrationViewInput;
import com.magorasystems.mafmodules.registration.module.output.RegistrationViewOutput;
import com.magorasystems.mafmodules.registration.router.RegistrationRouter;
import com.magorasystems.protocolapi.auth.dto.response.AuthInfo;

import java.io.Serializable;

/**
 * Developed 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public interface RegistrationPresenterModule<M extends AuthInfo<? extends Serializable>,R extends RegistrationRouter<M>, VI extends RegistrationViewInput<M, ?, ?>, VO extends RegistrationViewOutput<M>,
        I extends ModuleInput<VI, R>>
        extends ModulePresenter<R, VI, VO, I> {
}
