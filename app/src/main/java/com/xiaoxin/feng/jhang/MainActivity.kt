package com.xiaoxin.feng.jhang

import android.Manifest
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.blankj.utilcode.util.ActivityUtils
import com.xiaoxin.feng.jhang.activity.*
import com.xiaoxin.feng.jhang.activity.animation.AnimationActivity
import com.xiaoxin.feng.jhang.util.AccessibilityUtil
import com.xiaoxin.feng.jhang.wallpaper.WallPaperActivity
import com.xiaoxin.guid.activity.HumanBodyActivity
import com.xiaoxin.guid.listfriend.ListFriendActivity
import com.xiaoxin.guid.search.disease.SearchDiseaseActivity
import com.xiaoxin.guid.search.drug.DrugListActivity
import com.xiaoxin.guid.search.hospital.KsSearchHospitalActivity
import com.xiaoxin.guid.search.truth.TruthActivity
import com.xiaoxin.guid.util.AppUtil
import com.xiaoxin.library.base.BaseActivity
import com.xiaoxin.library.jump
import kotlinx.android.synthetic.main.activity_main.*
import uk.co.samuelwall.materialtaptargetprompt.MaterialTapTargetPrompt
import uk.co.samuelwall.materialtaptargetprompt.extras.backgrounds.RectanglePromptBackground
import uk.co.samuelwall.materialtaptargetprompt.extras.focals.RectanglePromptFocal

class MainActivity :BaseActivity(){

    override fun initData(bundle: Bundle?) {

    }

    override fun bindLayout(): Int {
        return R.layout.activity_main
    }

    override fun initView(savedInstanceState: Bundle?, contentView: View?) {


        bt_read_pic.setOnClickListener { jump<ReadPicActivity>() }
        bt_apk_parser.setOnClickListener { jump<ApkParserActivity>() }
        bt_read_hard.setOnClickListener { jump<ReadHardActivity>() }
        bt_wall_paper.setOnClickListener { jump<WallPaperActivity>() }
        bt_mars_text.setOnClickListener { jump<MarsTextActivity>() }
        bt_auto_message.setOnClickListener {
            val accessibility = AccessibilityUtil.isAccessibilitySettingsOn(this@MainActivity)
            if (!accessibility) {
//                showDialog()
            } else {
                jump<AccessbilityActivity>()
            }
        }

        bt_dd_robot.setOnClickListener { jump<RobotActivity>() }
        bt_wechat_zb.setOnClickListener { jump<WechatZbActivity>() }
        bt_sms.setOnClickListener { jump<SmsBackActivity>() }
        bt_dz.setOnClickListener { jump<HumanBodyActivity>() }
        bt_douyin.setOnClickListener { jump<DouyinPersonActivity>() }
        bt_animation.setOnClickListener { jump<AnimationActivity>() }
        bt_search_drug.setOnClickListener { jump<SearchDiseaseActivity>() }

        bt_search_hospital.setOnClickListener { jump<KsSearchHospitalActivity>() }
        bt_online_drug.setOnClickListener { jump<DrugListActivity>() }
        tv_fj_drug.setOnClickListener { AppUtil.startMap(this) }
        bt_down.setOnClickListener {
            val uri = Uri.parse("http://toutiao.iiilab.com/")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent) }
        //JkktActivity
        tv_truth.setOnClickListener { jump<TruthActivity>() }

        tv_list.setOnClickListener { jump<ListFriendActivity>() }

        MaterialTapTargetPrompt.Builder(this)
                .setTarget(bt_apk_parser)
                .setPrimaryText("提示")
                .setSecondaryText("选择apk文件可以读取清单信息")
                .setPromptBackground(RectanglePromptBackground())
                .setPromptFocal(RectanglePromptFocal())
                .show()
    }

    override fun doBusiness() {

        checkPermission()
    }

    private fun showDialog() {
        AlertDialog.Builder(this)
                .setTitle("需要开启辅助功能")
                .setMessage("使用此功能需要开启辅助功能。现在去开启辅助功能")
                .setPositiveButton("确定") { dialog, which -> AccessibilityUtil.checkAccessibility(this@MainActivity) }
                .setNegativeButton("取消") { dialog, which ->
                    //                       Toast.makeText(MainActivity.this,"好吧(ಥ﹏ಥ)",Toast.LENGTH_SHORT).show();
                }.show().show()
    }

    private fun checkPermission() {

        if (ContextCompat.checkSelfPermission(this@MainActivity,
                        Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this@MainActivity,
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_SMS, Manifest.permission.WRITE_CONTACTS, Manifest.permission.READ_CONTACTS, Manifest.permission.READ_PHONE_STATE, Manifest.permission.SYSTEM_ALERT_WINDOW, Manifest.permission.ACCESS_FINE_LOCATION), 60)
        }
    }

}