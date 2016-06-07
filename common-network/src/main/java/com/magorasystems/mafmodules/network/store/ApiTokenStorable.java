package com.magorasystems.mafmodules.network.store;

import com.magorasystems.mafmodules.common.utils.store.Storable;
import com.magorasystems.mafmodules.network.config.TokenConfig;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public interface ApiTokenStorable<T extends TokenConfig> extends Storable<String,T> {

    void clear();

    boolean updateAccessToken(String key, String accessToken);
}
