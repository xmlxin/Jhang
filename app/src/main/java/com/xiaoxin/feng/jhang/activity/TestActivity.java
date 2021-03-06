package com.xiaoxin.feng.jhang.activity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.webkit.WebView;

import com.xiaoxin.feng.jhang.R;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        WebView content = findViewById(R.id.tv_content);

//        content.loadUrl("file:///android_asset/html协议.html");
        content.loadData(getHtmlData(),"text/html","charset=UTF-8");
//        content.setText(R.string.xieyi);
//        RichText.fromHtml(getResources().getString(R.string.xieyi)).into(content);

    }

    private String getHtmlData() {
//        String head = "<head><style>img{max-width: 100%; width:auto; height: auto;}</style></head>";
        return " <html>\n" +
                "\t<head>\n" +
                "\t <title>签约协议</title>\n" +
                "\n" +
                "\t <style>\n" +
                "        \timg{max-width: 96%;\n" +
                "           \t\t padding: 2%;}\n" +
                "     </style>\n" +
                "\n" +
                "\t</head>\n" +
                "\n" +
                "\t\t<body>\n" +
                "\t\t\t<p style=\"text-align:center\"><strong><span style=\"font-size:29px;font-family:宋体\">家庭医生签约服务内容</span></strong></p><p></p><p><span style=\"font-family:宋体\">本着平等、利民、互惠的原则，根据国家“家庭医生”签约服务落地的政策，签约客户承诺上述药品及营养保健品每月均在怡康医药的连锁药店购买，享受服务主体提供的以下健康服务内容。各方本着平等、尊重和自愿的原则签订此协议，接受以下条款的约定。</span></p><p>;</p><p><span style=\"font-family:宋体\">一、协议期内，由怡适康健康管理服务中心为签约客户提供价值</span>4680<span style=\"font-family:宋体\">元的健康管理服务，所包含内容如下：</span></p><p>1<span style=\"font-family:宋体\">、健康档案：建立个人健康档案服务，从个人基本资料采集、疾病史、个人史、家族史、用药史、饮食习惯、运动习惯等资料全面收集建立完整的健康档案，并妥善保存，及时更新健康资料和信息，并为健康档案隐私尽保护责任；</span></p><p>2<span style=\"font-family:宋体\">、健康检查：</span></p><p><span style=\"font-family:宋体\">①基础健康检查（每月</span>1<span style=\"font-family:宋体\">次价值</span>240<span style=\"font-family:宋体\">元，共</span>12<span style=\"font-family:宋体\">次价值</span>2880<span style=\"font-family:宋体\">元）包含：腰臀比、血氧、血糖、血压、人体成分、心电、骨密度、动脉硬化、身高、体重、体温；</span></p><p><span style=\"font-family:宋体\">②国际领先高端生物波检测</span>1<span style=\"font-family:宋体\">次（价值</span>1800<span style=\"font-family:宋体\">元）包含：基本体质指数、十二经络反应性检测、脊椎神经系统反应性检测、常见维生素检测及食物来源、常见矿物质检测及食物来源、常见氨基酸检测及食物来源、常见毒素检测及可能来源、引起呼吸道及咽喉不适的常见原因检测、引起毒素堆积的常见原因检测、引起头晕头痛的常见原因检测、引起血压不稳</span>/<span style=\"font-family:宋体\">心率不齐</span>/<span style=\"font-family:宋体\">心慌</span>/<span style=\"font-family:宋体\">心悸</span>/<span style=\"font-family:宋体\">胸闷的常见原因检测、引起疲劳的常见原因检测、引起腰腿疼痛的常见原因检测、引起性功能障碍的常见原因检测、引起便秘的常见原因检测、引起高胆固醇和甘油三酯症的常见原因检测、引起血糖失衡的常见原因检测、引起肥胖的常见原因检测、引起毛发脱落的常见原因检测、引起贫血的常见原因检测；</span></p><p>3<span style=\"font-family:宋体\">、报告解读：根据检测结果及健康档案提供的信息，由专业医生提供门店一对一的</span>12<span style=\"font-family:宋体\">次报告解读服务或互联网怡适康在线医生一对一的</span>24<span style=\"font-family:宋体\">次报告解读服务；</span></p><p>4<span style=\"font-family:宋体\">、健康干预方案制定：根据体检报告结合会员个人情况提供科学、规范、个体化的健康指导方案，包含：饮食指导、运动指导、营养保健指导、心理指导、药物指导、理疗指导；</span></p><p>5<span style=\"font-family:宋体\">、健康教育：微信公众号</span>365<span style=\"font-family:宋体\">天健康信息内容推送、每月</span>1-2<span style=\"font-family:宋体\">次健康回访、每周</span>1<span style=\"font-family:宋体\">次健康大讲坛普及健康知识、每月</span>1<span style=\"font-family:宋体\">次驻店专家义诊服务、每年</span>4<span style=\"font-family:宋体\">次将季节性和突发性公共卫生事件信息告知签约客户；</span></p><p>6<span style=\"font-family:宋体\">、效果评价：健康干预</span>1<span style=\"font-family:宋体\">个月后根据签约客户再次体检结果及自我感觉情况，对健康干预方案作出调整；</span></p><p>7<span style=\"font-family:宋体\">、咨询</span>/<span style=\"font-family:宋体\">预约</span>/<span style=\"font-family:宋体\">转诊服务：提供免费怡适康驻店医生或怡适康在线医生咨询服务；提供怡适康医联体预约及转诊服务，预约或转诊需签约客户提前</span>24<span style=\"font-family:宋体\">小时告知服务主体。怡适康医联体包含：交大一附院、怡康长安（康泰）医院、璞太和中医（馆）院；</span></p><p>;</p><p><span style=\"font-family:宋体\">二、协议期内，璞太和中医医院为签约客户提供的单次免费项目（</span>6<span style=\"font-family:宋体\">个项目合计价值</span> 192<span style=\"font-family:宋体\">元</span> <span style=\"font-family:宋体\">）：</span></p><p style=\"text-align:center\"><img src=\"http://192.168.88.163:8888/group1/M00/00/7D/wKhYo1vSoKqARKbcAAA_AmFiooU918.png\" title=\"file\" alt=\"file\"/></p><p>;<br/></p><p style=\"margin-left:0;text-indent:0\">三、<span style=\"font-family: 宋体\">协议期内，怡康长安康泰医院为签约客户提供的单次免费项目（</span>7<span style=\"font-family:宋体\">个项目合计价值</span> 173<span style=\"font-family:宋体\">元</span> <span style=\"font-family:宋体\">）：</span></p><p style=\"text-align:center\"><img src=\"http://192.168.88.163:8888/group1/M00/00/7D/wKhYo1vSoOOAFeaiAAA9bVGL3Zk608.png\" title=\"file\" alt=\"file\"/></p><p style=\"margin-left:0;text-indent:0\">四、<span style=\"font-family: 宋体\">签约条件：</span></p><p>1<span style=\"font-family:宋体\">、签约客户已是怡康医药会员或授权本协议签约后自愿成为怡康医药会员；</span></p><p>2<span style=\"font-family:宋体\">、签约客户承诺每月在怡康医药消费不少于</span>200<span style=\"font-family:宋体\">元；</span></p><p>3<span style=\"font-family:宋体\">、怡康医药补贴签约费用，签约客户可享受以上免费服务内容；</span></p><p>4<span style=\"font-family:宋体\">、签约客户于签订协议</span>30<span style=\"font-family:宋体\">日内到怡适康健康管理服务中心激活签约服务内容；</span></p><p>5<span style=\"font-family:宋体\">、根据有关法律法规，服务主体必须保护签约客户相关健康信息及隐私；</span></p><p>6<span style=\"font-family:宋体\">、签约客户在其他医疗机构接受的医疗行为，由其他医疗机构负责；</span></p><p>7<span style=\"font-family:宋体\">、本协议服务有效期为壹年。</span></p><p><br/></p>\n" +
                "\t\t</body>\n" +
                "\n" +
                "</html>";


    }
}
