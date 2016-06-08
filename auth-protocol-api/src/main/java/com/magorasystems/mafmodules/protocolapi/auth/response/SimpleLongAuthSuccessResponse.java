package com.magorasystems.mafmodules.protocolapi.auth.response;


import com.magorasystems.protocolapi.auth.dto.response.AuthSuccessResponse;

/**
 * @author Valentin S. Bolkonsky
 * Developed by Magora Team (magora-systems.com). 2015.
 */
public class SimpleLongAuthSuccessResponse extends AuthSuccessResponse<LongAuthResponseData> {

    public SimpleLongAuthSuccessResponse(String code, LongAuthResponseData data) {
        super(code, data);
    }

    @Override
    public String toString() {
        return "SimpleLongAuthSuccessResponse{} " + super.toString();
    }
}
