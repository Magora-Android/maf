package com.magorasystems.mafmodules.common.dagger.module;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public enum ProviderMode {

    MOCK(BaseModule.QUALIFIER_MOCK),
    COMBAT(BaseModule.QUALIFIER_COMBAT),
    REST(BaseModule.QUALIFIER_REST);

    final String value;

    public final String getValue() {
        return value;
    }

    ProviderMode(String val) {
        this.value = val;
    }
}
