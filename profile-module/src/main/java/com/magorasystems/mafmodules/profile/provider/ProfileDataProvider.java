package com.magorasystems.mafmodules.profile.provider;

import com.magorasystems.mafmodules.common.mvp.provider.BaseProvider;

import rx.Observable;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public interface ProfileDataProvider<T> extends BaseProvider<T> {

    Observable<T> getMyProfile();

}
