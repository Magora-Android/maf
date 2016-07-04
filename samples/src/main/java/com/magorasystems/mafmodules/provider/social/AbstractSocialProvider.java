package com.magorasystems.mafmodules.provider.social;

import com.magorasystems.mafmodules.common.utils.SchedulersUtils;
import com.magorasystems.mafmodules.network.provider.RestBaseDataProvider;

import java.io.Serializable;

/**
 * Developed 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public abstract class AbstractSocialProvider<COMPONENT, WRAPPER, ID extends Serializable> extends RestBaseDataProvider<WRAPPER, COMPONENT> implements SocialProvider<ID> {


    public AbstractSocialProvider(COMPONENT component, SchedulersUtils.CoreScheduler scheduler,
                                  WRAPPER restApiClientWrapper) {
        super(component, scheduler, restApiClientWrapper);
    }
}
