package com.magorasystems.mafmodules.model;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class UserProfile {

    private Long id;
    private String uid;
    private String name;
    private String firstName;
    private String lastName;
    private String phone;
    private String avatarPath;

    public static UserProfile create(Long id, String uid, String name,
                                     String firstName, String lastName,
                                     String phone, String avatar) {
        final UserProfile userProfile = new UserProfile();
        userProfile.id = id;
        userProfile.uid = uid;
        userProfile.name = name;
        userProfile.firstName = firstName;
        userProfile.lastName = lastName;
        userProfile.phone = phone;
        userProfile.avatarPath = avatar;
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

    public String getUid() {
        return uid;
    }

    public String getAvatar() {
        return avatarPath;
    }

    @Override
    public String toString() {
        return "UserProfile{" +
                "id='" + id + '\'' +
                ", uid='" + uid + '\'' +
                ", name='" + name + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                ", avatar='" + avatarPath + '\'' +
                '}';
    }
}
