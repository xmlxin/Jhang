package com.xiaoxin.feng.jhang.util;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;

/**
 * @author xiaoxin
 * @date 2017/6/13
 * @describe ：获取电池信息
 * 修改内容
 */

public class BatteryUtil {

    Intent batteryInfoIntent;

//    int status1 = batteryInfoIntent.getIntExtra( "status" , 0 );  //电池状态 2为充电状态，其他为未充电状态
//    int health = batteryInfoIntent.getIntExtra( "health" , 1 ); //电池健康状态：只有数字2表示good;数字1：不清楚电池状况；数字2：电池良好 数字3：电池过热 数字4：没电或电池损坏 数字5：过压 数字6：未知错误 数字7：电池过冷
    // 电池健康情况，返回也是一个数字
    // BatteryManager.BATTERY_HEALTH_GOOD 良好
    // BatteryManager.BATTERY_HEALTH_OVERHEAT 过热
    // BatteryManager.BATTERY_HEALTH_DEAD 没电
    // BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE 过电压
    // BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE 未知错误

    // 电池状态，返回是一个数字
    // BatteryManager.BATTERY_STATUS_CHARGING 表示是充电状态
    // BatteryManager.BATTERY_STATUS_DISCHARGING 放电中
    // BatteryManager.BATTERY_STATUS_NOT_CHARGING 未充电
    // BatteryManager.BATTERY_STATUS_FULL 电池满
//    boolean present = batteryInfoIntent.getBooleanExtra( "present" , false );//电池是否安装在机身
//
//    int plugged = batteryInfoIntent.getIntExtra( "plugged" , 0 );
//    int voltage = batteryInfoIntent.getIntExtra( "voltage" , 0 ); //电压
//    int temperature = batteryInfoIntent.getIntExtra( "temperature" , 0 ); // 温度的单位是10℃
//    String technology = batteryInfoIntent.getStringExtra( "technology" );//电池种类(材料)
//    //判断是否正在充电
//    int status = batteryInfoIntent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
//    boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING ||
//            status == BatteryManager.BATTERY_STATUS_FULL;
//    // How are we charging?
//    //usbCharge；usb充电；acCharge：正常充电
//    int chargePlug = batteryInfoIntent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
//    boolean usbCharge = chargePlug == BATTERY_PLUGGED_USB;
//    boolean acCharge = chargePlug == BATTERY_PLUGGED_AC;
//
//    long time=System.currentTimeMillis();//当前时间
//    long time1=System.nanoTime();//long now = android.os.SystemClock.uptimeMillis();
//    SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//    Date d1=new Date(time);
//    Date d2=new Date(time1);
//    String t1=format.format(d1);
//    String t2=format.format(d2);

    public BatteryUtil(Context context) {
        batteryInfoIntent = context.getApplicationContext()
                .registerReceiver( null , new IntentFilter( Intent.ACTION_BATTERY_CHANGED ) ) ;
    }

    /**
     *  电量百分比
     * @return
     */
    public int getbatteryLevel() {
        return batteryInfoIntent.getIntExtra( "level" , 0 );
    }

    /**
     * 温度
     * @return
     */
    public int getbatteryTemperature() {
        return batteryInfoIntent.getIntExtra( "temperature" , 0 );
    }

    /**
     * 电压
     * @return
     */
    public float getbatteryVoltage() {
        return (float) ((batteryInfoIntent.getIntExtra( "voltage" , 0 ))/1000.0);
    }

    /**
     * 当前电池状态
     * @return
     */
    public String getbatteryStatus() {

        // 电池状态，返回是一个数字
        // BatteryManager.BATTERY_STATUS_CHARGING 表示是充电状态
        // BatteryManager.BATTERY_STATUS_DISCHARGING 放电中
        // BatteryManager.BATTERY_STATUS_NOT_CHARGING 未充电
        //BatteryManager.BATTERY_STATUS_FULL 电池满
        String statusString = "";
        switch (batteryInfoIntent.getIntExtra("status", 0)) {
            case BatteryManager.BATTERY_STATUS_UNKNOWN:
                statusString = "电池未知状态";
                break;
            case BatteryManager.BATTERY_STATUS_CHARGING:
                statusString = "充电中";
                break;
            case BatteryManager.BATTERY_STATUS_DISCHARGING:
                statusString = "耗电中";
                break;
            case BatteryManager.BATTERY_STATUS_NOT_CHARGING:
                //statusString = "not charging";
                statusString = "未充电";
                break;
            case BatteryManager.BATTERY_STATUS_FULL:
                statusString = "电量已满";
                break;
            default:
                break;
        }
        return statusString;
    }

    /**
     * 电池健康状态
     * @return
     */
    public String getbatteryHealth() {
        int health = batteryInfoIntent.getIntExtra( "health" , 1 );
        String healthString = "";
        switch (health) {
            case BatteryManager.BATTERY_HEALTH_UNKNOWN:
                healthString = "未知状态";
                break;
            case BatteryManager.BATTERY_HEALTH_GOOD:
                healthString = "电池健康";
                break;
            case BatteryManager.BATTERY_HEALTH_OVERHEAT:
                healthString = "电池过热";
                break;
            case BatteryManager.BATTERY_HEALTH_DEAD:
                healthString = "电池损坏";
                break;
            case BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE:
                healthString = "电池电压过大";
                break;
            case BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE:
                healthString = "未明示故障";
                break;
        }
        return healthString;
    }

    /**
     * 电池充电方式
     * @return
     */
    public String getbatteryAcOrUsb() {
        int plugged = batteryInfoIntent.getIntExtra("plugged", 0);
        String acString = "";
        switch (plugged) {
            case BatteryManager.BATTERY_PLUGGED_AC:
                acString = "AC充电器";
                break;
            case BatteryManager.BATTERY_PLUGGED_USB:
                acString = "USB";
                break;
            default:
                acString = "未充电";
                break;
        }
        return acString;
    }

}
