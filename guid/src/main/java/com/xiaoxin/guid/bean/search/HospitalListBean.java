package com.xiaoxin.guid.bean.search;

import java.util.List;

/**
 * @author: xiaoxin
 * date: 2018/11/5
 * describe: 医院列表
 * 修改内容:
 */
public class HospitalListBean {


    /**
     * data : {"items_per_page":20,"start_index":1,"page_index":1,"total_pages":1,"message":{"search_message":{"section":"皮肤性病科"},"next":{"post_code":"370100","location_level":2,"location_level_name":"山东"},"self":{"post_code":"370100","post_country":"中国","post_province":"山东","post_city":"济南市"}},"total_items":3,"current_item_count":3,"items":[{"id":"hos_9668_1802","section":"皮肤性病科","section_id":1802,"hospital_name":"山东省千佛山医院","hospital_id":9668,"hospital_grade":"三级甲等","address":"济南市经十路16766号"},{"id":"hos_15879_1802","section":"皮肤性病科","section_id":1802,"hospital_name":"山东省立医院","hospital_id":15879,"hospital_grade":"三级甲等","address":"济南市经五纬七路324号"},{"id":"hos_1086_1802","section":"皮肤性病科","section_id":1802,"hospital_name":"山东大学齐鲁医院","hospital_id":1086,"hospital_grade":"三级甲等","address":"文化西路107号"}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * items_per_page : 20
         * start_index : 1
         * page_index : 1
         * total_pages : 1
         * message : {"search_message":{"section":"皮肤性病科"},"next":{"post_code":"370100","location_level":2,"location_level_name":"山东"},"self":{"post_code":"370100","post_country":"中国","post_province":"山东","post_city":"济南市"}}
         * total_items : 3
         * current_item_count : 3
         * items : [{"id":"hos_9668_1802","section":"皮肤性病科","section_id":1802,"hospital_name":"山东省千佛山医院","hospital_id":9668,"hospital_grade":"三级甲等","address":"济南市经十路16766号"},{"id":"hos_15879_1802","section":"皮肤性病科","section_id":1802,"hospital_name":"山东省立医院","hospital_id":15879,"hospital_grade":"三级甲等","address":"济南市经五纬七路324号"},{"id":"hos_1086_1802","section":"皮肤性病科","section_id":1802,"hospital_name":"山东大学齐鲁医院","hospital_id":1086,"hospital_grade":"三级甲等","address":"文化西路107号"}]
         */

        private int items_per_page;
        private int start_index;
        private int page_index;
        private int total_pages;
        private MessageBean message;
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

        public MessageBean getMessage() {
            return message;
        }

        public void setMessage(MessageBean message) {
            this.message = message;
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

        public static class MessageBean {
            /**
             * search_message : {"section":"皮肤性病科"}
             * next : {"post_code":"370100","location_level":2,"location_level_name":"山东"}
             * self : {"post_code":"370100","post_country":"中国","post_province":"山东","post_city":"济南市"}
             */

            private SearchMessageBean search_message;
            private NextBean next;
            private SelfBean self;

            public SearchMessageBean getSearch_message() {
                return search_message;
            }

            public void setSearch_message(SearchMessageBean search_message) {
                this.search_message = search_message;
            }

            public NextBean getNext() {
                return next;
            }

            public void setNext(NextBean next) {
                this.next = next;
            }

            public SelfBean getSelf() {
                return self;
            }

            public void setSelf(SelfBean self) {
                this.self = self;
            }

            public static class SearchMessageBean {
                /**
                 * section : 皮肤性病科
                 */

                private String section;

                public String getSection() {
                    return section;
                }

                public void setSection(String section) {
                    this.section = section;
                }
            }

            public static class NextBean {
                /**
                 * post_code : 370100
                 * location_level : 2
                 * location_level_name : 山东
                 */

                private String post_code;
                private int location_level;
                private String location_level_name;

                public String getPost_code() {
                    return post_code;
                }

                public void setPost_code(String post_code) {
                    this.post_code = post_code;
                }

                public int getLocation_level() {
                    return location_level;
                }

                public void setLocation_level(int location_level) {
                    this.location_level = location_level;
                }

                public String getLocation_level_name() {
                    return location_level_name;
                }

                public void setLocation_level_name(String location_level_name) {
                    this.location_level_name = location_level_name;
                }
            }

            public static class SelfBean {
                /**
                 * post_code : 370100
                 * post_country : 中国
                 * post_province : 山东
                 * post_city : 济南市
                 */

                private String post_code;
                private String post_country;
                private String post_province;
                private String post_city;

                public String getPost_code() {
                    return post_code;
                }

                public void setPost_code(String post_code) {
                    this.post_code = post_code;
                }

                public String getPost_country() {
                    return post_country;
                }

                public void setPost_country(String post_country) {
                    this.post_country = post_country;
                }

                public String getPost_province() {
                    return post_province;
                }

                public void setPost_province(String post_province) {
                    this.post_province = post_province;
                }

                public String getPost_city() {
                    return post_city;
                }

                public void setPost_city(String post_city) {
                    this.post_city = post_city;
                }
            }
        }

        public static class ItemsBean {
            /**
             * id : hos_9668_1802
             * section : 皮肤性病科
             * section_id : 1802
             * hospital_name : 山东省千佛山医院
             * hospital_id : 9668
             * hospital_grade : 三级甲等
             * address : 济南市经十路16766号
             */

            private String id;
            private String section;
            private int section_id;
            private String hospital_name;
            private int hospital_id;
            private String hospital_grade;
            private String address;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getSection() {
                return section;
            }

            public void setSection(String section) {
                this.section = section;
            }

            public int getSection_id() {
                return section_id;
            }

            public void setSection_id(int section_id) {
                this.section_id = section_id;
            }

            public String getHospital_name() {
                return hospital_name;
            }

            public void setHospital_name(String hospital_name) {
                this.hospital_name = hospital_name;
            }

            public int getHospital_id() {
                return hospital_id;
            }

            public void setHospital_id(int hospital_id) {
                this.hospital_id = hospital_id;
            }

            public String getHospital_grade() {
                return hospital_grade;
            }

            public void setHospital_grade(String hospital_grade) {
                this.hospital_grade = hospital_grade;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }
        }
    }
}
