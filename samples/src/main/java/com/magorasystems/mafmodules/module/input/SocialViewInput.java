package com.magorasystems.mafmodules.module.input;

import com.magorasystems.mafmodules.authmodule.view.AuthLceView;
import com.magorasystems.mafmodules.common.module.input.ViewInput;
import com.magorasystems.protocolapi.auth.dto.response.AuthInfo;

import java.io.Serializable;

/**
 * Developed 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public interface SocialViewInput<ID extends Serializable, M extends AuthInfo<ID>,
        V extends AuthLceView<ID, M>, I extends SocialInteractiveView> extends ViewInput<V, I> {
}
