package com.magorasystems.mafmodules.provider.registration;

import com.magorasystems.mafmodules.common.mvp.provider.BaseProvider;
import com.magorasystems.protocolapi.auth.dto.request.AuthRequest;
import com.magorasystems.protocolapi.auth.dto.response.AuthInfo;

import java.io.Serializable;

import rx.Observable;

/**
 * Developed 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public interface RegistrationProvider<ID extends Serializable, M extends AuthInfo<ID>> extends BaseProvider<M> {

    Observable<M> registration(AuthRequest request);
}
