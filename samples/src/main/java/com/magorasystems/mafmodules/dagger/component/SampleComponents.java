package com.magorasystems.mafmodules.dagger.component;

import com.magorasystems.mafmodules.authmodule.dagger.component.AuthComponent;
import com.magorasystems.mafmodules.common.dagger.component.CommonModuleComponent;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public enum SampleComponents {

    AUTH_COMPONENT(AuthComponent.class.getSimpleName()),
    COMMON_COMPONENT(CommonModuleComponent.class.getSimpleName()),
    SAMPLE_COMPONENTS(SampleComponent.class.getSimpleName()),
    PROFILE_COMPONENT(ProfileComponent.class.getSimpleName()),
    SOCIAL_COMPONENT(SocialComponent.class.getSimpleName());

    private String component;

    SampleComponents(String component) {
        this.component = component;
    }

    public static SampleComponents find(String componentName) throws IllegalArgumentException {
        for (SampleComponents c : values()) {
            if (c.component.equals(componentName)) {
                return c;
            }
        }
        throw new IllegalArgumentException("component not found");
    }
}
