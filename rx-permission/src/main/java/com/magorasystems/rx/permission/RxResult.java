package com.magorasystems.rx.permission;

import android.app.Activity;
import android.app.Application;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;

import com.magorasystems.rx.activityresult.RxActivityResult;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * @author S.A.Bobrischev
 *         Developed by Magora Team (magora-systems.com). 2016.
 */
public class RxResult {

    private RxResult() {
    }

    public static void register(Application application) {
        RxActivityResult.register(application);
    }

    public static <T extends Activity> RxActivityResult.Builder<T> on(T activity) {
        return RxActivityResult.on(activity);
    }

    public static <T extends Fragment> RxActivityResult.Builder<T> on(T fragment) {
        return RxActivityResult.on(fragment);
    }

    public static <T extends android.app.Fragment> RxActivityResult.Builder<T> on(T fragment, @IdRes int resourceId) {
        return RxActivityResult.on(fragment, resourceId);
    }

    public static Observable<Permission> checkPermissions(final Activity activity,
                                                          final PermissionInfo permissionInfo) {
        return Observable.create(new Observable.OnSubscribe<Permission>() {
            @Override
            public void call(Subscriber<? super Permission> subscriber) {
                RxPermissions rxPermissions = RxPermissions.getInstance(activity);
                subscriber.onNext(new Permission(permissionInfo.permission,
                        rxPermissions.isGranted(permissionInfo.permission) ? Permission.STATUS_GRANTED :
                                rxPermissions.isRevoked(permissionInfo.permission) ? Permission.STATUS_REVOKED :
                                        rxPermissions.shouldShowRequestPermissionRationale(activity, permissionInfo.permission) ?
                                                Permission.STATUS_RATIONALE_REQUIRED : Permission.STATUS_DENIED));
            }
        }).compose(composeWithPermissionsRequest(activity, permissionInfo));
    }

    public static Observable<Permission> request(Activity activity, final String permission) {
        return RxPermissions.getInstance(activity).request(permission)
                .map(granted -> new Permission(permission, granted ? Permission.STATUS_GRANTED : Permission.STATUS_DENIED));
    }

    public static Observable.Transformer<Permission, Permission> composeWithPermissionsRequest(final Activity activity,
                                                                                               final PermissionInfo permissionInfo) {
        return permissionObservable -> permissionObservable.flatMap((Func1<Permission, Observable<? extends Permission>>) permission -> {
            if (permission.shouldRequest()) {
                return RxResult.request(activity, permission.name);
            } else if (permission.shouldShowRationale()) {
                return showRationale(activity, permission, permissionInfo)
                        .flatMap(afterRationale -> {
                            if (afterRationale.getStatus() == Permission.STATUS_RATIONALE_DECLINED) {
                                permission.updateStatus(Permission.STATUS_DENIED);
                                return Observable.just(permission);
                            } else {
                                return RxResult.request(activity, permission.name);
                            }
                        });
            } else {
                return Observable.just(permission);
            }
        });
    }

    protected static Observable<Permission> showRationale(final Activity activity, final Permission permission,
                                                          PermissionInfo permissionInfo) {
        return RxActivityResult.on(activity).startIntent(ActivityPermissionsRationale.createIntent(activity, permissionInfo))
                .flatMap(activityResult -> {
                    permission.updateStatus(
                            activityResult.resultCode() == Activity.RESULT_OK ?
                                    Permission.STATUS_RATIONALE_ACCEPTED :
                                    Permission.STATUS_RATIONALE_DECLINED);
                    return Observable.just(permission);
                });
    }
}
