package com.xiaoxin.guid.bean;

/**
 * @author: xiaoxin
 * date: 2018/10/16
 * describe:
 * 修改内容:
 */
public class GuidanceBean {

    private String id;
    private String name;
    private String symptomCourseId;
    private String part;
    private String sex;

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSymptomCourseId(String symptomCourseId) {
        this.symptomCourseId = symptomCourseId;
    }

    public void setPart(String part) {
        this.part = part;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSymptomCourseId() {
        return symptomCourseId;
    }

    public String getPart() {
        return part;
    }

    public String getSex() {
        return sex;
    }
}
