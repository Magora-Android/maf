package com.magorasystems.mafmodules.dagger.module;

import android.content.Context;

import com.magorasystems.mafmodules.common.dagger.module.StorableModule;
import com.magorasystems.mafmodules.network.config.SimpleTokenConfig;
import com.magorasystems.mafmodules.network.store.SimpleMemoryTokenStorable;
import com.magorasystems.mafmodules.network.store.StringApiTokenStorage;

import java.util.Locale;

import io.bloco.faker.Faker;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class MockStorableModule extends StorableModule {

    @Override
    public StringApiTokenStorage providerMemoryTokenStorable(Context context) {
        final Faker faker = new Faker(Locale.US.getLanguage());
        final SimpleTokenConfig tokenConfig = new SimpleTokenConfig(faker.number.number(20), faker.number.number(17));
        return new SimpleMemoryTokenStorable(tokenConfig);
    }
}
