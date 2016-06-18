package com.magorasystems.mafmodules.protocolapi.auth.response;


import com.magorasystems.protocolapi.auth.dto.response.AuthSuccessResponse;

/**
 * @author: Valentin S. Bolkonsky
 * Developed by Magora Team (magora-systems.com). 2015.
 */
public class SimpleStringAuthSuccessResponse extends AuthSuccessResponse<StringAuthResponseData> {

    public SimpleStringAuthSuccessResponse(String code, StringAuthResponseData data) {
        super(code, data);
    }

    @Override
    public String toString() {
        return "SimpleStringAuthSuccessResponse{} " + super.toString();
    }
}
