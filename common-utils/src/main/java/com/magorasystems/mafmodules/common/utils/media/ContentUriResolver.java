package com.magorasystems.mafmodules.common.utils.media;

import android.annotation.TargetApi;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.webkit.MimeTypeMap;

import com.magorasystems.mafmodules.common.utils.ParserUtils;

import java.io.File;

/**
 * @author S.A.Bobrischev
 *         Developed by Magora Team (magora-systems.com). 2016.
 */
public class ContentUriResolver {

    public static final String SCHEME_FILE = "file";
    public static final String SCHEME_CONTENT = "content";
    public static final String SCHEME_HTTP = "http";
    public static final String SCHEME_HTTPS = "https";
    public static final String TYPE_IMAGE = "image";
    public static final String TYPE_VIDEO = "video";
    public static final String TYPE_AUDIO = "audio";
    public static final String TYPE_CLOUD = "cloud";
    private static final String TAG = "Resolver";
    public static boolean isDebug;

    public static void setIsDebug(boolean isDebug) {
        ContentUriResolver.isDebug = isDebug;
    }

    /**
     * Get a file path from a Uri. This will get the the path for Storage Access
     * Framework Documents, as well as the _data field for the MediaStore and
     * other file-based ContentProviders.<br>
     * <br>
     * Callers should check whether the path is local before assuming it
     * represents a local file.
     *
     * @param context The context.
     * @param uri     The Uri to query.
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Nullable
    public static String getFilePath(final Context context, final Uri uri) {
        if (uri == null) {
            return null;
        }
        if (isDebug) {
            Log.d(TAG, "File - " +
                    "Authority: " + uri.getAuthority() +
                    ", Fragment: " + uri.getFragment() +
                    ", Port: " + uri.getPort() +
                    ", Query: " + uri.getQuery() +
                    ", Scheme: " + uri.getScheme() +
                    ", Host: " + uri.getHost() +
                    ", Segments: " + uri.getPathSegments().toString()
            );
        }

        boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");

                String fullPath = getPathFromExtSD(split);
                if (fullPath == null || fullPath.isEmpty()) {
                    return null;
                }
                return fullPath;
            } else if (isDownloadsDocument(uri)) {
                String id = DocumentsContract.getDocumentId(uri);
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

                return getDataColumn(context, contentUri, null, null);
            } else if (isMediaDocument(uri)) {
                String docId = DocumentsContract.getDocumentId(uri);
                String[] split = docId.split(":");
                String type = split[0];

                Uri contentUri = null;
                if (TYPE_IMAGE.equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if (TYPE_VIDEO.equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if (TYPE_AUDIO.equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                String selection = "_id=?";
                String[] selectionArgs = {
                        split[1]
                };

                return getDataColumn(context, contentUri, selection, selectionArgs);
            } else if (isGoogleDriveUri(uri)) {
                return TYPE_CLOUD;
            }
        } else if (SCHEME_CONTENT.equalsIgnoreCase(uri.getScheme())) {
            if (isGooglePhotosUri(uri)) {
                String contentPath = getContentFromSegments(uri.getPathSegments());
                if (contentPath == null || contentPath.isEmpty()) {
                    return null;
                }
                return getFilePath(context, Uri.parse(contentPath));
            }

            return getDataColumn(context, uri, null, null);
        } else if (SCHEME_FILE.equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }
        return null;
    }

    /**
     * Get a file path Uri from a Uri. This will get the the path for Storage Access
     * Framework Documents, as well as the _data field for the MediaStore and
     * other file-based ContentProviders.<br>
     * <br>
     * Callers should check whether the path is local before assuming it
     * represents a local file.
     *
     * @param context The context.
     * @param uri     The Uri to query.
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Nullable
    public static Uri getFileUri(final Context context, final Uri uri) {
        String path = getFilePath(context, uri);
        if (path != null) {
            File file = new File(path);
            if (file.exists()) {
                return Uri.fromFile(file);
            }
        }
        return null;
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is Google Photos.
     */
    public static boolean isGooglePhotosUri(Uri uri) {
        return ("com.google.android.apps.photos.content".equals(uri.getAuthority())
                || "com.google.android.apps.photos.contentprovider".equals(uri.getAuthority()));
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is Google Drive.
     */
    public static boolean isGoogleDriveUri(Uri uri) {
        return "com.google.android.apps.docs.storage".equals(uri.getAuthority());
    }

    /**
     * Get the value of the data column for this Uri. This is useful for
     * MediaStore Uris, and other file-based ContentProviders.
     *
     * @param context       The context.
     * @param uri           The Uri to query.
     * @param selection     (Optional) Filter used in the query.
     * @param selectionArgs (Optional) Selection arguments used in the query.
     * @return The value of the _data column, which is typically a file path.
     */
    private static String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {
        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {
                column
        };

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, null);
            if (cursor != null && cursor.moveToFirst()) {
                int column_index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(column_index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }

    /**
     * Get content:// from segment list
     * In the new Uri Authority of Google Photos, the last segment is not the content:// anymore
     * So let's iterate through all segments and find the content uri!
     *
     * @param segments The list of segment
     */
    private static String getContentFromSegments(Iterable<String> segments) {
        String contentPath = "";

        for (String item : segments) {
            if (item.startsWith("content://")) {
                contentPath = item;
                break;
            }
        }

        return contentPath;
    }

    /**
     * Check if a file exists on device
     *
     * @param filePath The absolute file path
     */
    private static boolean fileExists(String filePath) {
        File file = new File(filePath);

        return file.exists();
    }

    /**
     * Get full file path from external storage
     *
     * @param pathData The storage type and the relative path
     */
    private static String getPathFromExtSD(String[] pathData) {
        final String type = pathData[0];
        final String relativePath = "/" + pathData[1];
        String fullPath;

        // on my Sony devices (4.4.4 & 5.1.1), `type` is a dynamic string
        // something like "71F8-2C0A", some kind of unique id per storage
        // don't know any API that can get the root path of that storage based on its id.
        //
        // so no "primary" type, but let the check here for other devices
        if ("primary".equalsIgnoreCase(type)) {
            fullPath = Environment.getExternalStorageDirectory() + relativePath;
            if (fileExists(fullPath)) {
                return fullPath;
            }
        }

        // Environment.isExternalStorageRemovable() is `true` for external and internal storage
        // so we cannot relay on it.
        //
        // instead, for each possible path, check if file exists
        // we'll start with secondary storage as this could be our (physically) removable sd card
        fullPath = System.getenv("SECONDARY_STORAGE") + relativePath;
        if (fileExists(fullPath)) {
            return fullPath;
        }

        fullPath = System.getenv("EXTERNAL_STORAGE") + relativePath;
        if (fileExists(fullPath)) {
            return fullPath;
        }

        return fullPath;
    }

    @NonNull
    public static String getUriContentMimeType(Context context, Uri uri) {
        ContentResolver cR = context.getContentResolver();
        String type = cR.getType(uri);
        if (type == null) {
            type = getFileContentMimeType(uri);
        }
        return type == null ? "" : type;
    }

    @Nullable
    public static String getFileContentMimeType(@Nullable String fileUri) {
        if (fileUri == null) {
            return null;
        }
        String type = null;
        String extension = MimeTypeMap.getFileExtensionFromUrl(fileUri);
        if (extension != null) {
            type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
        }
        return type;
    }

    @Nullable
    private static String getFileContentMimeType(Uri fileUri) {
        if (fileUri == null) {
            return null;
        }
        return getFileContentMimeType(fileUri.toString());
    }

    public static boolean isImageContentType(@Nullable String contentType) {
        return contentType != null && contentType.startsWith(TYPE_IMAGE);
    }

    public static boolean isVideoContentType(String contentType) {
        return contentType != null && contentType.startsWith(TYPE_VIDEO);
    }

    public static boolean isFromWeb(@Nullable Uri uri) {
        if (uri == null) {
            return false;
        }
        String scheme = uri.getScheme();
        return SCHEME_HTTP.equals(scheme) || SCHEME_HTTPS.equals(scheme);
    }

    @Nullable
    public static Uri filePathToFileUri(@NonNull String path) {
        return ParserUtils.parseToUri(SCHEME_FILE + "//" + path);
    }

    public static boolean isFileLocal(@Nullable Uri url) {
        return url != null && url.getScheme().equals(SCHEME_FILE);
    }
}
