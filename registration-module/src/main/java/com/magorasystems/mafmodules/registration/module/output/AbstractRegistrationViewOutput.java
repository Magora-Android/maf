package com.magorasystems.mafmodules.registration.module.output;

import com.magorasystems.mafmodules.common.module.output.AbstractViewOutput;
import com.magorasystems.protocolapi.auth.dto.response.AuthInfo;

import java.io.Serializable;

/**
 * Developed 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public abstract class AbstractRegistrationViewOutput<M extends AuthInfo<? extends Serializable>> extends AbstractViewOutput<M>
        implements RegistrationViewOutput<M> {

    protected AbstractRegistrationViewOutput(M model) {
        super(model);
    }
}
