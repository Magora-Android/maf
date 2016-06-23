package com.magorasystems.rx.imagepicker;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;

import com.magorasystems.rx.permission.PermissionInfo;
import com.magorasystems.rx.permission.RxResult;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class HiddenActivity extends Activity {
    public final static String IMAGE_SOURCE = "image_source";


    private static String TAG = "RxImagePicker";

    private static final int SELECT_PHOTO = 100;
    private static final int TAKE_PHOTO = 101;

    private Uri cameraPictureUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            handleIntent(getIntent());
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case SELECT_PHOTO:
                    RxImagePicker.with(this).onImagePicked(data.getData());
                    break;
                case TAKE_PHOTO:
                    RxImagePicker.with(this).onImagePicked(cameraPictureUrl);
                    break;
            }
        }
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxImagePicker.with(this).onDestroy();
    }

    private void handleIntent(Intent intent) {
        final Sources sourceType = Sources.values()[intent.getIntExtra(IMAGE_SOURCE, 0)];
        switch (sourceType) {
            case CAMERA:
                RxResult.checkPermissions(this, PermissionInfo.writeToExternalStorage(
                        getString(R.string.write_storage_permission_title),
                        getString(R.string.write_storage_permission_message),
                        getString(android.R.string.ok),
                        getString(android.R.string.cancel)))
                        .subscribe(permission -> {
                            if (permission.granted()) {
                                takeCameraPhoto();
                                return;
                            }
                            finish();
                        });
                break;
            case GALLERY:
                RxResult.checkPermissions(this, PermissionInfo.readExternalStorage(
                        getString(R.string.read_storage_permission_title),
                        getString(R.string.read_storage_permission_message),
                        getString(android.R.string.ok),
                        getString(android.R.string.cancel)))
                        .subscribe(permission -> {
                            if (permission.granted()) {
                                takeGallery();
                                return;
                            }
                            finish();
                        });
                break;
        }
    }

    private void takeCameraPhoto() {
        cameraPictureUrl = Uri.fromFile(createImageFile());
        Intent pictureChooseIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        pictureChooseIntent.putExtra(MediaStore.EXTRA_OUTPUT, cameraPictureUrl);
        startActivityForResult(pictureChooseIntent, TAKE_PHOTO);
    }

    private void takeGallery() {
        Intent pictureChooseIntent;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            pictureChooseIntent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            pictureChooseIntent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, false);
            pictureChooseIntent.addFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
        } else {
            pictureChooseIntent = new Intent(Intent.ACTION_GET_CONTENT);
        }
        pictureChooseIntent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
        pictureChooseIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        pictureChooseIntent.setType("image/*");
        startActivityForResult(pictureChooseIntent, SELECT_PHOTO);
    }

    private File createImageFile() {
        File imageTempFile = null;
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        File storageDir = getExternalFilesDir(null);
        try {
            imageTempFile = File.createTempFile(
                    timeStamp,
                    ".jpg",
                    storageDir
            );
        } catch (IOException ex) {
            Log.e(TAG, ex.toString());
        }
        return imageTempFile;
    }

}

