package com.xiaoxin.feng.jhang.util;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;

import java.io.File;
import java.util.ArrayList;

/**
 * @author: xiaoxin
 * date: 2018/9/17
 * describe: 分享适配：https://www.jianshu.com/p/c9f071c51eba
 * 修改内容:
 */
public class ShareUtil {

    /**
     * 检查软件是否安装过
     * @param context
     * @param packageName
     * @return
     */
    private static boolean checkInstallation(Context context, String packageName) {
        try {
            context.getPackageManager().getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    /**
     *
     * @param context
     * @param imageFile
     * @return content Uri
     */
    public static Uri getImageContentUri(Context context, File imageFile) {
        String filePath = imageFile.getAbsolutePath();
        Cursor cursor = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                new String[]{MediaStore.Images.Media._ID}, MediaStore.Images.Media.DATA + "=? ",
                new String[]{filePath}, null);
        if (cursor != null && cursor.moveToFirst()) {
            int id = cursor.getInt(cursor.getColumnIndex(MediaStore.MediaColumns._ID));
            Uri baseUri = Uri.parse("content://media/external/images/media");
            return Uri.withAppendedPath(baseUri, "" + id);
        } else {
            if (imageFile.exists()) {
                ContentValues values = new ContentValues();
                values.put(MediaStore.Images.Media.DATA, filePath);
                return context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            } else {
                return null;
            }
        }
    }

    /**
     *  分享https://www.jianshu.com/p/c9f071c51eba
     *  微信6.7.3 不能分享多图功能：https://www.jianshu.com/p/1158d7c20a8b
     * @param context
     * @param files
     */
    public static void originalShareImage(Context context, ArrayList<File> files) {
        Intent share_intent = new Intent();
        ArrayList<Uri> imageUris = new ArrayList<Uri>();
        Uri uri;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            for (File f : files) {
                Uri imageContentUri = getImageContentUri(context, f);
                imageUris.add(imageContentUri);
            }
        } else {
            for (File f : files) {
                imageUris.add(Uri.fromFile(f));
            }

        }
        share_intent.setAction(Intent.ACTION_SEND_MULTIPLE);//设置分享行为
        share_intent.setType("image/png");//设置分享内容的类型
        share_intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        share_intent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, imageUris);
        context.startActivity(Intent.createChooser(share_intent, "Share"));

    }

    public static void originalShareText(Context context,String content) {
        Intent share_intent = new Intent();

        share_intent.setAction(Intent.ACTION_SEND_MULTIPLE);//设置分享行为
        share_intent.setType("text/plain");//设置分享内容的类型
        share_intent.putExtra(Intent.EXTRA_TEXT,content);
        share_intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        context.startActivity(Intent.createChooser(share_intent, "Share"));

    }

}
