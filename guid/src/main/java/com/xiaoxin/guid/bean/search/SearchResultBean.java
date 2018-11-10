package com.xiaoxin.guid.bean.search;

import java.util.List;

/**
 * @author: xiaoxin
 * date: 2018/10/31
 * describe:
 * 修改内容:
 */
public class SearchResultBean {


    /**
     * data : {"items":["布洛芬片","布洛芬胶囊","布洛芬混悬滴剂","精氨酸布洛芬颗粒","布洛芬缓释胶囊","布洛芬颗粒","布洛芬混悬液"]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<String> items;

        public List<String> getItems() {
            return items;
        }

        public void setItems(List<String> items) {
            this.items = items;
        }
    }
}
