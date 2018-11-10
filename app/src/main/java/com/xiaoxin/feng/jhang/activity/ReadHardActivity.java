package com.xiaoxin.feng.jhang.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.TrafficStats;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.os.SystemClock;
import android.os.storage.StorageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.CellInfo;
import android.telephony.CellInfoCdma;
import android.telephony.CellInfoGsm;
import android.telephony.CellInfoLte;
import android.telephony.CellInfoWcdma;
import android.telephony.CellSignalStrengthCdma;
import android.telephony.CellSignalStrengthGsm;
import android.telephony.CellSignalStrengthLte;
import android.telephony.CellSignalStrengthWcdma;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import android.text.format.DateUtils;
import android.text.format.Formatter;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.TextView;

import com.xiaoxin.feng.jhang.R;
import com.xiaoxin.feng.jhang.util.BatteryUtil;
import com.xiaoxin.feng.jhang.util.MacUtils;
import com.xiaoxin.feng.jhang.util.WifiAdmin;
import com.zhanshow.mylibrary.phonestate.MyPhoneStateListener;
import com.zhanshow.mylibrary.phonestate.PhoneStateUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import static android.os.BatteryManager.BATTERY_PLUGGED_AC;
import static android.os.BatteryManager.BATTERY_PLUGGED_USB;

public class ReadHardActivity extends AppCompatActivity {

    private static final String TAG = "ReadHardActivity";

