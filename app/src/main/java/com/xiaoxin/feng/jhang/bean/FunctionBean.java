package com.xiaoxin.feng.jhang.bean;

/**
 * @author: xiaoxin
 * date: 2018/9/7
 * describe:
 * 修改内容:
 */
public class FunctionBean {

    private String functionName;
    private int functionCode;

    public FunctionBean(String functionName, int functionCode) {
        this.functionName = functionName;
        this.functionCode = functionCode;
    }

    public String getFunctionName() {
        return functionName;
    }

    public int getFunctionCode() {
        return functionCode;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public void setFunctionCode(int functionCode) {
        this.functionCode = functionCode;
    }
}
