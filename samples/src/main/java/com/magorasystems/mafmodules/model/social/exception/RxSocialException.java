package com.magorasystems.mafmodules.model.social.exception;

import com.mgrmobi.sdk.social.base.SocialType;

/**
 * Developed 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class RxSocialException extends RuntimeException {

    private SocialType socialType;

    public RxSocialException(SocialType socialType) {
        this.socialType = socialType;
    }

    public RxSocialException(SocialType socialType, String message, Throwable throwable) {
        super(message, throwable);
        this.socialType = socialType;
    }

    public SocialType getSocialType() {
        return socialType;
    }
}
