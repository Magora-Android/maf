package com.magorasystems.mafmodules.network.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.magorasystems.mafmodules.model.UserProfile;
import com.magorasystems.protocolapi.dto.response.SuccessResponse;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class ProfileSuccessResponse extends SuccessResponse<UserProfile> {

    public ProfileSuccessResponse(@JsonProperty("data") UserProfile data) {
        super(data);
    }

    @Override
    public String toString() {
        return "ProfileSuccessResponse{} " + super.toString();
    }
}
