package com.magorasystems.rx.permission;

import android.Manifest;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author S.A.Bobrischev
 *         Developed by Magora Team (magora-systems.com). 2016.
 */
public class PermissionInfo implements Parcelable {
    public final String permission;
    public final String rationaleTitle;
    public final String rationaleMessage;
    public final String rationalePositiveButton;
    public final String rationaleNegativeButton;

    public static PermissionInfo location(String rationaleTitle, String rationaleMessage,
                                          String rationalePositiveButton, String rationaleNegativeButton) {
        return new PermissionInfo(Manifest.permission.ACCESS_FINE_LOCATION,
                rationaleTitle,
                rationaleMessage,
                rationalePositiveButton,
                rationaleNegativeButton);
    }

    public static PermissionInfo call(String rationaleTitle, String rationaleMessage,
                                      String rationalePositiveButton, String rationaleNegativeButton) {
        return new PermissionInfo(Manifest.permission.CALL_PHONE,
                rationaleTitle,
                rationaleMessage,
                rationalePositiveButton,
                rationaleNegativeButton);
    }

    public static PermissionInfo camera(String rationaleTitle, String rationaleMessage,
                                        String rationalePositiveButton, String rationaleNegativeButton) {
        return new PermissionInfo(Manifest.permission.CAMERA,
                rationaleTitle,
                rationaleMessage,
                rationalePositiveButton,
                rationaleNegativeButton);
    }

    public static PermissionInfo readExternalStorage(String rationaleTitle, String rationaleMessage,
                                                     String rationalePositiveButton, String rationaleNegativeButton) {
        return new PermissionInfo(Manifest.permission.READ_EXTERNAL_STORAGE,
                rationaleTitle,
                rationaleMessage,
                rationalePositiveButton,
                rationaleNegativeButton);
    }

    public static PermissionInfo writeToExternalStorage(String rationaleTitle, String rationaleMessage,
                                                        String rationalePositiveButton, String rationaleNegativeButton) {
        return new PermissionInfo(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                rationaleTitle,
                rationaleMessage,
                rationalePositiveButton,
                rationaleNegativeButton);
    }


    public PermissionInfo(String permission, String rationaleTitle, String rationaleMessage,
                          String rationalePositiveButton, String rationaleNegativeButton) {
        this.permission = permission;
        this.rationaleTitle = rationaleTitle;
        this.rationaleMessage = rationaleMessage;
        this.rationalePositiveButton = rationalePositiveButton;
        this.rationaleNegativeButton = rationaleNegativeButton;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.permission);
        dest.writeString(this.rationaleTitle);
        dest.writeString(this.rationaleMessage);
        dest.writeString(this.rationalePositiveButton);
        dest.writeString(this.rationaleNegativeButton);
    }

    protected PermissionInfo(Parcel in) {
        this.permission = in.readString();
        this.rationaleTitle = in.readString();
        this.rationaleMessage = in.readString();
        this.rationalePositiveButton = in.readString();
        this.rationaleNegativeButton = in.readString();
    }

    public static final Creator<PermissionInfo> CREATOR = new Creator<PermissionInfo>() {
        @Override
        public PermissionInfo createFromParcel(Parcel source) {
            return new PermissionInfo(source);
        }

        @Override
        public PermissionInfo[] newArray(int size) {
            return new PermissionInfo[size];
        }
    };
}
