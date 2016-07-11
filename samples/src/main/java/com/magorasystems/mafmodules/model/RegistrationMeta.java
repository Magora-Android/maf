package com.magorasystems.mafmodules.model;

import com.google.gson.annotations.SerializedName;

/**
 * Developed 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class RegistrationMeta  {

    @SerializedName("name")
    private final String name;
    @SerializedName("phone")
    private final String phone;

    public RegistrationMeta(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return "RegistrationMeta{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
