package com.magorasystems.mafmodules.common.dagger.component;

import android.location.LocationManager;

import com.magorasystems.mafmodules.common.utils.SchedulersUtils;
import com.magorasystems.mafmodules.network.config.ServerEndpoint;
import com.magorasystems.mafmodules.network.manager.NetworkConnectionManager;
import com.magorasystems.mafmodules.network.store.StringApiTokenStorage;

import javax.inject.Inject;

import me.henrytao.rxsharedpreferences.RxSharedPreferences;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class CommonComponentProvider {

    @Inject
    protected LocationManager locationManager;

    @Inject
    protected SchedulersUtils.CoreScheduler coreScheduler;

    @Inject
    protected NetworkConnectionManager networkConnectionManager;

    @Inject
    protected StringApiTokenStorage apiTokenStorage;

    @Inject
    protected ServerEndpoint serverEndpoint;

    @Inject
    protected RxSharedPreferences sharedPreferences;

    public CommonComponentProvider(CommonModuleComponent component) {
        component.inject(this);
    }

    public LocationManager getLocationManager() {
        return locationManager;
    }

    public SchedulersUtils.CoreScheduler getCoreScheduler() {
        return coreScheduler;
    }

    public NetworkConnectionManager getNetworkConnectionManager() {
        return networkConnectionManager;
    }

    public StringApiTokenStorage getApiTokenStorage() {
        return apiTokenStorage;
    }

    public ServerEndpoint getServerEndpoint() {
        return serverEndpoint;
    }

    public RxSharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }
}
