package com.xiaoxin.guid.bean.search;

import java.util.List;

/**
 * @author: xiaoxin
 * date: 2018/11/6
 * describe:每日真相
 * 修改内容:
 */
public class TruthBean {


    /**
     * data : {"items":[{"id":327,"title":"牙膏沾水会影响刷牙效果吗？","time":1511471190031,"content":"牙膏沾不沾水，只是泡泡多或少的小事儿，清洁效果差不多。但少数脱敏、美白牙膏遇水后功能会失效，就不要沾水了。","qrcode":"","date":"2017-11-24"}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<ItemsBean> items;

        public List<ItemsBean> getItems() {
            return items;
        }

        public void setItems(List<ItemsBean> items) {
            this.items = items;
        }

        public static class ItemsBean {
            /**
             * id : 327
             * title : 牙膏沾水会影响刷牙效果吗？
             * time : 1511471190031
             * content : 牙膏沾不沾水，只是泡泡多或少的小事儿，清洁效果差不多。但少数脱敏、美白牙膏遇水后功能会失效，就不要沾水了。
             * qrcode :
             * date : 2017-11-24
             */

            private int id;
            private String title;
            private long time;
            private String content;
            private String qrcode;
            private String date;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public long getTime() {
                return time;
            }

            public void setTime(long time) {
                this.time = time;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getQrcode() {
                return qrcode;
            }

            public void setQrcode(String qrcode) {
                this.qrcode = qrcode;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }
        }
    }
}
