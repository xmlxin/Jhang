package com.xiaoxin.guid.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @author: xiaoxin
 * date: 2018/10/10
 * describe: 导诊第一步：获取症状部位
 * 修改内容:
 */
public class BwBean implements Serializable{


    /**
     * data : [{"version":null,"id":247,"symptomName":"髋部疼痛","symCouSymId":null},{"version":null,"id":584,"symptomName":"腹股沟肿块","symCouSymId":null}]
     * message : 执行成功
     * success : true
     * code : 0
     */

    private String message;
    private boolean success;
    private int code;
    private List<DataBean> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{

        /**部位
         * version : null
         * id : 247
         * symptomName : 髋部疼痛
         * symCouSymId : null
         */

        /** 病症
         * version : null
         * id : 3
         * name : 无明显急慢性特征
         * symptomCourseId : 878
         */

//        symptomName: "手指红肿发热疼痛",
//        symCouSymId: 10417

        public Object version;
        public int id;
        public String symptomName;
        public String symCouSymId;

        public String name;
        public int symptomCourseId;
        public boolean status;

    }
}
