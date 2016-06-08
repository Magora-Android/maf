package com.magorasystems.mafmodules.protocolapi.auth.response;

import com.magorasystems.protocolapi.auth.dto.response.AuthResponseData;
import com.magorasystems.protocolapi.auth.dto.response.LongAuthInfo;

/**
 * @author Valentin S. Bolkonsky
 * Developed by Magora Team (magora-systems.com). 2015.
 */
public class LongAuthResponseData extends AuthResponseData<LongAuthInfo> {

    public LongAuthResponseData(String accessToken, Long accessTokenExpire, String refreshToken, LongAuthInfo authInfo) {
        super(accessToken, accessTokenExpire, refreshToken, authInfo);
    }

    @Override
    public String toString() {
        return "LongAuthResponseData{} " + super.toString();
    }
}
