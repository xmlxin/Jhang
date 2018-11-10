package com.xiaoxin.guid.search.truth;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xiaoxin.guid.Api;
import com.xiaoxin.guid.GsonUtils;
import com.xiaoxin.guid.HttpGet;
import com.xiaoxin.guid.R;
import com.xiaoxin.guid.base.BaseActivity;
import com.xiaoxin.guid.bean.search.TruthBean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author: xiaoxin
 * date: 2018/11/6
 * describe:每日真相
 * 修改内容:
 */
public class TruthActivity extends BaseActivity {

    private static final String TAG = "TruthActivity";

    private TextView btnGet, healthContent, healthTitle,time;
    private ImageView ivHealthContent;
    private ConstraintLayout shareBottom;

    private void loadTruth() {

        new Thread(() -> {
            Map<String, String> map = new HashMap<>();
//            map.put("time",time);//不填默认当前时间
            map.put("dxa_entry", "event_homepage_sepcial_area_click_每日真相");
            map.put("mc", "000000006fba5445ffffffff946c9cbf");
            map.put("cn", "General");
            map.put("hardName", "SM-G955F");
            map.put("ac", "d5424fa6-adff-4b0a-8917-4264daf4a348");
            map.put("bv", "2017");
            map.put("vc", "7.6.7");
            map.put("vs", "4.4.2");
            runOnUI(HttpGet.get(Api.TRUTH_DETAIL, map));
        }).start();
    }

    /**
     * 更新ui
     *
     * @param result
     */
    private void runOnUI(String result) {
        runOnUiThread(() -> {
            TruthBean truthBean = GsonUtils.GsonToBean(result, TruthBean.class);
            if (truthBean != null) {
                if (truthBean.getData() != null && truthBean.getData().getItems() != null) {
                    TruthBean.DataBean.ItemsBean itemsBean = truthBean.getData().getItems().get(0);
                    healthTitle.setText(itemsBean.getTitle());
                    healthContent.setText(itemsBean.getContent());
                }
            }
        });
    }

    @Override
    public void initData(@Nullable Bundle bundle) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_truth;
    }

    @Override
    public void initView(Bundle savedInstanceState, View contentView) {
        TextView title = findViewById(R.id.tv_title_name);
        title.setText("每日健康真相");
        title.setOnClickListener(v -> {
            finish();
        });

        ivHealthContent = findViewById(R.id.iv_daily_health_content);
        btnGet = findViewById(R.id.btn_daily_health_get);
        healthContent = findViewById(R.id.tv_daily_health_content);
        healthTitle = findViewById(R.id.tv_daily_health_title);
        shareBottom = findViewById(R.id.share_bottom_layout);
        time = findViewById(R.id.tv_daily_health_time);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(new Date());
        time.setText(date+"");
    }

    @Override
    public void doBusiness() {
        loadTruth();

        btnGet.setOnClickListener(v -> {
            ivHealthContent.setVisibility(View.GONE);
            healthContent.setVisibility(View.VISIBLE);
            btnGet.setVisibility(View.GONE);
            shareBottom.setVisibility(View.VISIBLE);
        });

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                List<String> dateStr = new ArrayList<>();
//                dateStr.addAll(getDayByMonth(2018, 5));
//                dateStr.addAll(getDayByMonth(2018, 6));
//                dateStr.addAll(getDayByMonth(2018, 7));
//                dateStr.addAll(getDayByMonth(2018, 8));
//                dateStr.addAll(getDayByMonth(2018, 9));
//                dateStr.addAll(getDayByMonth(2018, 10));
//                dateStr.addAll(getDayByMonth(2018, 11));
//                dateStr.addAll(getDayByMonth(2018, 12));
//
//                for (int i = 0; i < dateStr.size(); i++) {
//                    String timeStamp = date2TimeStamp(dateStr.get(i), "yyyy-MM-dd");
//                    Map<String, String> map = new HashMap<>();
//                    map.put("time",timeStamp);//不填默认当前时间
//                    map.put("dxa_entry", "event_homepage_sepcial_area_click_每日真相");
//                    map.put("mc", "000000006fba5445ffffffff946c9cbf");
//                    map.put("cn", "General");
//                    map.put("hardName", "SM-G955F");
//                    map.put("ac", "d5424fa6-adff-4b0a-8917-4264daf4a348");
//                    map.put("bv", "2017");
//                    map.put("vc", "7.6.7");
//                    map.put("vs", "4.4.2");
//                    String result = HttpGet.get(Api.TRUTH_DETAIL, map);
//                    try {
//                        Thread.sleep(20);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    Log.e(TAG, "run: result"+result );
//                    TruthBean truthBean = GsonUtils.GsonToBean(result, TruthBean.class);
//                    if (truthBean != null && truthBean.getData() != null && truthBean.getData().getItems()!= null) {
//
//                        SearchHospitalDao.insertTruth(truthBean.getData().getItems().get(0));
//                    }
//                }
//
//                Log.e(TAG, "loadBz: 数据加载完毕");
//            }
//
//        }).start();


    }


    public static String date2TimeStamp(String date_str, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return String.valueOf(sdf.parse(date_str).getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static List<String> getDayByMonth(int yearParam, int monthParam) {
        List list = new ArrayList();
        Calendar aCalendar = Calendar.getInstance(Locale.CHINA);
        aCalendar.set(yearParam, monthParam, 1);
        int year = aCalendar.get(Calendar.YEAR);//年份
        int month = aCalendar.get(Calendar.MONTH) + 1;//月份
        int day = aCalendar.getActualMaximum(Calendar.DATE);
        for (int i = 1; i <= day; i++) {
            String aDate = null;
            if (month < 10 && i < 10) {
                aDate = String.valueOf(year) + "-0" + month + "-0" + i;
            }
            if (month < 10 && i >= 10) {
                aDate = String.valueOf(year) + "-0" + month + "-" + i;
            }
            if (month >= 10 && i < 10) {
                aDate = String.valueOf(year) + "-" + month + "-0" + i;
            }
            if (month >= 10 && i >= 10) {
                aDate = String.valueOf(year) + "-" + month + "-" + i;
            }

            list.add(aDate);
        }
        return list;
    }

    public static void main(String[] args) {

        String timeStamp = date2TimeStamp("2017-11-10", "yyyy-MM-dd");
        System.out.print(timeStamp);

//        List<String> dateStr = new ArrayList<>();
//        dateStr.addAll(getDayByMonth(2018, 0));
//        dateStr.addAll(getDayByMonth(2018, 1));
//        dateStr.addAll(getDayByMonth(2018, 2));
//        dateStr.addAll(getDayByMonth(2018, 3));
//        dateStr.addAll(getDayByMonth(2018, 4));
//        System.out.println(dateStr.size()+"");
//        for (int i = 0; i < dateStr.size(); i++) {
//            System.out.println(dateStr.get(i));
//        }
    }
}
