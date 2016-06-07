package com.magorasystems.mafmodules.common.protocolapi.response;

import com.magorasystems.protocolapi.dto.response.SuccessResponse;

/**
 * @author Valentin S. Bolkonsky
 *         Developed by Magora Team (magora-systems.com). 2015.
 */
public class StringResultSuccessResponse extends SuccessResponse<StringResultData> {

    public StringResultSuccessResponse(StringResultData data) {
        super(data);
    }
}
