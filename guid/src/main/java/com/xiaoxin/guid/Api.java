package com.xiaoxin.guid;

/**
 * @author: xiaoxin
 * date: 2018/9/30
 * describe:
 * 修改内容:
 */
public class Api {

    //第一步接口
    public static final String FIRST = "http://1.85.45.235:37003/patient/v1/clientGuide/bodyPart/getByBodyPartName";

    //第二步接口
    public static final String SECOND = "http://1.85.45.235:37003/patient/v1/clientGuide/bodyPart/getBySymptomId";

    //第三步接口
    public static final String THREE = "http://1.85.45.235:37003/patient/v1/clientGuide/bodyPart/getBySymptomCourseId";

    //第四步接口
    public static final String FOUR = "http://1.85.45.235:37003/patient/v1/clientGuide/bodyPart/getDisease";

   /** 查疾病 详情*/
    public static final String SEARCH_DISEASE = "https://dxy.com/app/i/content/detail";

   /** 查疾病 详情*/
    public static final String SEARCH_DRUG = "https://dxy.com/app/i/ask/drug/category";

   /** 搜索药品名称*/
    public static final String SEARCH_DRUG_LIST = "https://dxy.com/app/i/ask/drug/search/tip";

    /** 医院列表*/
    public static final String HOSPITAL_LIST = "https://dxy.com/app/i/recommend/hospital/combination/list";

   /** 医院详情*/
    public static final String HOSPITAL_DETAIL = "https://dxy.com/app/i/hospital/single";

   /** 每日真相*/
    public static final String TRUTH_DETAIL = "https://dxy.com/app/i/ask/healthtruth";


}
