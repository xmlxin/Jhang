package com.xiaoxin.feng.jhang.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.xiaoxin.feng.jhang.R;

/**
 * @author xiaoxin
 * @date 2018/09/30
 * @describe ：智能聊天机器人
 * http://drea.cc/mm.php 抓包获取
 * http://open.drea.cc/chat/get?keyWord=%E6%98%AF%E5%A4%9A%E5%B0%91&userName=drea_bbs
    keyWord  你好
    userName drea_bbs

{
data: {
keyWord: "是多少",
reply: "主人说多少就多少。"
},
isSuccess: true,
code: "0",
message: ""
}

 2.http://api.qingyunke.com/api.php?key=free&appid=0&msg=

 * 修改内容
 */
public class MyRobotActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_robot);

    }
}
