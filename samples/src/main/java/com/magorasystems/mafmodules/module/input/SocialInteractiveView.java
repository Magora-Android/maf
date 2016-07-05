package com.magorasystems.mafmodules.module.input;

import com.magorasystems.mafmodules.common.module.input.InteractiveView;
import com.magorasystems.mafmodules.model.social.RxCommonSocial;
import com.mgrmobi.sdk.social.base.SocialType;

import rx.Observable;

/**
 * Developed 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public interface SocialInteractiveView extends InteractiveView<RxCommonSocial> {

    Observable<RxCommonSocial> model(SocialType socialType);
}
