package com.magorasystems.rx.images;

import android.app.Application;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.BuildConfig;
import android.util.Log;

import com.magorasystems.rx.images.util.BitmapLoadUtils;
import com.magorasystems.rx.images.util.FileUtils;

import rx.Observable;

/**
 * @author Anton Vlasov 2016.
 *         Developed by Magora-Systems.com
 */
public class RxImage {
    private static final String TAG = RxImage.class.getSimpleName();

    private static RxImage instance;

    private Context context;

    private RxImage(Context context) {
        this.context = context;
    }

    public static void register(Application application) {
        if (instance == null) {
            instance = new RxImage(application);
        }
    }

    public static RxImage with(Context context) {
        if (instance == null) {
            return instance = new RxImage(context.getApplicationContext());
        }
        return instance;
    }

    /**
     * Compress bitmap by scale, rotation,  ...
     *
     * @param uri uri for your image
     * @return Observable Bitmap
     */

    public static Observable<Bitmap> processBitmap(@NonNull final Uri uri) {
        return Observable.create(subscriber -> {
            if (instance == null) {
                subscriber.onCompleted();
                subscriber.onError(new RuntimeException("RxImage is not initialization yet."));
                return;
            }
            Log.d(TAG, "processBitmap: raw uri " + uri.toString());
            String realPath = FileUtils.getPath(instance.context, uri);

            if (!FileUtils.isValidPath(realPath)) { //validation uri
                Log.e(TAG, "processBitmap: realPath is invalid");
                subscriber.onCompleted();
                subscriber.onError(new RuntimeException("processBitmap: realPath \'"
                        + realPath + "\' is invalid"));
                return;
            }

            Uri uriWithRealPath = FileUtils.getUriFromPath(realPath); // it`s real path

            BitmapFactory.Options options = new BitmapFactory.Options();
            options = BitmapLoadUtils.compressByOptions(options, uri); // compress

            Bitmap bitmap = BitmapFactory.decodeFile(uriWithRealPath.getPath(), options);

            bitmap = BitmapLoadUtils.transformBitmap(bitmap,
                    BitmapLoadUtils.getExifOrientation(instance.context, uriWithRealPath)); // rotate

            // FIXME: 22.08.2016 rotation from camera
            bitmap = BitmapLoadUtils.scaleBitmap(bitmap,
                    BitmapLoadUtils.getRotationFromGallery(instance.context, uriWithRealPath)); // resize if needed
            if (!subscriber.isUnsubscribed()) {
                subscriber.onNext(bitmap);
            } else {
                subscriber.onCompleted();
            }
        });
    }

    /**
     * Attempt to retrieve the thumbnail of given Uri from the MediaStore. This
     * should not be called on the UI thread.
     *
     * @param context
     * @param uri
     * @return
     * @author paulburke
     */
    public static Bitmap getThumbnail(Context context, Uri uri) {
        return getThumbnail(context, uri, FileUtils.getMimeType(context, uri));
    }

    /**
     * Attempt to retrieve the thumbnail of given Uri from the MediaStore. This
     * should not be called on the UI thread.
     *
     * @param context
     * @param uri
     * @param mimeType
     * @return
     * @author paulburke
     */
    public static Bitmap getThumbnail(Context context, Uri uri, String mimeType) {
        if (BuildConfig.DEBUG)
            Log.d(TAG, "Attempting to get thumbnail");

        if (!FileUtils.isMediaUri(uri)) {
            Log.e(TAG, "You can only retrieve thumbnails for images and videos.");
            return null;
        }

        Bitmap bm = null;
        if (uri != null) {
            final ContentResolver resolver = context.getContentResolver();
            Cursor cursor = null;
            try {
                cursor = resolver.query(uri, null, null, null, null);
                if (cursor != null && cursor.moveToFirst()) {
                    final int id = cursor.getInt(0);
                    if (BuildConfig.DEBUG)
                        Log.d(TAG, "Got thumb ID: " + id);

                    if (mimeType.contains("video")) {
                        bm = MediaStore.Video.Thumbnails.getThumbnail(
                                resolver,
                                id,
                                MediaStore.Video.Thumbnails.MINI_KIND,
                                null);
                    } else if (mimeType.contains(FileUtils.MIME_TYPE_IMAGE)) {
                        bm = MediaStore.Images.Thumbnails.getThumbnail(
                                resolver,
                                id,
                                MediaStore.Images.Thumbnails.MINI_KIND,
                                null);
                    }
                }
            } catch (Exception e) {
                if (BuildConfig.DEBUG)
                    Log.e(TAG, "getThumbnail", e);
            } finally {
                if (cursor != null)
                    cursor.close();
            }
        }
        return bm;
    }
}
