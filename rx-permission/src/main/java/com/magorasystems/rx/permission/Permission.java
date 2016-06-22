package com.magorasystems.rx.permission;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author S.A.Bobrischev
 *         Developed by Magora Team (magora-systems.com). 2016.
 */
public class Permission {
    public static final int STATUS_DENIED = 1;
    public static final int STATUS_GRANTED = 2;
    public static final int STATUS_RATIONALE_REQUIRED = 3;
    public static final int STATUS_REVOKED = 4;
    public static final int STATUS_RATIONALE_DECLINED = 5;
    public static final int STATUS_RATIONALE_ACCEPTED = 6;

    public final String name;
    @Status private int status;

    @IntDef({
            STATUS_DENIED,
            STATUS_GRANTED,
            STATUS_RATIONALE_REQUIRED,
            STATUS_REVOKED,
            STATUS_RATIONALE_DECLINED,
            STATUS_RATIONALE_ACCEPTED
    })
    @Retention(RetentionPolicy.SOURCE)
    public @interface Status {

    }

    public Permission(String name, @Status int status) {
        this.name = name;
        this.status = status;
    }

    public boolean granted() {
        return status == STATUS_GRANTED;
    }

    public boolean shouldShowRationale() {
        return status == STATUS_RATIONALE_REQUIRED;
    }

    public boolean shouldRequest() {
        return status == STATUS_DENIED || status == STATUS_REVOKED;
    }

    @Status
    public int getStatus() {
        return status;
    }

    public void updateStatus(@Status int status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Permission that = (Permission) o;

        return status == that.status && name.equals(that.name);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result;
        return result;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "name='" + name + '\'' +
                ", status=" + statusToString() +
                '}';
    }

    private String statusToString() {
        switch (status) {
            case STATUS_DENIED:
                return "denied";
            case STATUS_GRANTED:
                return "granted";
            case STATUS_RATIONALE_REQUIRED:
                return "rationale required";
            case STATUS_REVOKED:
                return "revoked";
            case STATUS_RATIONALE_ACCEPTED:
                return "STATUS_RATIONALE_ACCEPTED".toLowerCase();
            case STATUS_RATIONALE_DECLINED:
                return "STATUS_RATIONALE_DECLINED".toLowerCase();
            default:
                return "unknown";
        }
    }
}
