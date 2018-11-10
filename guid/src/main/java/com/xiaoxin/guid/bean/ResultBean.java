package com.xiaoxin.guid.bean;

import java.util.List;

/**
 * @author: xiaoxin
 * date: 2018/10/10
 * describe:
 * 修改内容:
 */
public class ResultBean {

    /**
     * data : [{"version":null,"id":262,"diseaseName":"甲沟炎和甲下脓肿","percent":31.25,"juniorCodes":["011001","002008","009019","009013"],"deptList":[{"version":null,"id":22,"seniorId":2,"juniorCode":"002008","juniorName":"普外科"},{"version":null,"id":72,"seniorId":9,"juniorCode":"009013","juniorName":"中医皮肤科"},{"version":null,"id":78,"seniorId":9,"juniorCode":"009019","juniorName":"中医外科"},{"version":null,"id":87,"seniorId":11,"juniorCode":"011001","juniorName":"皮肤科"}]},{"version":null,"id":780,"diseaseName":"甲肥厚","percent":31.25,"juniorCodes":["011001"],"deptList":[{"version":null,"id":87,"seniorId":11,"juniorCode":"011001","juniorName":"皮肤科"}]},{"version":null,"id":537,"diseaseName":"甲癣","percent":18.75,"juniorCodes":["011001","009013"],"deptList":[{"version":null,"id":72,"seniorId":9,"juniorCode":"009013","juniorName":"中医皮肤科"},{"version":null,"id":87,"seniorId":11,"juniorCode":"011001","juniorName":"皮肤科"}]},{"version":null,"id":756,"diseaseName":"钱币状掌跖角化病","percent":10,"juniorCodes":["011001"],"deptList":[{"version":null,"id":87,"seniorId":11,"juniorCode":"011001","juniorName":"皮肤科"}]}]
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
         * id : 262
         * diseaseName : 甲沟炎和甲下脓肿
         * percent : 31.25
         * juniorCodes : ["011001","002008","009019","009013"]
         * deptList : [{"version":null,"id":22,"seniorId":2,"juniorCode":"002008","juniorName":"普外科"},{"version":null,"id":72,"seniorId":9,"juniorCode":"009013","juniorName":"中医皮肤科"},{"version":null,"id":78,"seniorId":9,"juniorCode":"009019","juniorName":"中医外科"},{"version":null,"id":87,"seniorId":11,"juniorCode":"011001","juniorName":"皮肤科"}]
         */

        private Object version;
        private int id;
        private String diseaseName;
        private double percent;
        private List<String> juniorCodes;
        private List<DeptListBean> deptList;

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

        public String getDiseaseName() {
            return diseaseName;
        }

        public void setDiseaseName(String diseaseName) {
            this.diseaseName = diseaseName;
        }

        public double getPercent() {
            return percent;
        }

        public void setPercent(double percent) {
            this.percent = percent;
        }

        public List<String> getJuniorCodes() {
            return juniorCodes;
        }

        public void setJuniorCodes(List<String> juniorCodes) {
            this.juniorCodes = juniorCodes;
        }

        public List<DeptListBean> getDeptList() {
            return deptList;
        }

        public void setDeptList(List<DeptListBean> deptList) {
            this.deptList = deptList;
        }

        public static class DeptListBean {
            /**
             * version : null
             * id : 22
             * seniorId : 2
             * juniorCode : 002008
             * juniorName : 普外科
             */

            private Object version;
            private int id;
            private int seniorId;
            private String juniorCode;
            private String juniorName;

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

            public int getSeniorId() {
                return seniorId;
            }

            public void setSeniorId(int seniorId) {
                this.seniorId = seniorId;
            }

            public String getJuniorCode() {
                return juniorCode;
            }

            public void setJuniorCode(String juniorCode) {
                this.juniorCode = juniorCode;
            }

            public String getJuniorName() {
                return juniorName;
            }

            public void setJuniorName(String juniorName) {
                this.juniorName = juniorName;
            }
        }
    }
}
