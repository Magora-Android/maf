package com.magorasystems.mafmodules.common.protocolapi.response;

import com.magorasystems.protocolapi.dto.response.SuccessResponse;

/**
 * @author: Valentin S. Bolkonsky
 * Developed by Magora Team (magora-systems.com). 2015.
 */
public class EmptySuccessResponse extends SuccessResponse<Void> {

    public EmptySuccessResponse(String code) {
        super(code, null);
    }

    @Override
    public String toString() {
        return "EmptySuccessResponse{} " + super.toString();
    }
}
