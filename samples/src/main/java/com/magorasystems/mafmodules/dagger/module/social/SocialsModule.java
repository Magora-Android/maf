package com.magorasystems.mafmodules.dagger.module.social;

import android.content.Context;

import com.magorasystems.mafmodules.common.dagger.module.BaseModule;
import com.magorasystems.mafmodules.dagger.scope.SocialScope;
import com.mgrmobi.sdk.social.android.AndroidBaseSocialNetwork;
import com.mgrmobi.sdk.social.base.SocialNetworkManager;
import com.mgrmobi.sdk.social.base.SocialType;
import com.mgrmobi.sdk.social.vk.VKSocialNetwork;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Developed 2016.
 *
 * @author Valentin S.Bolkonsky
 */
@Module
public class SocialsModule implements BaseModule {

    public static final String VK = "VK";

    @Provides
    @SocialScope
    @Named(VK)
    protected AndroidBaseSocialNetwork providerVkNetwork(Context context) {
        final AndroidBaseSocialNetwork socialNetwork;
        if (SocialNetworkManager.isRegistered(SocialType.VK)) {
            socialNetwork = (AndroidBaseSocialNetwork) SocialNetworkManager.getNetwork(SocialType.VK);
        } else {
            socialNetwork = new VKSocialNetwork.Builder()
                    .setContext(context)
                    .setScope(new String[]{"photos", "wall", "email", "status", "friends"})
                    .create();
        }
        return socialNetwork;
    }

}
