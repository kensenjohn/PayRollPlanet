package com.payroll;

import com.payroll.bean.data.Data;
import com.payroll.bean.data.JsonData;
import com.payroll.bean.data.RequestData;
import com.payroll.bean.data.ResponseData;
import com.payroll.calculator.PayrollProcessor;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by root on 8/27/15.
 */
@Path("/")
public class Payroll {
    @POST
    @Path("/salary")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create( String sResult ) {

        Data data = new JsonData(sResult);
        RequestData requestData = new RequestData(data);

        PayrollProcessor payrollProcessor = new PayrollProcessor( requestData );
        ResponseData responseData = payrollProcessor.getSalary(  );

        JsonData jsonResponseData =  (JsonData)responseData.getData();
        return Response.status(200).entity(jsonResponseData.getJsonString() ).build();

    }
}
