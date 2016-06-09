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
}
