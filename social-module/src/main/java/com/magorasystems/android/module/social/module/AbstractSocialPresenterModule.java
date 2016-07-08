package com.magorasystems.android.module.social.module;

import android.content.Context;

import com.magorasystems.mafmodules.authmodule.view.AuthLceView;
import com.magorasystems.mafmodules.common.module.base.AbstractModulePresenter;
import com.magorasystems.mafmodules.common.module.base.ModuleInput;
import com.magorasystems.mafmodules.common.module.output.ViewOutput;
import com.magorasystems.mafmodules.common.utils.component.HasComponent;
import com.magorasystems.mafmodules.common.utils.component.Injectable;
import com.magorasystems.android.module.social.module.input.SocialInteractiveView;
import com.magorasystems.android.module.social.module.input.SocialViewInput;
import com.magorasystems.android.module.social.router.SocialRouter;
import com.magorasystems.protocolapi.auth.dto.response.AuthInfo;

import java.io.Serializable;

/**
 * Developed 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public abstract class AbstractSocialPresenterModule<COMPONENT, ID extends Serializable, M extends AuthInfo<ID>, R extends SocialRouter<ID>,
        VI extends SocialViewInput<ID, M, ? extends AuthLceView<ID, M>, ? extends SocialInteractiveView>, MI extends ModuleInput<VI, R>, VO extends ViewOutput<M>>
        extends AbstractModulePresenter<R, VI, VO, MI> implements SocialPresenterModule<ID, M, R, VI, MI, VO>, Injectable<COMPONENT> {


    @Override
    public void onError(Throwable e) {
        super.onError(e);
        R router = getRouter();
        if (router == null) {
            return;
        }
        router.onShowError(e);
    }

    @Override
    public void onNext(VO vo) {
        super.onNext(vo);
        R router = getRouter();
        if (router == null) {
            return;
        }
        router.onAfterSocialAuth(vo.getModel());
    }

    protected final void injectComponent(Context context, Class<COMPONENT> clazz) {
        if (context instanceof HasComponent<?>) {
            final Object component = ((HasComponent<?>) context).getComponent(clazz.getSimpleName());
            if (component != null) {
                try {
                    inject(clazz.cast(component));
                } catch (ClassCastException e) {
                    throw new IllegalArgumentException("Context has not implement \"" + clazz.getSimpleName() + "\" component", e);
                }
            }
        }

    }
}