    private TextView mTextView;
    private TextView mTextView2;
    Intent batteryInfoIntent;
    private long mAvailMem;
    private BatteryUtil mBatteryUtil;
    private int phoneGsmSignal;
    private WifiAdmin mWifiAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_hard);

        mTextView = (TextView) findViewById(R.id.tv);
        mTextView2 = (TextView) findViewById(R.id.tv2);
        batteryInfoIntent = getApplicationContext()
                .registerReceiver(null,
                        new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        mBatteryUtil = new BatteryUtil(this);
        getDisplay();
        getScreenSizeOfDevice2();
        getDianChi();
        //移动网路下载流量
        long mobileRx = TrafficStats.getMobileRxBytes();
        //移动网络上传流量
        long mobileTx = TrafficStats.getMobileTxBytes();
        //计算移动网络总流量
        long tatolMobile = mobileRx + mobileTx;
        //总的下载流量
        long totalRx = TrafficStats.getTotalRxBytes();
        //总的上传流量
        long totalTx = TrafficStats.getTotalTxBytes();
        long total = totalRx + totalTx;
        Log.e("sss", "移动流量:" + Formatter.formatFileSize(this, tatolMobile));
        Log.e("ssss", "总流量:" + Formatter.formatFileSize(this, total));
        Log.e("ssss", "ip地址:" + getPhoneIp());
        Log.e("ssss", "网络类型:" + getCurrentNetType(this));
        Log.e("ssss", "mac:" + MacUtils.getMac());
        showROMInfo();
        showRAMInfo();
        showSDInfo();

        PhoneStateUtils.registerPhoneStateListener(this, new MyPhoneStateListener.MyPhoneStateListenerListener() {
            @Override
            public void onSignalStrengthsChanged(SignalStrength signalStrength) {
                Log.e("sss", "onSignalStrengthsChanged: " + signalStrength
                        + "----  signalStrength.getGsmSignalStrength()" + signalStrength.getGsmSignalStrength());
                phoneGsmSignal = signalStrength.getGsmSignalStrength();
                mTextView2.setText("信号强度: " + signalStrength.getGsmSignalStrength() + "%" + "\n");
            }
        });
        getPhoneInfo();
        Log.e("sss", "phoneGsmSignal:" + phoneGsmSignal);
    }

    /**
     * 获得内置SD卡总大小
     *
     * @return
     */
    public static String getInSDTotalSize(Context context) {
        try {
            File path = Environment.getExternalStorageDirectory();
            StatFs stat = new StatFs(path.getPath());
            long blockSize = stat.getBlockSizeLong();
            long totalBlocks = stat.getBlockCountLong();
            return Formatter.formatFileSize(context, blockSize * totalBlocks);
        } catch (Exception e) {
            return null;
        }

    }

    /** 获取cpu使用率
     * @return
     */
    private int getProcessCpuRate() {

//        StringBuilder tv = new StringBuilder();
//        int rate = 0;
//
//        try {
//            String Result;
//            Process p;
//            p = Runtime.getRuntime().exec("top -n 1");
//
//            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
//            while ((Result = br.readLine()) != null) {
//                if (Result.trim().length() < 1) {
//                    continue;
//                } else {
//                    String[] CPUusr = Result.split("%");
//                    tv.append("USER:" + CPUusr[0] + "\n");
//                    String[] CPUusage = CPUusr[0].split("User");
//                    String[] SYSusage = CPUusr[1].split("System");
//                    tv.append("CPU:" + CPUusage[1].trim() + " length:" + CPUusage[1].trim().length() + "\n");
//                    tv.append("SYS:" + SYSusage[1].trim() + " length:" + SYSusage[1].trim().length() + "\n");
//
//                    rate = Integer.parseInt(CPUusage[1].trim()) + Integer.parseInt(SYSusage[1].trim());
//                    break;
//                }
//            }
//
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        System.out.println(rate + "");
        return 1;
    }


    public void getDianChi() {
        Intent batteryInfoIntent = getApplicationContext()
                .registerReceiver(null,
                        new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        int status1 = batteryInfoIntent.getIntExtra("status", 0);  //电池状态 2为充电状态，其他为未充电状态
        int health = batteryInfoIntent.getIntExtra("health", 1); //电池健康状态：只有数字2表示good;数字1：不清楚电池状况；数字2：电池良好 数字3：电池过热 数字4：没电或电池损坏 数字5：过压 数字6：未知错误 数字7：电池过冷
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
        boolean present = batteryInfoIntent.getBooleanExtra("present", false);//电池是否安装在机身
        int level = batteryInfoIntent.getIntExtra("level", 0); //电量百分比
        int tootllevel = batteryInfoIntent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        Log.e("sss", tootllevel + "");
        int scale11 = batteryInfoIntent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
        Log.e("sss", scale11 + "scale11");
        int scale = batteryInfoIntent.getIntExtra("scale", 0);
        Log.e("sss", scale + "scale11");

        int l1 = batteryInfoIntent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int l2 = batteryInfoIntent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);

        float batteryPct = l1 / (float) l2;
        Log.e("sss", "heih:" + batteryPct);
        Log.e("sss", "heih:" + l1);
        Log.e("sss", "heih:" + l2);

        int plugged = batteryInfoIntent.getIntExtra("plugged", 0);
        int voltage = batteryInfoIntent.getIntExtra("voltage", 0); //电压
        int temperature = batteryInfoIntent.getIntExtra("temperature", 0); // 温度的单位是10℃
        String technology = batteryInfoIntent.getStringExtra("technology");//电池种类(材料)
        Log.e("sss", "status1:" + status1 + "health:" + health + "level:" + level + "scale:" + scale + "plugged:" + plugged
                + "voltage:" + voltage + "temper:" + temperature + "technology:" + technology);
        //判断是否正在充电
        int status = batteryInfoIntent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
        boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING ||
                status == BatteryManager.BATTERY_STATUS_FULL;
        Log.e("sss", "isCharging:" + isCharging);

        // How are we charging?
        //usbCharge；usb充电；acCharge：正常充电
        int chargePlug = batteryInfoIntent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
        boolean usbCharge = chargePlug == BATTERY_PLUGGED_USB;
        boolean acCharge = chargePlug == BATTERY_PLUGGED_AC;
        Log.e("sss", "usbCharge:" + usbCharge);
        Log.e("sss", "acCharge:" + acCharge);
        Log.e("sss", "系统启动时间:" + SystemClock.uptimeMillis());
        Log.e("sss", "系统启动时间:" + DateUtils.formatDateTime(this, SystemClock.elapsedRealtime(), DateUtils.FORMAT_SHOW_TIME));
        Log.e("sss", "系统启动时间:" + DateUtils.formatDateTime(this, System.nanoTime(), DateUtils.FORMAT_SHOW_YEAR));
        DateUtils.formatDateTime(this, SystemClock.uptimeMillis(), DateUtils.FORMAT_SHOW_YEAR);
        DateUtils.formatDateTime(this, SystemClock.uptimeMillis(), DateUtils.FORMAT_SHOW_TIME);
        SystemClock.elapsedRealtime();
        System.nanoTime();
        long time = System.currentTimeMillis();//当前时间
        long time1 = System.nanoTime();//long now = android.os.SystemClock.uptimeMillis();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d1 = new Date(time);
        Date d2 = new Date(time1);
        String t1 = format.format(d1);
        String t2 = format.format(d2);
        Log.e("sss", "t1:" + t1 + "  t2:  " + t2);

        mWifiAdmin = new WifiAdmin(this);
    }

    /**
     * 获取手机信息
     */
    public void getPhoneInfo() {
        //判断是否正在充电
        int status = batteryInfoIntent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
        boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING ||
                status == BatteryManager.BATTERY_STATUS_FULL;
        int chargePlug = batteryInfoIntent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
        boolean usbCharge = chargePlug == BATTERY_PLUGGED_USB;
        boolean acCharge = chargePlug == BATTERY_PLUGGED_AC;
        TelephonyManager tm = (TelephonyManager) this.getSystemService(TELEPHONY_SERVICE);
        String mtyb = android.os.Build.BRAND;// 手机品牌
        String mtype = android.os.Build.MODEL; // 手机型号
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        String imei = tm.getDeviceId();
        String imsi = tm.getSubscriberId();
        String numer = tm.getLine1Number(); // 手机号码
        String serviceName = tm.getSimOperatorName(); // 运营商
        mTextView.setText("品牌: " + mtyb + "\n" + "型号: " + mtype + "\n" + "版本: Android "
                + android.os.Build.VERSION.RELEASE + "\n"+ "SDK版本:"
                + Build.VERSION.SDK_INT + "\n" + "IMEI: " + imei
                + "\n" + "IMSI: " + imsi + "\n" + "手机号码: " + numer + "\n"
                + "运营商: " + serviceName + "\n");
//        mTextView.append("信号强度: " + phoneGsmSignal+"%" + "\n");
        mTextView.append("是否root: " + "false" + "\n");
        mTextView.append("主板: " + ""+ Build.BOARD + "\n");
        mTextView.append("主板版本: " + Build.BOOTLOADER + "\n");
        mTextView.append("内核版本: " + System.getProperty("os.version") + "\n");
        mTextView.append("RAM: " +showRAMInfo() + "\n");
        mTextView.append("ROM: " + showROMInfo()+ "\n");
        mTextView.append("SD卡: " +   showSDInfo()+ "\n");
        mTextView.append("7.0SD卡: " +   showSDInfo()+ "\n");
        mTextView.append("CPU名字: " + getCpuName() + "\n");
        mTextView.append("CPU架构: " + Build.CPU_ABI + "\n");
        mTextView.append("CPU最大频率: " + getMaxCpuFreq() + "\n");
        mTextView.append("CPU最小频率: " + getMinCpuFreq() + "\n");
        mTextView.append("CPU当前频率: " + getCurCpuFreq() + "\n");
        mTextView.append("屏幕分辨率: " + getDisplayInfomation() + "\n");
        mTextView.append("屏幕尺寸: " + getScreenSizeOfDevice2() + "\n");
        mTextView.append("电池电量: " + mBatteryUtil.getbatteryLevel()+"%" + "\n");
        mTextView.append("电池温度: " + mBatteryUtil.getbatteryTemperature()/10.0+"℃" + "\n");
        mTextView.append("电池电压: " + mBatteryUtil.getbatteryVoltage()+"V" + "\n");
        mTextView.append("电池健康: " + mBatteryUtil.getbatteryHealth() + "\n");
        mTextView.append("电池状态: " + mBatteryUtil.getbatteryStatus() + "\n");
        mTextView.append("充电方式: " + mBatteryUtil.getbatteryAcOrUsb() + "\n");
        mTextView.append("ip地址: " + getPhoneIp() + "\n");
        mTextView.append("网络类型: " + getCurrentNetType(this)+ "\n");
        mTextView.append("MAC地址: " + MacUtils.getMac()+ "\n");
        mTextView.append("7.0MAC地址: " + getMacAddr()+ "\n");
        mTextView.append("路由器MAC地址: " + mWifiAdmin.getBSSID()+ "\n");
        mTextView.append("cup使用率: " + getProcessCpuRate()+ "\n");
        mTextView.append("手机制造商: " + Build.MANUFACTURER+ "\n");
    }

    /**
     *
     * @param mContext
     * @param is_removale  true sd
     * @return
     */
    private static String getStoragePath(Context mContext, boolean is_removale) {

        StorageManager mStorageManager = (StorageManager) mContext.getSystemService(Context.STORAGE_SERVICE);
        Class<?> storageVolumeClazz = null;
        try {
            storageVolumeClazz = Class.forName("android.os.storage.StorageVolume");
            Method getVolumeList = mStorageManager.getClass().getMethod("getVolumeList");
            Method getPath = storageVolumeClazz.getMethod("getPath");
            Method isRemovable = storageVolumeClazz.getMethod("isRemovable");
            Object result = getVolumeList.invoke(mStorageManager);
            final int length = Array.getLength(result);
            for (int i = 0; i < length; i++) {
                Object storageVolumeElement = Array.get(result, i);
                String path = (String) getPath.invoke(storageVolumeElement);
                boolean removable = (Boolean) isRemovable.invoke(storageVolumeElement);
                if (is_removale == removable) {
                    return path;
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 适配7.0获取mac地址
     * @return
     */
    public static String getMacAddr() {
        try {
            List<NetworkInterface> all = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface nif : all) {
                if (!nif.getName().equalsIgnoreCase("wlan0")) continue;

                byte[] macBytes = nif.getHardwareAddress();
                if (macBytes == null) {
                    return "";
                }

                StringBuilder res1 = new StringBuilder();
                for (byte b : macBytes) {
                    res1.append(String.format("%02X:",b));
                }

                if (res1.length() > 0) {
                    res1.deleteCharAt(res1.length() - 1);
                }
                return res1.toString();
            }
        } catch (Exception ex) {
        }
        return "02:00:00:00:00:00";
    }

    /**
     * 获得SD卡总大小
     *
     * @return
     */
    private String getSDTotalSize() {
        File path = Environment.getExternalStorageDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long totalBlocks = stat.getBlockCount();
        return Formatter.formatFileSize(ReadHardActivity.this, blockSize * totalBlocks);
    }

    /**
     * 获得sd卡剩余容量，即可用大小
     *
     * @return
     */
    private String getSDAvailableSize() {
        File path = Environment.getExternalStorageDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long availableBlocks = stat.getAvailableBlocks();
        return Formatter.formatFileSize(ReadHardActivity.this, blockSize * availableBlocks);
    }

    /**
     * 获取手机信号强度，需添加权限 android.permission.ACCESS_COARSE_LOCATION <br>
     * API要求不低于17 <br>
     *
     * @return 当前手机主卡信号强度,单位 dBm（-1是默认值，表示获取失败）
     */
    @SuppressLint("MissingPermission")
    public int getMobileDbm(Context context)
    {
        int dbm = -1;
        TelephonyManager tm = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
        List<CellInfo> cellInfoList;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
        {
            cellInfoList = tm.getAllCellInfo();
            if (null != cellInfoList)
            {
                for (CellInfo cellInfo : cellInfoList)
                {
                    if (cellInfo instanceof CellInfoGsm)
                    {
                        CellSignalStrengthGsm cellSignalStrengthGsm = ((CellInfoGsm)cellInfo).getCellSignalStrength();
                        dbm = cellSignalStrengthGsm.getDbm();
                    }
                    else if (cellInfo instanceof CellInfoCdma)
                    {
                        CellSignalStrengthCdma cellSignalStrengthCdma =
                                ((CellInfoCdma)cellInfo).getCellSignalStrength();
                        dbm = cellSignalStrengthCdma.getDbm();
                    }
                    else if (cellInfo instanceof CellInfoWcdma)
                    {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2)
                        {
                            CellSignalStrengthWcdma cellSignalStrengthWcdma =
                                    ((CellInfoWcdma)cellInfo).getCellSignalStrength();
                            dbm = cellSignalStrengthWcdma.getDbm();
                        }
                    }
                    else if (cellInfo instanceof CellInfoLte)
                    {
                        CellSignalStrengthLte cellSignalStrengthLte = ((CellInfoLte)cellInfo).getCellSignalStrength();
                        dbm = cellSignalStrengthLte.getDbm();
                    }
                }
            }
        }
        return dbm;
    }

    /**
     * 获取已连接的Wifi路由器的Mac地址
     */
    public static String getConnectedWifiMacAddress(Context context) {
        String connectedWifiMacAddress = null;
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        List<ScanResult> wifiList;

        if (wifiManager != null) {
            wifiList = wifiManager.getScanResults();
            WifiInfo info = wifiManager.getConnectionInfo();
            if (wifiList != null && info != null) {
                for (int i = 0; i < wifiList.size(); i++) {
                    ScanResult result = wifiList.get(i);
                    if (info.getBSSID().equals(result.BSSID)) {
                        connectedWifiMacAddress = result.BSSID;
                    }
                }
            }
        }
        return connectedWifiMacAddress;
    }

    /**
     * 获取屏幕分辨率
     * @return
     */
    private String getDisplayInfomation() {
        Point point = new Point();
        //getWindowManager().getDefaultDisplay().getSize (point);
        getWindowManager().getDefaultDisplay().getRealSize (point);
        Log.d("ssss","the screen size is "+point.toString());
        return point.toString();
    }

    /**
     * 获取屏幕尺寸(在个别机型不准确)
     */
    public void getDisplay() {
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width=dm.widthPixels;
        int height=dm.heightPixels;
        double wi=(double)width/(double)dm.xdpi;
        double hi=(double)height/(double)dm.ydpi;
        double x = Math.pow(wi,2);
        double y = Math.pow(hi,2);
        double screenInches = Math.sqrt(x+y);
        Log.e("sss","主板:"+ Build.BOARD);//可能获取不到
        Log.e("sss","主板版本:"+ Build.BOOTLOADER);//可能获取不到
        Log.e("sss","android系统定制商:"+ Build.BRAND);
        Log.e("sss","编译时间:"+ Build.TIME);
        //Log.e("sss","cpu指令集:"+ Build.SUPPORTED_ABIS );
        Log.e("sss","设备参数:"+ Build.DEVICE);//r7s
        Log.e("sss","内核版本"+ System.getProperty("os.version"));
        Log.e("sss","os.name"+ System.getProperty("os.name")); //linux
        Log.e("sss","os.arch"+ System.getProperty("os.arch"));//aarch64
        Log.e("sss","os.arch"+ System.getProperty("os.arch"));//aarch64
    }

    /**
     * 获取屏幕尺寸(相对准确)
     */
    private double getScreenSizeOfDevice2() {
        Point point = new Point();
        getWindowManager().getDefaultDisplay().getRealSize(point);
        DisplayMetrics dm = getResources().getDisplayMetrics();
        double x = Math.pow(point.x/ dm.xdpi, 2);
        double y = Math.pow(point.y / dm.ydpi, 2);
        double screenInches = Math.sqrt(x + y);
        Log.e("ssss", "Screen inches : " + screenInches);
        return screenInches;
    }

    /**
     * 获取手机内存大小
     *
     * @return
     */
    private long getTotalMemory() {
        String str1 = "/proc/meminfo";// 系统内存信息文件
        String str2;
        String[] arrayOfString;
        long initial_memory = 0;
        try {
            FileReader localFileReader = new FileReader(str1);
            BufferedReader localBufferedReader = new BufferedReader(localFileReader, 8192);
            str2 = localBufferedReader.readLine();// 读取meminfo第一行，系统总内存大小

            arrayOfString = str2.split("\\s+");
            for (String num : arrayOfString) {
                Log.i(str2, num + "\t");
            }

            initial_memory = Integer.valueOf(arrayOfString[1]).intValue() * 1024;// 获得系统总内存，单位是KB，乘以1024转换为Byte
            localBufferedReader.close();

        } catch (IOException e) {
        }
        //return Formatter.formatFileSize(getBaseContext(), initial_memory);// Byte转换为KB或者MB，内存大小规格化
        return initial_memory;
    }

    /**
     * 获取当前可用内存大小
     *
     * @return
     */
    private long getAvailMemory() {
        ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
        am.getMemoryInfo(mi);
        //return Formatter.formatFileSize(getBaseContext(), mi.availMem);
        return mi.availMem;
    }

    public static String getMaxCpuFreq() {
        String result = "";
        ProcessBuilder cmd;
        try {
            String[] args = {"/system/bin/cat",
                    "/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq"};
            cmd = new ProcessBuilder(args);
            Process process = cmd.start();
            InputStream in = process.getInputStream();
            byte[] re = new byte[24];
            while (in.read(re) != -1) {
                result = result + new String(re);
            }
            in.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            result = "N/A";
        }
        return result.trim() + "Hz";
    }

    // 获取CPU最小频率（单位KHZ）

    public static String getMinCpuFreq() {
        String result = "";
        ProcessBuilder cmd;
        try {
            String[] args = {"/system/bin/cat",
                    "/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_min_freq"};
            cmd = new ProcessBuilder(args);
            Process process = cmd.start();
            InputStream in = process.getInputStream();
            byte[] re = new byte[24];
            while (in.read(re) != -1) {
                result = result + new String(re);
            }
            in.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            result = "N/A";
        }
        return result.trim() + "Hz";
    }

    // 实时获取CPU当前频率（单位KHZ）

    public static String getCurCpuFreq() {
        String result = "N/A";
        try {
            FileReader fr = new FileReader(
                    "/sys/devices/system/cpu/cpu0/cpufreq/scaling_cur_freq");
            BufferedReader br = new BufferedReader(fr);
            String text = br.readLine();
            result = text.trim() + "Hz";
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String getCpuName() {
        try {
            FileReader fr = new FileReader("/proc/cpuinfo");
            BufferedReader br = new BufferedReader(fr);
            String text = br.readLine();
            String[] array = text.split(":\\s+", 2);
            for (int i = 0; i < array.length; i++) {
            }
            return array[1];
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取系统内部存储空间的总大小
     * @return
     */
    public static long getInternalStorageSize() {
        File file = Environment.getDataDirectory();
        return file.getTotalSpace();
    }

    /**
     * 获取系统内部存储空间的可用大小
     * @return
     */
    public static long getInternalStorageFreeSize() {
        File file = Environment.getDataDirectory();
        return file.getUsableSpace();
    }

    /**
     * 获取系统SD卡存储空间的可用大小
     * @return
     */
    public static long getSDStorageFreeSize() {
        File file = Environment.getExternalStorageDirectory();
        return file.getUsableSpace();
    }

    /**
     * 获取手机ip地址
     *
     * @return
     */
    public static String getPhoneIp() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
                        // if (!inetAddress.isLoopbackAddress() && inetAddress
                        // instanceof Inet6Address) {
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
        } catch (Exception e) {
            Log.e("sss","eeeeee"+e);
        }
        return "";
    }

    /**
     * 得到当前的手机网络类型
     *
     * @param context
     * @return
     */
    public static String getCurrentNetType(Context context) {
        String type = "";
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        if (info == null) {
            type = "null";
        } else if (info.getType() == ConnectivityManager.TYPE_WIFI) {
            type = "wifi";
        } else if (info.getType() == ConnectivityManager.TYPE_MOBILE) {
            int subType = info.getSubtype();
            if (subType == TelephonyManager.NETWORK_TYPE_CDMA || subType == TelephonyManager.NETWORK_TYPE_GPRS
                    || subType == TelephonyManager.NETWORK_TYPE_EDGE) {
                type = "2g";
            } else if (subType == TelephonyManager.NETWORK_TYPE_UMTS || subType == TelephonyManager.NETWORK_TYPE_HSDPA
                    || subType == TelephonyManager.NETWORK_TYPE_EVDO_A || subType == TelephonyManager.NETWORK_TYPE_EVDO_0
                    || subType == TelephonyManager.NETWORK_TYPE_EVDO_B) {
                type = "3g";
            } else if (subType == TelephonyManager.NETWORK_TYPE_LTE) {// LTE是3g到4g的过渡，是3.9G的全球标准
                type = "4g";
            }
        }
        return type;
    }

    public String getMacAddress() {
        String result = "";
        String Mac = "";
        //result = callCmd("busybox ifconfig", "HWaddr");

        if (result == null) {
            return "网络出错，请检查网络";
        }
        if (result.length() > 0 && result.contains("HWaddr")) {
            Mac = result.substring(result.indexOf("HWaddr") + 6, result.length() - 1);
            if (Mac.length() > 1) {
                result = Mac.toLowerCase();
            }
        }
        return result.trim();
    }

    /*显示RAM的可用和总容量，RAM相当于电脑的内存条*/
    private String showRAMInfo(){
        ActivityManager am=(ActivityManager)getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo mi=new ActivityManager.MemoryInfo();
        am.getMemoryInfo(mi);
        String[] available=fileSize(mi.availMem);
        String[] total=fileSize(mi.totalMem);
        Log.e("sss","RAM "+available[0]+available[1]+"/"+total[0]+total[1]);
        return available[0]+available[1]+"/"+total[0]+total[1];
    }

    /*显示ROM的可用和总容量，ROM相当于电脑的C盘*/
    private String showROMInfo(){
        File file=Environment.getDataDirectory();
        StatFs statFs=new StatFs(file.getPath());
        long blockSize=statFs.getBlockSize();
        long totalBlocks=statFs.getBlockCount();
        long availableBlocks=statFs.getAvailableBlocks();

        String[] total=fileSize(totalBlocks*blockSize);
        String[] available=fileSize(availableBlocks*blockSize);
        Log.e("sss","ROM "+available[0]+available[1]+"/"+total[0]+total[1]);
        return available[0]+available[1]+"/"+total[0]+total[1];
    }

    /*返回为字符串数组[0]为大小[1]为单位KB或者MB*/
    private String[] fileSize(long size){
        String str="";
        if(size>=1000){
            str="KB";
            size/=1000;
            if(size>=1000){
                str="MB";
                size/=1000;
            }
        }
        /*将每3个数字用,分隔如:1,000*/
        DecimalFormat formatter=new DecimalFormat();
        formatter.setGroupingSize(3);
        String result[]=new String[2];
        result[0]=formatter.format(size);
        result[1]=str;
        return result;
    }

    /*显示SD卡的可用和总容量，SD卡就相当于电脑C盘以外的硬盘*/
    private String showSDInfo(){
        if(Environment.getExternalStorageState().equals
                (Environment.MEDIA_MOUNTED)){
            File file=Environment.getExternalStorageDirectory();
            StatFs statFs=new StatFs(file.getPath());
            long blockSize=statFs.getBlockSize();
            long totalBlocks=statFs.getBlockCount();
            long availableBlocks=statFs.getAvailableBlocks();

            String[] total=fileSize(totalBlocks*blockSize);
            String[] available=fileSize(availableBlocks*blockSize);

            Log.e("sss","SD "+available[0]+available[1]+"/"+total[0]+total[1]);
            return available[0]+available[1]+"/"+total[0]+total[1];
        }else {
            Log.e("sss","SD CARD 已删除");
            return "SD CARD 已删除";
        }
    }


    public void sd() {
        File path = new File(getStoragePath(this,true));
        StatFs stat = new StatFs(path.getPath());
        long blockSize;
        long totalBlocks;
        long availableBlocks;
        // 由于API18（Android4.3）以后getBlockSize过时并且改为了getBlockSizeLong
        // 因此这里需要根据版本号来使用那一套API
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            blockSize = stat.getBlockSizeLong();
            totalBlocks = stat.getBlockCountLong();
            availableBlocks = stat.getAvailableBlocksLong();
        } else {
            blockSize = stat.getBlockSize();
            totalBlocks = stat.getBlockCount();
            availableBlocks = stat.getAvailableBlocks();
        }
        // 利用formatSize函数把字节转换为用户等看懂的大小数值单位
        String totalText = formatSize(blockSize * totalBlocks);
        String availableText = formatSize(blockSize * availableBlocks);
    }


    //封装Formatter.formatFileSize方法，具体可以参考安卓的API
    private String formatSize(long size)
    {
        return Formatter.formatFileSize(this, size);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PhoneStateUtils.unRegisterPhoneStateListener(this);
    }

}
