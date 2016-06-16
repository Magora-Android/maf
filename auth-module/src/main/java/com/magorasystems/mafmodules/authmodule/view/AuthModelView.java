package com.magorasystems.mafmodules.authmodule.view;

import com.magorasystems.mafmodules.common.mvp.view.BaseModelView;
import com.magorasystems.protocolapi.auth.dto.response.AuthInfo;

import java.io.Serializable;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public interface AuthModelView<I extends Serializable, M extends AuthInfo<I>> extends BaseModelView<M>, AuthView {
}
