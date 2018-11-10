package com.xiaoxin.guid.bean.search;

import java.util.List;

/**
 * @author: xiaoxin
 * date: 2018/10/27
 * describe: 查找医院第一步
 * 修改内容:
 */
public class SearchHospitalOneBean {

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {

        private int items_per_page;
        private int start_index;
        private int page_index;
        private int total_pages;
        private int total_items;
        private int current_item_count;
        private List<ItemsBean> items;

        public int getItems_per_page() {
            return items_per_page;
        }

        public void setItems_per_page(int items_per_page) {
            this.items_per_page = items_per_page;
        }

        public int getStart_index() {
            return start_index;
        }

        public void setStart_index(int start_index) {
            this.start_index = start_index;
        }

        public int getPage_index() {
            return page_index;
        }

        public void setPage_index(int page_index) {
            this.page_index = page_index;
        }

        public int getTotal_pages() {
            return total_pages;
        }

        public void setTotal_pages(int total_pages) {
            this.total_pages = total_pages;
        }

        public int getTotal_items() {
            return total_items;
        }

        public void setTotal_items(int total_items) {
            this.total_items = total_items;
        }

        public int getCurrent_item_count() {
            return current_item_count;
        }

        public void setCurrent_item_count(int current_item_count) {
            this.current_item_count = current_item_count;
        }

        public List<ItemsBean> getItems() {
            return items;
        }

        public void setItems(List<ItemsBean> items) {
            this.items = items;
        }

        public static class ItemsBean {
            /**
             * id : 9
             * name : 皮肤性病科
             * cover_url : https://img.dxycdn.com/dotcom/2016/10/24/20/nz2bsqhc.png
             * member_count : 315
             * consult_expert_count : 0
             * description : 痣、痘痘、痤疮、湿疹、狐臭、瘙痒、脚气、脱发、灰指甲、梅毒、淋病、皮肤过敏、毛孔粗大、毛囊炎
             * public_question_status : 0
             * voice_status : 1
             * voice_available_count : 0
             * doctor_avatar_list : ["https://img1.dxycdn.com/2018/0620/743/3284562712662974267-22.jpg!wh200","https://img1.dxycdn.com/2018/1022/295/3307568533185402389-22.jpg!wh200","https://img1.dxycdn.com/2018/0705/608/3287367420796709674-22.jpg!wh200","https://img1.dxycdn.com/2018/1015/779/3306203508826910658-22.jpg!wh200","https://img1.dxycdn.com/2018/0925/569/3302519333124936295-22.jpg!wh200"]
             * suggest_tag :
             */

            private int id;
            private String name;
            private String cover_url;
            private int member_count;
            private int consult_expert_count;
            private String description;
            private int public_question_status;
            private int voice_status;
            private int voice_available_count;
            private String suggest_tag;
            private List<String> doctor_avatar_list;

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

            public String getCover_url() {
                return cover_url;
            }

            public void setCover_url(String cover_url) {
                this.cover_url = cover_url;
            }

            public int getMember_count() {
                return member_count;
            }

            public void setMember_count(int member_count) {
                this.member_count = member_count;
            }

            public int getConsult_expert_count() {
                return consult_expert_count;
            }

            public void setConsult_expert_count(int consult_expert_count) {
                this.consult_expert_count = consult_expert_count;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public int getPublic_question_status() {
                return public_question_status;
            }

            public void setPublic_question_status(int public_question_status) {
                this.public_question_status = public_question_status;
            }

            public int getVoice_status() {
                return voice_status;
            }

            public void setVoice_status(int voice_status) {
                this.voice_status = voice_status;
            }

            public int getVoice_available_count() {
                return voice_available_count;
            }

            public void setVoice_available_count(int voice_available_count) {
                this.voice_available_count = voice_available_count;
            }

            public String getSuggest_tag() {
                return suggest_tag;
            }

            public void setSuggest_tag(String suggest_tag) {
                this.suggest_tag = suggest_tag;
            }

            public List<String> getDoctor_avatar_list() {
                return doctor_avatar_list;
            }

            public void setDoctor_avatar_list(List<String> doctor_avatar_list) {
                this.doctor_avatar_list = doctor_avatar_list;
            }
        }
    }
}
