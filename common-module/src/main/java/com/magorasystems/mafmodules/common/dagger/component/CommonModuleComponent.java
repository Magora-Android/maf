package com.magorasystems.mafmodules.common.dagger.component;

import android.content.Context;
import android.location.LocationManager;

import com.google.gson.Gson;
import com.magorasystems.mafmodules.common.SomeClass;
import com.magorasystems.mafmodules.common.application.ApplicationSettings;
import com.magorasystems.mafmodules.common.dagger.module.ApplicationModule;
import com.magorasystems.mafmodules.common.dagger.module.DomainModule;
import com.magorasystems.mafmodules.common.dagger.module.NetworkModule;
import com.magorasystems.mafmodules.common.dagger.module.StorableModule;
import com.magorasystems.mafmodules.common.utils.SchedulersUtils;
import com.magorasystems.mafmodules.network.config.ServerEndpoint;
import com.magorasystems.mafmodules.network.interceptor.HeaderInterceptor;
import com.magorasystems.mafmodules.network.manager.NetworkConnectionManager;
import com.magorasystems.mafmodules.network.store.StringApiTokenStorage;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.Cache;
import okhttp3.OkHttpClient;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
@Component(
        modules = {
                ApplicationModule.class,
                DomainModule.class,
                StorableModule.class,
                NetworkModule.class
        }
)
@Singleton
public interface CommonModuleComponent {

    LocationManager locationManager();

    NetworkConnectionManager networkConnectionManager();

    Context context();

    SchedulersUtils.CoreScheduler coreScheduler();

    StringApiTokenStorage memoryTokenStorable();

    ApplicationSettings applicationSettings();

    Cache okHttpCache();

    OkHttpClient okHttpClient();

    HeaderInterceptor headerInterceptor();

    ServerEndpoint serverEndpoint();

    Gson gson();

    void inject(SomeClass someClass);


}
