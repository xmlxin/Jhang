package com.xiaoxin.feng.jhang.app;

import android.os.Environment;

import com.xiaoxin.feng.jhang.util.AppUtil;

/**
 * @author xiaoxin
 * @date 2018/04/08
 * @describe ：常量
 * 修改内容
 */

public class Constant {

    /**
     * 装逼路径
     */
    public static String filePath = Environment.getExternalStorageDirectory()
            .getAbsolutePath() + "/tobacco/pic/";

    public static final String SPCONFIG = "wxConfig";
    public static final String SPEAK = "speak";

    //发送次数
    public static final String SEND_NUMBER = "send_number";
    //是否发送自定义文本
    public static final String CB_CONTENT = "cb_content";
    //自定义文本内容
    public static final String ZDY_CONTENT = "zdy_content";

    public static final String TARGET_PACKAGE_MMS = "com.tencent.mm";
    public static final String SEND_MSG_MM_CLASS = "com.tencent.mm.ui.chatting.o";
    public static final String SEND_MSG_MM_METHOD = "EM";

    //wx
    public static String EDITTEXT_ID;
    public static final String SEND_ID = "com.tencent.mm:id/aai";
    public static final String SEND_TEXT = "发送";
    public static final String BUTTON = "android.widget.Button";
    public static final String TEXTVIEW = "android.widget.TextView";

    //dingding
    public static String DD_ET_ID = "com.alibaba.android.rimet:id/et_sendmessage";
    public static final String PACKAGE_DD = "com.alibaba.android.rimet";

    public static final String APP_ID = "20180524000165309";
    public static final String SECURITY_KEY = "v0Ct47KEYz_VLJgTsANZ";
    //若当月翻译字符数≤2百万，当月免费 我不信能用超

    //http://api.fanyi.baidu.com/api/trans/product/apidoc#appendix 翻译
    //智能机器人接口
    //msg 字段内容需要进行urlencode 编码处理
    //http://api.qingyunke.com/api.php?key=free&appid=0&msg=

    static {

        switch (AppUtil.getVersionName(MyApplication.mContext,TARGET_PACKAGE_MMS)) {
            case "6.6.5":
                EDITTEXT_ID = "com.tencent.mm:id/aac";
                break;
            case "6.6.6":
                EDITTEXT_ID = "com.tencent.mm:id/aaa";
                break;
            case "6.6.7":
                EDITTEXT_ID = "com.tencent.mm:id/ac8";
                break;
            case "6.7.2":
                EDITTEXT_ID = "com.tencent.mm:id/aep";
                break;
            default:
                EDITTEXT_ID = "com.tencent.mm:id/aaa";
                break;
        }
    }

}
