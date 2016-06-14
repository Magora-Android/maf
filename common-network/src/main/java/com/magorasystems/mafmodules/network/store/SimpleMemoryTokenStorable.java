package com.magorasystems.mafmodules.network.store;

import com.magorasystems.mafmodules.network.config.SimpleTokenConfig;

import javax.inject.Inject;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */

public class SimpleMemoryTokenStorable extends MemoryTokenStorable<SimpleTokenConfig> implements StringApiTokenStorage {

    @Inject
    public SimpleMemoryTokenStorable() {
        super();
    }

    public SimpleMemoryTokenStorable(SimpleTokenConfig config) {
        super();
        storeObject(SimpleTokenConfig.HEADER_FIELD_NAME, config);
    }

    @Override
    public String toString() {
        return "SimpleMemoryTokenStorable{" +
                "size=" + size() +
                ", header config=" + restoreObject(SimpleTokenConfig.HEADER_FIELD_NAME) +
                "}";
    }
}
