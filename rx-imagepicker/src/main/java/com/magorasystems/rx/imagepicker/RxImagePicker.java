package com.magorasystems.rx.imagepicker;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import rx.Observable;
import rx.subjects.PublishSubject;

public class RxImagePicker {

    private static RxImagePicker instance;

    public static synchronized RxImagePicker with(Context context) {
        if (instance == null) {
            instance = new RxImagePicker(context.getApplicationContext());
        }
        return instance;
    }

    private Context context;
    private PublishSubject<Uri> publishSubject;

    private RxImagePicker(Context context) {
        this.context = context;
    }

    public Observable<Uri> requestImage(Sources imageSource) {
        publishSubject = PublishSubject.create();
        startImagePickHiddenActivity(imageSource.ordinal());
        return publishSubject;
    }

    void onImagePicked(Uri uri) {
        if (publishSubject != null) {
            publishSubject.onNext(uri);
            publishSubject.onCompleted();
        }
    }

    void onDestroy() {
        if (publishSubject != null) {
            publishSubject.onCompleted();
        }
    }

    private void startImagePickHiddenActivity(int imageSource) {
        Intent intent = new Intent(context, HiddenActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(HiddenActivity.IMAGE_SOURCE, imageSource);
        context.startActivity(intent);
    }

}

