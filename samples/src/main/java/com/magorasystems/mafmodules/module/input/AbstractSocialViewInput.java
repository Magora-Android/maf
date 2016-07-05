package com.magorasystems.mafmodules.module.input;

import com.magorasystems.mafmodules.authmodule.view.AuthLceView;
import com.magorasystems.mafmodules.common.module.input.AbstractViewInput;
import com.magorasystems.protocolapi.auth.dto.response.AuthInfo;

import java.io.Serializable;

/**
 * Developed 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public abstract class AbstractSocialViewInput<ID extends Serializable, M extends AuthInfo<ID>, V extends AuthLceView<ID, M>, I extends SocialInteractiveView>
        extends AbstractViewInput<V, I> implements SocialViewInput<ID,M,V,I> {

    protected AbstractSocialViewInput(V passiveView, I interactiveView) {
        super(passiveView, interactiveView);
    }
}
