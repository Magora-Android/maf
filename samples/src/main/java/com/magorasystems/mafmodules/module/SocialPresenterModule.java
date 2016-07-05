package com.magorasystems.mafmodules.module;

import com.magorasystems.mafmodules.authmodule.view.AuthLceView;
import com.magorasystems.mafmodules.common.module.base.ModuleInput;
import com.magorasystems.mafmodules.common.module.base.ModulePresenter;
import com.magorasystems.mafmodules.common.module.output.ViewOutput;
import com.magorasystems.mafmodules.module.input.SocialInteractiveView;
import com.magorasystems.mafmodules.module.input.SocialViewInput;
import com.magorasystems.mafmodules.router.SocialRouter;
import com.magorasystems.protocolapi.auth.dto.response.AuthInfo;

import java.io.Serializable;

/**
 * Developed 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public interface SocialPresenterModule<ID extends Serializable, M extends AuthInfo<ID>, R extends SocialRouter<ID>,
        VI extends SocialViewInput<ID, M, ? extends AuthLceView<ID, M>, ? extends SocialInteractiveView>, MI extends ModuleInput<VI, R>, VO extends ViewOutput<M>> extends ModulePresenter<R,
        VI, VO, MI> {
}
