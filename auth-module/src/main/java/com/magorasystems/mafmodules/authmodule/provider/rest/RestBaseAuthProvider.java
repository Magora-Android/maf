package com.magorasystems.mafmodules.authmodule.provider.rest;

import com.magorasystems.mafmodules.authmodule.provider.AuthProvider;
import com.magorasystems.mafmodules.common.utils.SchedulersUtils;
import com.magorasystems.mafmodules.network.GenericApiClientWrapper;
import com.magorasystems.mafmodules.network.provider.RestBaseDataProvider;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public abstract class RestBaseAuthProvider<A, T extends GenericApiClientWrapper<A>, COMPONENT, R> extends RestBaseDataProvider<T, COMPONENT>
        implements AuthProvider<R> {


    public RestBaseAuthProvider(COMPONENT component, SchedulersUtils.CoreScheduler scheduler, T restApiClientWrapper) {
        super(component, scheduler, restApiClientWrapper);
    }
}
