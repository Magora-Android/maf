package com.magorasystems.rx.images.util;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.graphics.drawable.animated.BuildConfig;
import android.util.Log;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Anton Vlasov 2016.
 *         Developed by Magora-Systems.com
 */
public class BitmapLoadUtils {
    public static final int MAX_BITMAP_WIDTH = 1080;
    public static final int MAX_BITMAP_HEIGHT = 1920;
    private static final String TAG = BitmapLoadUtils.class.getSimpleName();

    /**
     * Calculated inSampleSize by default width and height
     *
     * @param options options for getting {@link BitmapFactory.Options#outHeight} and {@link BitmapFactory.Options#outWidth} for calculating
     * @return {@link BitmapFactory.Options#inSampleSize}
     * @see BitmapLoadUtils#MAX_BITMAP_HEIGHT
     * @see BitmapLoadUtils#MAX_BITMAP_WIDTH
     */
    public static int calculateInSampleSize(@NonNull BitmapFactory.Options options) {
        int height = options.outHeight;
        int width = options.outWidth;
        int inSampleSize = 1;

        if (height > MAX_BITMAP_HEIGHT || width > MAX_BITMAP_WIDTH) {
            int halfHeight = height / 2;
            int halfWidth = width / 2;
            inSampleSize = 2;

            while ((halfHeight / inSampleSize) > MAX_BITMAP_HEIGHT && (halfWidth / inSampleSize) > MAX_BITMAP_WIDTH) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }

    /**
     * Calculated inSampleSize with yout width and height
     *
     * @param options   options for getting {@link BitmapFactory.Options#outHeight} and {@link BitmapFactory.Options#outWidth} for calculating
     * @param reqHeight height
     * @param reqWidth  width
     * @return {@link BitmapFactory.Options#inSampleSize}
     * @see BitmapLoadUtils#MAX_BITMAP_HEIGHT
     * @see BitmapLoadUtils#MAX_BITMAP_WIDTH
     */
    public static int calculateInSampleSize(@NonNull BitmapFactory.Options options, int reqWidth, int reqHeight) {
        int height = options.outHeight;
        int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            int halfHeight = height / 2;
            int halfWidth = width / 2;
            inSampleSize = 2;

            while ((halfHeight / inSampleSize) > reqHeight && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }

    @Nullable
    public static Bitmap transformBitmap(Bitmap bitmap, int exifOrientation) {
        int exifDegrees = exifToDegrees(exifOrientation);
        int exifTranslation = exifToTranslation(exifOrientation);

        Matrix matrix = new Matrix();
        if (exifDegrees != 0) {
            matrix.preRotate(exifDegrees);
        }

        if (exifTranslation != -1) {
            matrix.postScale(exifTranslation, 1);
        }

        if (!matrix.isIdentity()) {
            return transformBitmap(bitmap, matrix);
        }

        return bitmap;
    }

    public static Bitmap scaleBitmap(Bitmap bitmap, int rotation) {
        int bitmapW = bitmap.getWidth();
        int bitmapH = bitmap.getHeight();

        if (bitmapW <= MAX_BITMAP_WIDTH && bitmapH <= MAX_BITMAP_HEIGHT) {
            Log.d(TAG, "scaleBitmap: don`t needed be scaled");
            return bitmap;
        }

        float ratio = 1;
        if (bitmapW > MAX_BITMAP_WIDTH) {
            ratio = (float) MAX_BITMAP_WIDTH / (float) bitmapW;
        } else if (bitmapH > MAX_BITMAP_HEIGHT) {
            ratio = (float) MAX_BITMAP_HEIGHT / (float) bitmapH;
        }

        int correctWidth = (int) (bitmapW * ratio);
        int correctHeight = (int) (bitmapH * ratio);

        Bitmap result = Bitmap.createScaledBitmap(bitmap, correctWidth, correctHeight, true);
        result = rotate(result, rotation);

        if (result != bitmap) {
            bitmap.recycle();
        }

        return result;
    }

    public static int getExifOrientation(@NonNull Context context, @NonNull Uri uri) {
        int orientation = ExifInterface.ORIENTATION_UNDEFINED;
        try {
            InputStream stream = context.getContentResolver().openInputStream(uri);
            if (stream == null) {
                return orientation;
            }
            orientation = new ImageHeaderParser(stream).getOrientation();
            close(stream);
        } catch (IOException e) {
            Log.e(TAG, "getExifOrientation: " + uri.toString(), e);
        }
        return orientation;
    }

    public static int getRotationFromCamera(Context context, @NonNull Uri imageFile) {
        int rotate = 0;
        try {
            context.getContentResolver().notifyChange(imageFile, null);
            ExifInterface exif = new ExifInterface(imageFile.getPath());
            int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);

            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_270:
                    rotate = 270;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    rotate = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_90:
                    rotate = 90;
                    break;
            }
        } catch (Exception e) {
            if (BuildConfig.DEBUG) {
                e.printStackTrace();
            }
        }
        return rotate;
    }

