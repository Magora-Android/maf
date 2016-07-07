package com.magorasystems.android.module.social.presenter;

import com.magorasystems.mafmodules.authmodule.view.AuthLceView;
import com.magorasystems.mafmodules.common.module.output.ViewOutput;
import com.magorasystems.mafmodules.common.mvp.presenter.BaseIteratorLcePresenter;
import com.magorasystems.mafmodules.common.utils.rx.PresenterSubscriber;
import com.magorasystems.android.module.social.interactor.SocialInteractor;
import com.magorasystems.android.module.social.model.RxCommonSocial;
import com.magorasystems.android.module.social.router.SocialRouter;
import com.magorasystems.protocolapi.auth.dto.response.AuthInfo;

import java.io.Serializable;

/**
 * Developed 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public abstract class AbstractSocialPresenter<ID extends Serializable,
        M extends AuthInfo<ID>,
        I extends SocialInteractor<ID, M>, V extends AuthLceView<ID, M>, R extends SocialRouter<ID>, VO extends ViewOutput<M>>
        extends BaseIteratorLcePresenter<M, I, V, R, VO>
        implements SocialPresenter<ID, M, V, R, VO> {

    protected AbstractSocialPresenter(I interactor) {
        super(interactor);
    }


    @Override
    public void authorizationBySocial(RxCommonSocial rxCommonSocial) {
        showProgress();
        getIteractor().executeSocialAuthorization(rxCommonSocial, new PresenterSubscriber<>(this));
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {
        getIteractor().unsubscribe();
        destroy();
    }
}
