package com.magorasystems.mafmodules.authmodule.view;

import com.magorasystems.mafmodules.common.mvp.view.BaseLceView;
import com.magorasystems.protocolapi.auth.dto.response.AuthInfo;

import java.io.Serializable;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public interface AuthView<ID extends Serializable, M extends AuthInfo<ID>> extends BaseLceView<M> {


}
