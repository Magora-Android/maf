/*
 * Copyright 2016 Víctor Albertos
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.magorasystems.rx.activityresult;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.support.annotation.IntDef;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

public class RxActivityResult {
    private static ActivitiesLifecycleCallbacks activitiesLifecycle;

    public static final int UI_TARGET_ACTIVITY = 0;
    public static final int UI_TARGET_FRAGMENT = 1;
    public static final int UI_TARGET_SUPPORT_FRAGMENT = 2;

    public static void register(final Application application) {
        activitiesLifecycle = new ActivitiesLifecycleCallbacks(application);
    }

    public static <T extends Activity> Builder<T> on(T activity) {
        return new Builder<>(activity);
    }

    public static <T extends Fragment> Builder<T> on(T fragment) {
        return new Builder<>(fragment);
    }


    @IntDef({UI_TARGET_ACTIVITY, UI_TARGET_FRAGMENT, UI_TARGET_SUPPORT_FRAGMENT})
    @Retention(RetentionPolicy.SOURCE)
    public @interface UiTargetMode {
    }

    public static class Builder<T> {
        private final Class clazz;
        private final
        @UiTargetMode
        int uiTargetMode;
        @LayoutRes
        int resourceId;
        private Subscriber<? super Result<T>> subscriber;

        public Builder(T t) {
            if (activitiesLifecycle == null) {
                throw new IllegalStateException(Locale.RX_ACTIVITY_RESULT_NOT_REGISTER);
            }

            this.clazz = t.getClass();
            if (t instanceof Activity) {
                this.uiTargetMode = UI_TARGET_ACTIVITY;
            } else {
                if (t instanceof Fragment) {
                    this.uiTargetMode = UI_TARGET_SUPPORT_FRAGMENT;
                } else {
                    this.uiTargetMode = UI_TARGET_FRAGMENT;
                }
            }
        }

        public Builder<T> fragmentContainer(@LayoutRes int id) {
            this.resourceId = id;
            return this;
        }

        public Observable<Result<T>> startIntent(final Intent intent) {
            Observable<Result<T>> observable = Observable.create(new Observable.OnSubscribe<Result<T>>() {
                @Override
                public void call(Subscriber<? super Result<T>> aSubscriber) {
                    subscriber = aSubscriber;
                }
            });

            final OnResult onResult;
            switch (uiTargetMode) {
                case UI_TARGET_SUPPORT_FRAGMENT:
                    onResult = onResultSupportFragment();
                    break;
                case UI_TARGET_FRAGMENT:
                    onResult = onResultFragment();
                    break;
                default:
                case UI_TARGET_ACTIVITY:
                    onResult = onResultActivity();
                    break;
            }

            ActivityForResult.setRequest(new Request(intent, onResult));

            activitiesLifecycle.getOLiveActivity().subscribe(new Action1<Activity>() {
                @Override
                public void call(Activity activity) {
                    activity.startActivity(new Intent(activity, ActivityForResult.class));
                }
            });

            return observable;
        }

        @SuppressWarnings("unchecked")
        private OnResult onResultActivity() {
            return new OnResult() {
                @Override
                public void response(int resultCode, Intent data) {
                    if (activitiesLifecycle.getLiveActivity() == null) {
                        return;
                    }

                    //If true it means some other activity has been stacked as a secondary process.
                    //Wait until the current activity be the target activity
                    if (activitiesLifecycle.getLiveActivity().getClass() != clazz) {
                        return;
                    }
                    T activity = (T) activitiesLifecycle.getLiveActivity();
                    subscriber.onNext(new Result<>(activity, resultCode, data));
                    subscriber.onCompleted();
                }
            };
        }

        @SuppressWarnings("unchecked")
        private OnResult onResultFragment() {
            return new OnResult() {
                @Override
                public void response(int resultCode, Intent data) {
                    if (activitiesLifecycle.getLiveActivity() == null) {
                        return;
                    }

                    Activity activity = activitiesLifecycle.getLiveActivity();


                    FragmentActivity fragmentActivity = (FragmentActivity) activity;
                    android.app.FragmentManager fragmentManager = fragmentActivity.getFragmentManager();

                    if (resourceId != 0) {
                        final android.app.Fragment fragment = fragmentManager.findFragmentById(resourceId);
                        if (fragment != null && fragment.isVisible() && fragment.getClass() == clazz) {
                            subscriber.onNext(new Result<>((T) fragment, resultCode, data));
                            subscriber.onCompleted();
                            return;
                        }
                        return;
                    }

                    final int fragmentCount = fragmentManager.getBackStackEntryCount();
                    for (int i = 0; i < fragmentCount; i++) {
                        int fragmentId = fragmentManager.getBackStackEntryAt(i).getId();
                        android.app.Fragment fragment = fragmentManager.findFragmentById(fragmentId);
                        if (fragment != null && fragment.isVisible() && fragment.getClass() == clazz) {
                            subscriber.onNext(new Result<>((T) fragment, resultCode, data));
                            subscriber.onCompleted();
                            return;
                        }
                    }

                    //If code reaches this point it means some other activity has been stacked as a secondary process.
                    //Wait until the current activity be the target activity to get the associated fragment
                }
            };
        }

        @SuppressWarnings("unchecked")
        private OnResult onResultSupportFragment() {
            return new OnResult() {
                @Override
                public void response(int resultCode, Intent data) {
                    if (activitiesLifecycle.getLiveActivity() == null) {
                        return;
                    }

                    Activity activity = activitiesLifecycle.getLiveActivity();

                    FragmentActivity fragmentActivity = (FragmentActivity) activity;
                    FragmentManager fragmentManager = fragmentActivity.getSupportFragmentManager();

                    List<Fragment> fragments = fragmentManager.getFragments();

                    if (fragments != null) {
                        for (Fragment fragment : fragments) {
                            if (fragment != null && fragment.isVisible() && fragment.getClass() == clazz) {
                                subscriber.onNext(new Result<>((T) fragment, resultCode, data));
                                subscriber.onCompleted();
                                return;
                            }
                        }
                    }

                    //If code reaches this point it means some other activity has been stacked as a secondary process.
                    //Wait until the current activity be the target activity to get the associated fragment
                }
            };
        }
    }
}
