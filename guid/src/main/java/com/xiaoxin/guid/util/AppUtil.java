package com.xiaoxin.guid.util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.net.Uri;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author: xiaoxin
 * date: 2018/10/22
 * describe:
 * 修改内容:
 */
public class AppUtil {

    /**
     * 检查包是否存在
     * @param context
     * @param packname
     * @return
     */
    public static boolean checkPackInfo(Context context, String packname) {
        PackageInfo packageInfo = null;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(packname, 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return packageInfo != null;
    }

    /**
     * 打开地图软件
     * @param context
     */
    public static void startMap(Context context) {
        if (AppUtil.checkPackInfo(context,"com.baidu.BaiduMap")) {
            AppUtil.startActivity(context,true);
        }else if (AppUtil.checkPackInfo(context,"com.autonavi.minimap")){
            AppUtil.startActivity(context,false);
        }else {
            Toast.makeText(context,"没有安装地图软件",Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 百度地图uri：http://lbsyun.baidu.com/index.php?title=uri/api/android
     * 高德地图uri:https://lbs.amap.com/api/uri-api/guide/search/around-search
     */
    public static void startActivity(Context context,boolean state){

        try {
            LocationUtil locationUtil = new LocationUtil(context);
            double[] doubles = locationUtil.startLocation(context);
            //URL: //uri.amap.com/nearby?service=movie&location=116.481590,39.989175&city=250000&src=mypage&coordinate=wgs84
            // POI搜索
            if (state) {
                Intent i1 = new Intent();//"region=jinan&" +
                i1.setData(Uri.parse("baidumap://map/place/search?" +
                        "query=药店&" +
                        "location="+doubles[0]+","+doubles[1] +
                        "&radius=1000&" +
                        "src=andr.baidu.openAPIdemo"));
                context.startActivity(i1);
            }else {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_DEFAULT);
//将功能S  cheme以URI的方式传入银行|加油站|电影院起终点是否偏移(0:lat 和 lon 是已经加密后的,不需要国测加密;1:需要国测加密)
                Uri uri = Uri.parse("androidamap://arroundpoi?sourceApplication=Jhang&keywords=药店&" +
                        "lat="+doubles[0]+"&lon="+doubles[1]+"&dev=0");
                intent.setData(uri);
                context.startActivity(intent);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取asset下json文件
     * @param fileName
     * @param context
     * @return
     */
    public static String getJson(String fileName,Context context) {
        //将json数据变成字符串
        StringBuilder stringBuilder = new StringBuilder();
        try {
            //获取assets资源管理器
            AssetManager assetManager = context.getAssets();
            //通过管理器打开文件并读取
            BufferedReader bf = new BufferedReader(new InputStreamReader(
                    assetManager.open(fileName)));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
}
