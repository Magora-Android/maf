package com.magorasystems.mafmodules.registration.view;

import com.magorasystems.mafmodules.common.mvp.view.BaseLceView;
import com.magorasystems.protocolapi.auth.dto.response.AuthInfo;

import java.io.Serializable;

/**
 * Developed 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public interface RegistrationLceView<M extends AuthInfo<? extends Serializable>> extends BaseLceView<M> {
}
