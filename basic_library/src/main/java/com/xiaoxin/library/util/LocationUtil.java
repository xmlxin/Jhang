package com.xiaoxin.library.util;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.util.Log;
import android.widget.Toast;

/**
 * @author: xiaoxin
 * date: 2018/10/22
 * describe:
 * 修改内容:
 */
public class LocationUtil {

    LocationManager locationManager; //系统定位
    private String mLongitude = ""; // 经度
    private String mLatitude = ""; // 维度

    public LocationUtil(Context context) {
        //获取定位服务
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        //检查定位是否被打开
        boolean gpsIsOpen = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (gpsIsOpen) {
            //定位
//            startLocation(context);
        } else {
            Toast.makeText(context, "请打开地理位置", Toast.LENGTH_SHORT).show();
        }
    }

    public void initLocalLibView(Context context) {
        //获取定位服务
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        //检查定位是否被打开
        boolean gpsIsOpen = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (gpsIsOpen) {
            //定位
            startLocation(context);
        } else {
            Toast.makeText(context, "请打开地理位置", Toast.LENGTH_SHORT).show();
        }
    }

    public double[] startLocation(Context context) {

        // 为获取地理位置信息时设置查询条件 是按GPS定位还是network定位
        String bestProvider = getProvider();

        Location location = locationManager.getLastKnownLocation(bestProvider);
        mLongitude = String.valueOf(location.getLongitude());
        mLatitude = String.valueOf(location.getLatitude());
        double[] doubles = new double[2];
        doubles[0] = location.getLatitude();
        doubles[1] = location.getLongitude();
        Log.e("tude","Location" + "纬度：" + mLatitude + "\n" +  "经度:  " + mLongitude);

        return doubles;
    }

    /**
     * 定位查询条件
     * 返回查询条件 ，获取目前设备状态下，最适合的定位方式
     */
    private String getProvider() {
        // 构建位置查询条件
        Criteria criteria = new Criteria();
        // 设置定位精确度 Criteria.ACCURACY_COARSE比较粗略，Criteria.ACCURACY_FINE则比较精细
        //Criteria.ACCURACY_FINE,当使用该值时，在建筑物当中，可能定位不了,建议在对定位要求并不是很高的时候用Criteria.ACCURACY_COARSE，避免定位失败
        // 查询精度：高  ACCURACY_FINE
        criteria.setAccuracy(Criteria.ACCURACY_COARSE);
        // 设置是否要求速度
        criteria.setSpeedRequired(false);
        // 是否查询海拨：否
        criteria.setAltitudeRequired(false);
        // 是否查询方位角 : 否
        criteria.setBearingRequired(false);
        // 是否允许付费：是
        criteria.setCostAllowed(false);
        // 电量要求：低
        criteria.setPowerRequirement(Criteria.POWER_LOW);
        // 返回最合适的符合条件的provider，第2个参数为true说明 , 如果只有一个provider是有效的,则返回当前provider
        return locationManager.getBestProvider(criteria, true);
    }


}
