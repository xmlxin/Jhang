package com.xiaoxin.guid.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.SystemClock;
import android.util.Log;

import com.xiaoxin.guid.bean.BwBean;
import com.xiaoxin.guid.bean.GuidanceBean;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: xiaoxin
 * date: 2018/10/16
 * describe:
 * 修改内容:
 */
public class Dbdao {

    private static final String TAG = "Dbdao";

    //    /** 药品数据库 路径 /data/user/0/com.xiaoxin.feng.jhang/files */
    public static final String PATH =
            "/data/data/com.xiaoxin.feng.jhang/files/guidance.db";

    /**
     * 拷贝数据库文件
     * @param context 上下文
     * @param db 数据库文件名
     */
    public static void copyAddressDB(final Context context, final String db) {
        File file = new File(context.getFilesDir(),db);
        if(file.exists() && file.length()>0) {
            Log.e(TAG, "数据库存在无需拷贝");
        }else {
            new Thread() {
                public void run() {
                    //把assets资产牡蛎里面的数据库文件拷贝到手机系统里面
                    try {
                        InputStream is = context.getAssets().open(db);
                        File file = new File(context.getFilesDir(),db);
                        FileOutputStream fos = new FileOutputStream(file);

                        Log.e(TAG, "数据库路径地址："+context.getFilesDir());
                        byte[] b = new byte[1024];
                        int len = -1;
                        while((len = is.read(b))!=-1) {
                            fos.write(b, 0, len);
                        }
                        is.close();
                        fos.close();
                        Log.e(TAG, "拷贝成功");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                };
            }.start();
        }
    }

    /**
     * 插入排序过的西药表 数据
     * @param list
     */
    public static void insertSymptom(List<BwBean.DataBean> list,String sex,String part) {
        long startTime = SystemClock.elapsedRealtime();
        String path = "/data/data/com.nova.ysk.doctor/files/drug.db";//OPEN_READONLY
        SQLiteDatabase db = null;

        try{
            db = SQLiteDatabase.openDatabase(PATH, null, SQLiteDatabase.OPEN_READWRITE);
            db.beginTransaction();
            for (int i = 0; i < list.size(); i++) {
                ContentValues values = new ContentValues();
                values.put("id", list.get(i).id);
                values.put("symptomName", list.get(i).symptomName);
                values.put("part",part);
                values.put("sex", sex);
                db.insert("symptom", null, values);
            }
            db.setTransactionSuccessful();
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            if (db != null) {
                db.endTransaction();
                db.close();
            }
        }
        long time = SystemClock.elapsedRealtime()-startTime;
        Log.e(TAG, "插入时间"+time+"毫秒" + "  数量"+list.size());
    }

    /**
     * 插入症状数据
     * @param list
     */
    public static void insertDisease(List<BwBean.DataBean> list,String sex,String part,String id) {
        long startTime = SystemClock.elapsedRealtime();
        String path = "/data/data/com.nova.ysk.doctor/files/drug.db";//OPEN_READONLY
        SQLiteDatabase db = null;

        try{
            db = SQLiteDatabase.openDatabase(PATH, null, SQLiteDatabase.OPEN_READWRITE);
            db.beginTransaction();
            for (int i = 0; i < list.size(); i++) {
                ContentValues values = new ContentValues();
                values.put("id", list.get(i).id);//此次查询返回的id
                values.put("name", list.get(i).name);
                values.put("symptomCourseId", list.get(i).symptomCourseId);
                values.put("part",part);
                values.put("sex", sex);
                values.put("symptomId", id);//上一次查询的id
                db.insert("disease", null, values);
            }
            db.setTransactionSuccessful();
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            if (db != null) {
                db.endTransaction();
                db.close();
            }
        }
        long time = SystemClock.elapsedRealtime()-startTime;
        Log.e(TAG, "插入时间"+time+"毫秒" + "  数量"+list.size());
    }

    /**
     * 插入症状数据
     * @param list
     */
    public static void insertDisease1(List<BwBean.DataBean> list,String sex,String part,String id) {
        long startTime = SystemClock.elapsedRealtime();
        String path = "/data/data/com.nova.ysk.doctor/files/drug.db";//OPEN_READONLY
        SQLiteDatabase db = null;

        try{
            db = SQLiteDatabase.openDatabase(PATH, null, SQLiteDatabase.OPEN_READWRITE);
            db.beginTransaction();
            for (int i = 0; i < list.size(); i++) {
                ContentValues values = new ContentValues();
                values.put("id", list.get(i).id);//此次查询返回的id
                values.put("name", list.get(i).name);
                values.put("symptomCourseId", list.get(i).symptomCourseId);
                values.put("part",part);
                values.put("sex", sex);
                values.put("symptomId", id);//上一次查询的id
                db.insert("disease", null, values);
            }
            db.setTransactionSuccessful();
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            if (db != null) {
                db.endTransaction();
                db.close();
            }
        }
        long time = SystemClock.elapsedRealtime()-startTime;
        Log.e(TAG, "插入时间"+time+"毫秒" + "  数量"+list.size());
    }

    /**
     * 查询主症状列表
     * @return
     */
    public static ArrayList<GuidanceBean> querySymptom() {
        Log.e(TAG, "开始时间"+ SystemClock.elapsedRealtime());
        long startTime = SystemClock.elapsedRealtime();
        ArrayList<GuidanceBean> guidanceBeanArrayList = new ArrayList<>();
        String path = "/data/data/com.nova.ysk.doctor/files/drug.db";//OPEN_READONLY
        SQLiteDatabase db = null;
        Cursor cursor = null;
        try {
            db = SQLiteDatabase.openDatabase(PATH, null, SQLiteDatabase.OPEN_READWRITE);
            db.beginTransaction();
            cursor = db.rawQuery(
                    "select id,sex,part from symptom",null);
            db.setTransactionSuccessful();
            Log.e("xiaoxin", "getWestDrug: "+cursor.getCount() );
            if(cursor != null){
                while (cursor.moveToNext()) {
                    GuidanceBean guidanceBean = new GuidanceBean();
                    guidanceBean.setId(cursor.getString(cursor.getColumnIndex("id")));
                    guidanceBean.setSex(cursor.getString(cursor.getColumnIndex("sex")));
                    guidanceBean.setPart(cursor.getString(cursor.getColumnIndex("part")));
                    guidanceBeanArrayList.add(guidanceBean);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (cursor != null) {
                cursor.close();
            }
            if (db != null) {
                db.endTransaction();
                db.close();
            }
        }
        long time = SystemClock.elapsedRealtime()-startTime;
        Log.e(TAG, "执行时间"+time+"毫秒");
        return guidanceBeanArrayList;
    }


}
