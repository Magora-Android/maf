package com.magorasystems.mafmodules.presenter;

import com.magorasystems.mafmodules.authmodule.view.AuthLceView;
import com.magorasystems.mafmodules.common.module.output.ViewOutput;
import com.magorasystems.mafmodules.common.mvp.presenter.BaseLifecyclePresenter;
import com.magorasystems.mafmodules.router.SocialRouter;
import com.magorasystems.protocolapi.auth.dto.response.AuthInfo;

import java.io.Serializable;

/**
 * Developed 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public interface SocialPresenter <ID extends Serializable,M extends AuthInfo<ID>,
        V  extends AuthLceView<ID, M>,R extends SocialRouter, VO extends ViewOutput<? extends M>> extends BaseLifecyclePresenter<V, R, M, VO> {
}
