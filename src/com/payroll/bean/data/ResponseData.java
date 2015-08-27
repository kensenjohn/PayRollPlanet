package com.payroll.bean.data;

/**
 * Created by root on 8/26/15.
 */
public class ResponseData {
    private Data data;
    public ResponseData(){

    }
    public ResponseData( Data data ){
        this.data = data;
    }
    public Data getData(){
        return this.data;
    }
}
