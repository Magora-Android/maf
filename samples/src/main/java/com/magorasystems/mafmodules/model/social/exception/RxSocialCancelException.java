package com.magorasystems.mafmodules.model.social.exception;

import com.mgrmobi.sdk.social.base.SocialCancelReason;
import com.mgrmobi.sdk.social.base.SocialType;

/**
 * Developed 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class RxSocialCancelException extends RxSocialException {

    private SocialCancelReason socialCancelReason;

    public RxSocialCancelException(SocialType socialType, SocialCancelReason socialCancelReason) {
        super(socialType);
        this.socialCancelReason = socialCancelReason;
    }

    public RxSocialCancelException(SocialType socialType, SocialCancelReason socialCancelReason, String message, Throwable throwable) {
        super(socialType, message, throwable);
        this.socialCancelReason = socialCancelReason;
    }

    public SocialCancelReason getSocialCancelReason() {
        return socialCancelReason;
    }
}
