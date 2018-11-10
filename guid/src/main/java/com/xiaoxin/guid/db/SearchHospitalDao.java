package com.xiaoxin.guid.db;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.SystemClock;
import android.util.Log;

import com.xiaoxin.guid.bean.search.DrugDetailBean;
import com.xiaoxin.guid.bean.search.TruthBean;

import java.util.List;

/**
 * @author: xiaoxin
 * date: 2018/10/29
 * describe:
 * 修改内容:
 */
public class SearchHospitalDao {

    private static final String TAG = "SearchHospitalDao";

    public static final String PATH =
            "/data/data/com.xiaoxin.feng.jhang/files/guidance.db";

    /**
     * 插入症状数据
     * @param list
     */
    public static void insertJbingDetail(String id,String list, String title) {
        long startTime = SystemClock.elapsedRealtime();
        SQLiteDatabase db = null;

        try{
            db = SQLiteDatabase.openDatabase(PATH, null, SQLiteDatabase.OPEN_READWRITE);
            db.beginTransaction();

                ContentValues values = new ContentValues();
                values.put("jbId", id);
                values.put("title", title);
                values.put("content", list);

                db.insert("jibing", null, values);

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
        Log.e(TAG, "插入时间"+time+"毫秒" + "  数量");
    }

    /**
     * 插入症状数据
     * @param itemsBean
     */
    public static void insertTruth(TruthBean.DataBean.ItemsBean itemsBean) {
        long startTime = SystemClock.elapsedRealtime();
        SQLiteDatabase db = null;

        try{
            db = SQLiteDatabase.openDatabase(PATH, null, SQLiteDatabase.OPEN_READWRITE);
            db.beginTransaction();

            ContentValues values = new ContentValues();
            values.put("truth_id", itemsBean.getId());
            values.put("title", itemsBean.getTitle());
            values.put("time", itemsBean.getTime());
            values.put("content", itemsBean.getContent());
            values.put("date", itemsBean.getDate());
            values.put("qrcode", itemsBean.getQrcode());

            db.insert("truth", null, values);

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
        Log.e(TAG, "插入时间"+time+"毫秒" + "  数量");
    }

    /**
     * 插入症状数据
     * @param list
     */
    public static void insertDrug(List<DrugDetailBean.DataBean.ItemsBean> list,String drugType) {
        long startTime = SystemClock.elapsedRealtime();
        SQLiteDatabase db = null;

        try{
            db = SQLiteDatabase.openDatabase(PATH, null, SQLiteDatabase.OPEN_READWRITE);
            db.beginTransaction();
            for (int i = 0; i < list.size(); i++) {
                ContentValues values = new ContentValues();
                values.put("drug_ids", list.get(i).getId());
                values.put("name",  list.get(i).getName());
                values.put("drug_type", drugType);
                values.put("brand_name", list.get(i).getBrand_name());
                values.put("packing_product", list.get(i).getPacking_product());
                values.put("unit_price",  list.get(i).getUnit_price());
                values.put("drug_id", list.get(i).getDrug_id());
                values.put("thumbnail_url", list.get(i).getThumbnail_url());
                values.put("product_img_url", list.get(i).getProduct_img_url());
                values.put("product_inventory", list.get(i).getProduct_inventory());
                values.put("manufacturer", list.get(i).getManufacturer());
                values.put("outer_drug_id", list.get(i).getOuter_drug_id());
                values.put("approval_number", list.get(i).getApproval_number());
                values.put("supplier_id", list.get(i).getSupplier_id());
                values.put("supplies_drug_no", list.get(i).getSupplies_drug_no());
                if (list.get(i).isPrescription()) {
                    values.put("prescription", 1);
                }else {
                    values.put("prescription", 0);
                }
                values.put("prescription_type", list.get(i).getPrescription_type());
                values.put("count", list.get(i).getCount());
                values.put("characters", list.get(i).getCharacters());
                values.put("indication", list.get(i).getIndication());
                values.put("usage", list.get(i).getUsage());
                values.put("adverse_reaction", list.get(i).getAdverse_reaction());
                values.put("contraindication", list.get(i).getContraindication());
                values.put("notice", list.get(i).getNotice());
                values.put("interaction", list.get(i).getInteraction());
                values.put("storage", list.get(i).getStorage());
                values.put("pharmacology_and_toxicology", list.get(i).getPharmacology_and_toxicology());
                values.put("productmainmaterial", list.get(i).getProductmainmaterial());
                values.put("packing", list.get(i).getPacking());

                db.insert("drug", null, values);
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


}
