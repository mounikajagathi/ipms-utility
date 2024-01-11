package in.ults.gisurvey.utils;

import android.annotation.TargetApi;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import in.ults.gisurvey.BuildConfig;

public class FileUtils {

    public static final String MIME_TYPE_IMAGE = "image/*";
    private static final String[] FILE_TYPES_IMAGES = {".jpg", ".png", ".jpeg"};
    private static final String AUTHORITY = BuildConfig.APPLICATION_ID + ".localstorage.documents";
    private static final boolean DEBUG = false;
    public static final String DEFAULT_IMAGE_EXTENSION = ".jpg";
    public static final String IMAGE_BACKUP_FOLDER_NAME="surveyimagebackup";



    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is {@link FileUtils}.
     */
    private static boolean isLocalStorageDocument(Uri uri) {
        return AUTHORITY.equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    private static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    private static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    private static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is Google Photos.
     */
    private static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }


    //App default document storage location
    public static File getAppFileDirectory() {
        File myDir = new File(Environment.getExternalStorageDirectory(), "files");
        try {
            // Find the dir to save cached images
            myDir = new File(Environment.getExternalStorageDirectory(), "android/data/" + BuildConfig.APPLICATION_ID + "/files");
            if (!myDir.exists())
                if (myDir.mkdirs()) {
                    Log.d("FileUtils", "Files Directory Created");
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myDir;
    }

    //App default document storage location
    public static File getSurveyImageBackupDirectoryAboveQ() {
        File myDir = new File(Environment.DIRECTORY_DCIM,  IMAGE_BACKUP_FOLDER_NAME);

        try {
            // Find the dir to save cached images
            myDir = new File(Environment.DIRECTORY_DCIM, IMAGE_BACKUP_FOLDER_NAME);
            if (!myDir.exists())
                if (myDir.mkdirs()) {
                    Log.d("FileUtils", "Files Directory Created");
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myDir;
    }
    //App default document storage location
    public static File getSurveyImageBackupDirectoryForBelowQ() {
        File myDir = new File(Environment.getExternalStorageDirectory(), ".surveyimagebackup");
        try {
            // Find the dir to save cached images
            myDir = new File(Environment.getExternalStorageDirectory(), ".surveyimagebackup");
            if (!myDir.exists())
                if (myDir.mkdirs()) {
                    Log.d("FileUtils", "Files Directory Created");
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myDir;
    }
    public static File getSurveyDataBackupDirectory() {
        File myDir = new File(Environment.getExternalStorageDirectory(), ".surveydatabackup");
        try {
            // Find the dir to save cached images
            myDir = new File(Environment.getExternalStorageDirectory(), ".surveydatabackup");
            if (!myDir.exists())
                if (myDir.mkdirs()) {
                    Log.d("FileUtils", "Files Directory Created");
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myDir;
    }

    //App default document storage location
    public static File getCaptureImageDirectory() {
        File myDir = new File(Environment.getExternalStorageDirectory(), "files");
        try {
            // Find the dir to save cached images
            myDir = new File(Environment.getExternalStorageDirectory(), "ipms");
            if (!myDir.exists())
                if (myDir.mkdirs()) {
                    Log.d("FileUtils", "Files Directory Created");
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myDir;
    }

    private static String getExtension(String uri) {
        if (uri == null) {
            return null;
        }
        int dot = uri.lastIndexOf(".");
        if (dot >= 0) {
            return uri.substring(dot);
        } else {
            // No extension.
            return "";
        }
    }


    public static Boolean isValidImageFile(String fileUri) {
        boolean supportedConfiguration = false;
        try {
            if (fileUri != null) {
                String fileType = FileUtils.getExtension(fileUri);
                for (String FILE_TYPES_IMAGE : FILE_TYPES_IMAGES) {
                    if (fileType.equalsIgnoreCase(FILE_TYPES_IMAGE)) {
                        supportedConfiguration = true;
                    }
                }
            }
        } catch (Exception e) {
            supportedConfiguration = false;
            e.printStackTrace();
        }
        return supportedConfiguration;
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
    private static String getDataColumn(Context context, Uri uri, String selection,
                                        String[] selectionArgs) {

        final String column = "_data";
        final String[] projection = {
                column
        };

        try (Cursor cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                null)) {
            if (cursor != null && cursor.moveToFirst()) {
                if (DEBUG)
                    DatabaseUtils.dumpCursor(cursor);

                final int column_index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(column_index);
            }
        }
        return null;
    }


    @TargetApi(Build.VERSION_CODES.KITKAT)
    public static String getPath(final Context context, final Uri uri) {
        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            // LocalStorageProvider
            if (isLocalStorageDocument(uri)) {
                // The path is the id
                return DocumentsContract.getDocumentId(uri);
            }
            // ExternalStorageProvider
            else if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }

                // TODO handle non-primary volumes
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {

                /*final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));
                return getDataColumn(context, contentUri, null, null);*/

                final String id = DocumentsContract.getDocumentId(uri);
                if (id != null && id.startsWith("raw:")) {
                    return id.substring(4);
                }
                String[] contentUriPrefixesToTry = new String[]{
                        "content://downloads/public_downloads",
                        "content://downloads/my_downloads",
                        "content://downloads/all_downloads"
                };
                for (String contentUriPrefix : contentUriPrefixesToTry) {
                    Uri contentUri = null;
                    if (id != null) {
                        contentUri = ContentUris.withAppendedId(Uri.parse(contentUriPrefix), Long.parseLong(id));
                    }
                    try {
                        String path = getDataColumn(context, contentUri, null, null);
                        if (path != null) {
                            return path;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{
                        split[1]
                };

                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {

            // Return the remote address
            if (isGooglePhotosUri(uri))
                return uri.getLastPathSegment();

            return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }

    public static boolean reduceImageSizeBelowQ(File file, String imageName,String imageType) {
        try {
            // BitmapFactory options to downsize the image
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            o.inSampleSize = 6;
            // factor of downsizing the image
            FileInputStream inputStream = new FileInputStream(file);
            //Bitmap selectedBitmap = null;
            BitmapFactory.decodeStream(inputStream, null, o);
            inputStream.close();
            // The new size we want to scale to
            final int REQUIRED_SIZE;
            if(imageType.equalsIgnoreCase(AppConstants.IMG_THREE))
            {
                REQUIRED_SIZE=70;
            }else{
                REQUIRED_SIZE=40;
            }
            // Find the correct scale value. It should be the power of 2.
            int scale = 1;
            while (o.outWidth / scale / 2 >= REQUIRED_SIZE &&
                    o.outHeight / scale / 2 >= REQUIRED_SIZE) {
                scale *= 2;
            }
            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize = scale;
            inputStream = new FileInputStream(file);
            Bitmap selectedBitmap = BitmapFactory.decodeStream(inputStream, null, o2);
            inputStream.close();
            // here i override the original image file
            file.createNewFile();

            File outFile = new File(getAppFileDirectory(), imageName);
            File backUpFile = new File(getSurveyImageBackupDirectoryForBelowQ(), imageName);

            if (outFile.exists()) {
                boolean deleteMe = outFile.delete();
            }
            if (backUpFile.exists()) {
                boolean deleteMe = backUpFile.delete();
            }

            FileOutputStream outputStream = new FileOutputStream(outFile);
            FileOutputStream backUpStream = new FileOutputStream(backUpFile);
            if (selectedBitmap != null) {
                selectedBitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                selectedBitmap.compress(Bitmap.CompressFormat.JPEG, 100, backUpStream);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public static boolean reduceImageSizeAboveQ(Context context, File file, String imageName, String imageType) {
        try {
            // BitmapFactory options to downsize the image
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            o.inSampleSize = 6;
            // factor of downsizing the image
            FileInputStream inputStream = new FileInputStream(file);
            //Bitmap selectedBitmap = null;
            BitmapFactory.decodeStream(inputStream, null, o);
            inputStream.close();
            // The new size we want to scale to
            final int REQUIRED_SIZE;
            if(imageType.equalsIgnoreCase(AppConstants.IMG_THREE))
            {
                REQUIRED_SIZE=70;
            }else{
                REQUIRED_SIZE=40;
            }
            // Find the correct scale value. It should be the power of 2.
            int scale = 1;
            while (o.outWidth / scale / 2 >= REQUIRED_SIZE &&
                    o.outHeight / scale / 2 >= REQUIRED_SIZE) {
                scale *= 2;
            }
            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize = scale;
            inputStream = new FileInputStream(file);
            Bitmap selectedBitmap = BitmapFactory.decodeStream(inputStream, null, o2);
            inputStream.close();
            ContentResolver resolver = context.getContentResolver();
            ContentValues contentValues = new ContentValues();
            contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, imageName);
            contentValues.put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_DCIM + File.separator + "surveyimagebackup");
            Uri imageUri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
            OutputStream  fos = resolver.openOutputStream(imageUri);
            if (selectedBitmap != null) {
                selectedBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public static void copy(File src, File dst) throws IOException {
        InputStream in = new FileInputStream(src);
        try {
            OutputStream out = new FileOutputStream(dst);
            try {
                // Transfer bytes from in to out
                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
            } finally {
                out.close();
            }
        } finally {
            in.close();
        }
    }


    public static List<File> getSurveyImages(List<String> selectedList) {
        List<File> data = new ArrayList<>();
        File fileDir = null;
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
            fileDir  = getBackupImageDirectoryForAboveQ();
        }else{
            fileDir = getSurveyImageBackupDirectoryBelowQ();
        }
        File[] files = fileDir.listFiles();
        if (files != null) {
            for (File image : files) {
                if (selectedList.contains(image.getName())) {
                    data.add(image);
                }
            }
        }
        return data;
    }

    public static void removeSurveyImages(List<String> selectedList) {
        File fileDir = getAppFileDirectory();
        File[] files = fileDir.listFiles();
        if (files != null) {
            for (File image : files) {
                if (selectedList.contains(image.getName())) {
                    String deleteCmd = "rm -r " + image;
                    Runtime runtime = Runtime.getRuntime();
                    try {
                        runtime.exec(deleteCmd);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


    public static boolean isValidTpkFile(String fileUri) {
        boolean supportedConfiguration = false;
        try {
            if (fileUri != null) {
                String fileType = FileUtils.getExtension(fileUri);
                if (fileType.equalsIgnoreCase(".tpk")) {
                    supportedConfiguration = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return supportedConfiguration;
    }

    public static boolean isValidTxtFile(String fileUri) {
        boolean supportedConfiguration = false;
        try {
            if (fileUri != null) {
                String fileType = FileUtils.getExtension(fileUri);
                if (fileType.equalsIgnoreCase(".txt")) {
                    supportedConfiguration = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return supportedConfiguration;
    }

    public static StringBuilder getDataFromFile(String fileLocation) {
        File backupTxtFile = new File(fileLocation);
        //Read text from file
        StringBuilder text = new StringBuilder();

        try {
            BufferedReader br = new BufferedReader(new FileReader(backupTxtFile));
            String line;

            while ((line = br.readLine()) != null) {
                text.append(line);
                text.append('\n');
            }
            br.close();
        } catch (IOException e) {
            //You'll need to add proper error handling here
        }
        return text;

    }

    public static String getTimeStampForSave() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
        String timeStamp = sdf.format(Calendar.getInstance().getTime()); // reading local time in the system
        return timeStamp;
    }

    /**
     * @param message the message to be encoded
     *
     * @return the enooded from of the message
     */
    public static String toBase64(String message) {
        byte[] data;
        try {
            data = message.getBytes("UTF-8");
            String base64Sms = Base64.encodeToString(data, Base64.DEFAULT);
            return base64Sms;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * @param message the encoded message
     *
     * @return the decoded message
     */
    public static String fromBase64(String message) {
        byte[] data = Base64.decode(message, Base64.DEFAULT);
        try {
            return new String(data, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static boolean isValidXLSFile(String fileUri) {
        boolean supportedConfiguration = false;
        try {
            if (fileUri != null) {
                String fileType = FileUtils.getExtension(fileUri);
                if (fileType.equalsIgnoreCase(".xls")) {
                    supportedConfiguration = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return supportedConfiguration;
    }

    public static boolean isValidInfoVideoFile(String fileUri) {
        boolean supportedConfiguration = false;
        try {
            if (fileUri != null) {
                String fileType = FileUtils.getExtension(fileUri);
                if (fileType.equalsIgnoreCase(".mp4")) {
                    supportedConfiguration = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return supportedConfiguration;
    }
    public static String getBackupImagePath(String imageName){
        String path="";
        path=Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath()+File.separator+IMAGE_BACKUP_FOLDER_NAME+File.separator+imageName;
        return path;
    }
    public static File getBackupImageDirectoryForAboveQ(){
        File path=null;
        path=Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM+File.separator+IMAGE_BACKUP_FOLDER_NAME);
        return path;
    }
    //App default document storage location
    public static File getSurveyImageBackupDirectoryBelowQ() {
        File myDir = new File(Environment.getExternalStorageDirectory(), ".surveyimagebackup");
        try {
            // Find the dir to save cached images
            myDir = new File(Environment.getExternalStorageDirectory(), ".surveyimagebackup");
            if (!myDir.exists())
                if (myDir.mkdirs()) {
                    Log.d("FileUtils", "Files Directory Created");
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myDir;
    }
}