    public static int getRotationFromGallery(Context context, @NonNull Uri imageUri) {
        String[] columns = {MediaStore.Images.Media.ORIENTATION};
        Cursor cursor = context.getContentResolver().query(imageUri, columns, null, null, null);
        if (cursor == null) return 0;

        cursor.moveToFirst();

        int orientationColumnIndex = cursor.getColumnIndex(columns[0]);
        try {
            return cursor.getInt(orientationColumnIndex);
        } finally {
            cursor.close();
        }
    }

    public static BitmapFactory.Options compressByOptions(BitmapFactory.Options options, Uri uri) {
        options.inJustDecodeBounds = true;
        options.inPreferredConfig = Bitmap.Config.RGB_565;

        BitmapFactory.decodeFile(uri.getPath(), options);

        if (options.outHeight == -1 || options.outWidth == -1) {
            Log.d(TAG, "compress: Bounds for bitmap could not be retrieved from the Uri: " + uri);
            // TODO: 22.08.2016 resize to size hard
        }

        options.inSampleSize = BitmapLoadUtils.calculateInSampleSize(options);

        options.inJustDecodeBounds = false;
        return options;
    }

    public void logBitmap(Bitmap bitmap) {
        Log.d(TAG, "logBitmap: height = " + bitmap.getHeight());
        Log.d(TAG, "logBitmap: width = " + bitmap.getWidth());
        Log.d(TAG, "logBitmap: MB = " + (float) bitmap.getByteCount() / (1024 * 1024));
        Log.d(TAG, "logBitmap: row bytes = " + bitmap.getRowBytes());
        Log.d(TAG, "logBitmap: config = " + bitmap.getConfig());
    }

    private static Bitmap rotate(Bitmap bm, int rotation) {
        if (rotation != 0) {
            Matrix matrix = new Matrix();
            matrix.postRotate(rotation);
            return Bitmap.createBitmap(bm, 0, 0, bm.getWidth(), bm.getHeight(), matrix, true);
        }
        return bm;
    }

    private static Bitmap transformBitmap(@NonNull Bitmap bitmap, @NonNull Matrix transformMatrix) {
        try {
            Bitmap converted = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), transformMatrix, true);
            if (!bitmap.sameAs(converted)) {
                bitmap = converted;
            }
        } catch (OutOfMemoryError error) {
            Log.e(TAG, "transformBitmap: ", error);
        }
        return bitmap;
    }

    private static int exifToDegrees(int exifOrientation) {
        int rotation;
        switch (exifOrientation) {
            case ExifInterface.ORIENTATION_ROTATE_90:
            case ExifInterface.ORIENTATION_TRANSPOSE:
                rotation = 90;
                break;
            case ExifInterface.ORIENTATION_ROTATE_180:
            case ExifInterface.ORIENTATION_FLIP_VERTICAL:
                rotation = 180;
                break;
            case ExifInterface.ORIENTATION_ROTATE_270:
            case ExifInterface.ORIENTATION_TRANSVERSE:
                rotation = 270;
                break;
            default:
                rotation = 0;
        }
        return rotation;
    }

    private static int exifToTranslation(int exifOrientation) {
        int translation;
        switch (exifOrientation) {
            case ExifInterface.ORIENTATION_FLIP_HORIZONTAL:
            case ExifInterface.ORIENTATION_FLIP_VERTICAL:
            case ExifInterface.ORIENTATION_TRANSPOSE:
            case ExifInterface.ORIENTATION_TRANSVERSE:
                translation = -1;
                break;
            default:
                translation = 1;
        }
        return translation;
    }

    private static void close(@Nullable Closeable c) {
        if (c != null && c instanceof Closeable) { // java.lang.IncompatibleClassChangeError: interface not implemented
            try {
                c.close();
            } catch (IOException e) {
                // silence
            }
        }
    }
}
