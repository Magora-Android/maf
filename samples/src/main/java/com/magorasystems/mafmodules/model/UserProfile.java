package com.magorasystems.mafmodules.model;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class UserProfile {

    private Long id;
    private String name;
    private String firstName;
    private String lastName;
    private String phone;

    public static UserProfile create(Long id, String name, String firstName, String lastName, String phone) {
        final UserProfile userProfile = new UserProfile();
        userProfile.id = id;
        userProfile.name = name;
        userProfile.firstName = firstName;
        userProfile.lastName = lastName;
        userProfile.phone = phone;
        return userProfile;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return "UserProfile{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
