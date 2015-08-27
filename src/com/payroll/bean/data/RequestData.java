package com.payroll.bean.data;

/**
 * Created by root on 8/26/15.
 */
public class RequestData {
    private Data data;
    public RequestData( Data data ){
        this.data = data;
    }

    public Data getData(){
        return this.data;
    }
}
