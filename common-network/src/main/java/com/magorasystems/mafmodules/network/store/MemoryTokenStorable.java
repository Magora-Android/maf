package com.magorasystems.mafmodules.network.store;

import com.google.common.collect.Maps;
import com.magorasystems.mafmodules.network.config.TokenConfig;

import java.util.Map;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class MemoryTokenStorable<T extends TokenConfig> implements ApiTokenStorable<T> {

    private final Map<String, T> tokens;

    public MemoryTokenStorable() {
        tokens = Maps.newHashMap();
    }

    @Override
    public T restoreObject(String key) {
        return tokens.get(key);
    }

    @Override
    public void storeObject(String key, T object) {
        if (tokens.containsKey(key)) {
            tokens.remove(key);
        }
        tokens.put(key, object);
    }

    @Override
    public void clear() {
        tokens.clear();
    }

    @Override
    public boolean updateAccessToken(String key, String accessToken) {
        T config = tokens.remove(key);
        if (config != null) {
            config.setAccessToken(accessToken);
            return tokens.put(key, config) != null;
        }
        return false;
    }

    @Override
    public void remove(String key) {
        tokens.remove(key);
    }

}
