package com.xiaoxin.guid.bean;

import java.util.List;

/**
 * @author: xiaoxin
 * date: 2018/10/10
 * describe:
 * 修改内容:
 */
public class BzBean {


    /**
     * data : [{"version":null,"id":3,"name":"无明显急慢性特征","symptomCourseId":878},{"version":null,"id":7,"name":"急性发病","symptomCourseId":879},{"version":null,"id":1,"name":"突然发病","symptomCourseId":880},{"version":null,"id":2,"name":"慢性发病","symptomCourseId":881}]
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

    public static class DataBean {
        /**
         * version : null
         * id : 3
         * name : 无明显急慢性特征
         * symptomCourseId : 878
         */

        private Object version;
        private int id;
        private String name;
        private int symptomCourseId;

        public Object getVersion() {
            return version;
        }

        public void setVersion(Object version) {
            this.version = version;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getSymptomCourseId() {
            return symptomCourseId;
        }

        public void setSymptomCourseId(int symptomCourseId) {
            this.symptomCourseId = symptomCourseId;
        }
    }
}
