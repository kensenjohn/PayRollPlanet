package com.payroll.bean.data;

/**
 * Created by root on 8/27/15.
 */
public class JsonData implements Data {
    private String jsonString = "" ;
    public JsonData(String data){
        this.jsonString = data;
    }

    public String getDataAsString() {
        return this.jsonString;
    }

}
