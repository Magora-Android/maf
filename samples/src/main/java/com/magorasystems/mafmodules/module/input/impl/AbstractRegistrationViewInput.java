package com.magorasystems.mafmodules.module.input.impl;

import com.magorasystems.mafmodules.common.module.input.AbstractViewInput;
import com.magorasystems.mafmodules.module.input.RegistrationViewInput;
import com.magorasystems.mafmodules.view.RegistrationInteractiveView;
import com.magorasystems.mafmodules.view.RegistrationLceView;
import com.magorasystems.protocolapi.auth.dto.response.AuthInfo;

import java.io.Serializable;

/**
 * Developed 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public abstract class AbstractRegistrationViewInput<M extends AuthInfo<? extends Serializable>, P extends RegistrationLceView<M>, I extends RegistrationInteractiveView<?>>
        extends AbstractViewInput<P, I> implements RegistrationViewInput<M, P, I> {

    protected AbstractRegistrationViewInput(P passiveView, I interactiveView) {
        super(passiveView, interactiveView);
    }
}
