package com.xiaoxin.guid.bean.search;

import java.util.List;

/**
 * @author: xiaoxin
 * date: 2018/10/29
 * describe: 药品种类
 * 修改内容:
 */
public class DrugTypeBean {


    /**
     * data : {"items":[{"id":"2","name":"解热镇痛"},{"id":"10","name":"消化系统"},{"id":"29","name":"皮肤用药"},{"id":"57","name":"风湿骨痛"},{"id":"69","name":"神经系统"},{"id":"84","name":"心脑血管"},{"id":"101","name":"糖尿病类"},{"id":"106","name":"妇科用药"},{"id":"130","name":"儿科用药"},{"id":"140","name":"肝胆用药"},{"id":"155","name":"男科用药"},{"id":"161","name":"泌尿系统"},{"id":"169","name":"维矿物质"},{"id":"178","name":"补益安神"},{"id":"192","name":"五官用药"},{"id":"210","name":"清热解毒"},{"id":"227","name":"呼吸系统"},{"id":"442","name":"内分泌"},{"id":"453","name":"减肥瘦身"},{"id":"458","name":"肿瘤用药"},{"id":"680","name":"生发乌发"},{"id":"706","name":"其他"},{"id":"797","name":"生物制品"},{"id":"802","name":"肺动脉高压"},{"id":"819","name":"不孕不育保胎"}]}
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
             * id : 2
             * name : 解热镇痛
             */

            private String id;
            private String name;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
