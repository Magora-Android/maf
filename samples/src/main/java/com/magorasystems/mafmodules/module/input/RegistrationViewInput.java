package com.magorasystems.mafmodules.module.input;

import com.magorasystems.mafmodules.common.module.input.ViewInput;
import com.magorasystems.mafmodules.view.RegistrationInteractiveView;
import com.magorasystems.mafmodules.view.RegistrationLceView;
import com.magorasystems.protocolapi.auth.dto.response.AuthInfo;

import java.io.Serializable;

/**
 * Developed 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public interface RegistrationViewInput<M extends AuthInfo<? extends Serializable>, P extends RegistrationLceView<M>, I extends RegistrationInteractiveView<?>> extends ViewInput<P, I> {
}
