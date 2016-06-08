package com.magorasystems.mafmodules.protocolapi.auth.response;

import com.magorasystems.protocolapi.auth.dto.response.AuthResponseData;
import com.magorasystems.protocolapi.auth.dto.response.StringAuthInfo;

/**
 * @author Valentin S. Bolkonsky
 * Developed by Magora Team (magora-systems.com). 2015.
 */
public class StringAuthResponseData extends AuthResponseData<StringAuthInfo> {

    public StringAuthResponseData(String accessToken, Long accessTokenExpire, String refreshToken, StringAuthInfo authInfo) {
        super(accessToken, accessTokenExpire, refreshToken, authInfo);
    }

    @Override
    public String toString() {
        return "StringAuthResponseData{} " + super.toString();
    }
}
