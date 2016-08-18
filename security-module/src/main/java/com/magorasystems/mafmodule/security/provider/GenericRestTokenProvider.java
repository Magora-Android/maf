package com.magorasystems.mafmodule.security.provider;

import com.magorasystems.mafmodules.common.utils.SchedulersUtils;
import com.magorasystems.mafmodules.network.config.TokenConfig;
import com.magorasystems.mafmodules.network.provider.RestBaseDataProvider;
import com.magorasystems.mafmodules.network.store.ApiTokenStorable;

/**
 * Developed 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public abstract class GenericRestTokenProvider<W, COMPONENT, TOKEN extends TokenConfig> extends RestBaseDataProvider<W, COMPONENT> {

    protected ApiTokenStorable<TOKEN> tokenStorage;

    public GenericRestTokenProvider(COMPONENT component, SchedulersUtils.CoreScheduler scheduler, W restApiClientWrapper, ApiTokenStorable<TOKEN> tokenStorage) {
        super(component, scheduler, restApiClientWrapper);
        this.tokenStorage = tokenStorage;
    }

    protected final TOKEN getTokenConfig(String key) {
        return tokenStorage.restoreObject(key);
    }

    protected final void saveToken(String key, TOKEN token) {
        tokenStorage.storeObject(key, token);
    }
}
