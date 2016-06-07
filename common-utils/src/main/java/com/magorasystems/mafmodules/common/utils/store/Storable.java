package com.magorasystems.mafmodules.common.utils.store;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S. Bolkonsky
 */
public interface Storable<K,T> {

    T restoreObject(K key);

    void storeObject(K key, T object);

}
