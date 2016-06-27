package com.magorasystems.mafmodules.network;

import com.magorasystems.protocolapi.dto.response.SuccessResponse;

import rx.Observable;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public interface ProfileApiClientWrapper<P> {

    Observable<? extends SuccessResponse<? super P>> getMyProfile();


}
