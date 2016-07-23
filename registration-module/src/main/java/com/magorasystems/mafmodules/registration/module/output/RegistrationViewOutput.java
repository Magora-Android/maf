package com.magorasystems.mafmodules.registration.module.output;

import com.magorasystems.mafmodules.common.module.output.ViewOutput;
import com.magorasystems.protocolapi.auth.dto.response.AuthInfo;

import java.io.Serializable;

/**
 * Developed 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public interface RegistrationViewOutput<M extends AuthInfo<? extends Serializable>> extends ViewOutput<M> {

}
