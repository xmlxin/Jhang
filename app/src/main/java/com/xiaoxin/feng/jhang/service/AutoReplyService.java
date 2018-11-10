package com.xiaoxin.feng.jhang.service;

import android.accessibilityservice.AccessibilityService;
import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import com.xiaoxin.feng.jhang.app.Constant;

import java.io.IOException;
import java.util.List;

/**
 * @author: xiaoxin
 * date: 2018/10/31
 * describe: 自动回复
 * 修改内容:
 */
public class AutoReplyService extends AccessibilityService{

    private static final String TAG = "AutoReplyService";

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {

        if (event.getEventType() == AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED) {
            sendNotifacationReply(event);
            sendMsg(Constant.DD_ET_ID,"此消息为钉钉机器人自动回复,",1);
            back2Home();
        }
    }

    @Override
    public void onInterrupt() {

    }

    private void sendMsg(String packageNameEdit, String sendContent,int sendNumber) {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.e("xiaoxin", "remove view: ");
        for (int i = 0; i < sendNumber; i++) {
            if (fill(packageNameEdit,sendContent)) {
                send();

                Log.e("xiaoxin", "send success: " );
            }else {
                Log.e("xiaoxin", "fill: 复制失败");
            }
        }
    }

    @SuppressLint("NewApi")
    private boolean fill(String edit,String content) {
        AccessibilityNodeInfo rootNode = getRootInActiveWindow();
        if (rootNode != null) {
            return findEditTextSend(rootNode, edit,content);
        }
        return false;
    }

    private boolean findEditTextSend(AccessibilityNodeInfo rootNode, String edit,String content) {
        Log.e(TAG, "findEditTextSend: rootNode"+rootNode.toString() );
        List<AccessibilityNodeInfo> editInfo = rootNode.findAccessibilityNodeInfosByViewId(edit);

        if(editInfo!=null&&!editInfo.isEmpty()){
            Bundle arguments = new Bundle();
            arguments.putCharSequence(AccessibilityNodeInfo.ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE, content);
            editInfo.get(0).performAction(AccessibilityNodeInfo.ACTION_SET_TEXT, arguments);
            Log.e("xiaoxin", "findEditTextSend: true");
            return true;
        }else {
            Log.e("xiaoxin", "findEditTextSend: null");
        }
        Log.e("xiaoxin", "findEditTextSend: false"+editInfo.isEmpty() );
        return false;
    }

    /**
     * 寻找窗体中的“发送”按钮，并且点击。
     */
    @SuppressLint("NewApi")
    private void send() {
        AccessibilityNodeInfo nodeInfo = getRootInActiveWindow();
        if (nodeInfo != null) {
            List<AccessibilityNodeInfo> list = nodeInfo
                    .findAccessibilityNodeInfosByText("发送");
            if (list != null && list.size() > 0) {
                for (AccessibilityNodeInfo n : list) {
                    if(n.getClassName().equals("android.widget.Button") || n.getClassName().equals("android.widget.TextView") && n.isEnabled()){
                        n.performAction(AccessibilityNodeInfo.ACTION_CLICK);
//                        hasSend = true;  //把这里改成false 就会一直发送 可做成炸群android.widget.Buttonandroid.widget.TextView
                    }
                }

            } else {
                List<AccessibilityNodeInfo> liste = nodeInfo
                        .findAccessibilityNodeInfosByText("Send");
                if (liste != null && liste.size() > 0) {
                    for (AccessibilityNodeInfo n : liste) {
                        if(n.getClassName().equals("android.widget.Button") && n.isEnabled()){
                            n.performAction(AccessibilityNodeInfo.ACTION_CLICK);
//                            hasSend = true;
                        }
                    }
                }
            }
            pressBackButton();
        }
    }

    /**
     * 模拟back按键
     */
    private void pressBackButton(){
        Runtime runtime = Runtime.getRuntime();
        try {
            runtime.exec("input keyevent " + KeyEvent.KEYCODE_BACK);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 拉起微信界面
     * @param event
     */
    private void sendNotifacationReply(AccessibilityEvent event) {
        if (event.getParcelableData() != null
                && event.getParcelableData() instanceof Notification) {
            Notification notification = (Notification) event
                    .getParcelableData();
            CharSequence tickerText = notification.tickerText;
            PendingIntent pendingIntent = notification.contentIntent;
            try {
                pendingIntent.send();
            } catch (PendingIntent.CanceledException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 回到系统桌面
     */
    private void back2Home() {
        Intent home = new Intent(Intent.ACTION_MAIN);
        home.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        home.addCategory(Intent.CATEGORY_HOME);
        startActivity(home);
    }
}
