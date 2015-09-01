package com.payroll.calculator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.payroll.Utility;
import com.payroll.bean.Deduction;
import com.payroll.bean.EmployeeContract;
import com.payroll.bean.EmployeeSalary;
import com.payroll.bean.data.JsonData;
import com.payroll.bean.data.RequestData;
import com.payroll.bean.data.ResponseData;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Created by root on 8/27/15.
 */
public class PayrollProcessor {
    private RequestData requestData;
    private EmployeeContract employeeContract = new EmployeeContract();
    private EmployeeSalary employeeSalary;


    public PayrollProcessor(RequestData requestData){
        this.requestData = requestData;
    }


    /**
     * This is entry point of the code.
     * Has 3 separate steps
     * 1) Initialize the Employee Salary Object
     * 2) Calculate the Salary based on input and set values
     * 3) Generate the Response object which contain the JSON output
     *
     * @return ResponseData
     */
    public ResponseData getSalary(){
        ResponseData responseData = new ResponseData();
        if( this.requestData!=null ){

            try {
                bootstrapEmployeeData();
                calculateSalary();
                responseData = generateResponse();
            } catch (Exception e) {
                responseData = generateExceptionResponse( e );
                e.printStackTrace();
            }



        }
        return responseData;
    }


    private void calculateSalary() throws Exception {

        BigDecimal bdGrossAmount = Utility.fixPennyDecimal(new BigDecimal(employeeContract.getGrossAmount()));

        BigDecimal bdAmount = applyDeductions(Utility.DEDUCTION_TYPE.PRE_TAX, bdGrossAmount );

        bdAmount = applyTaxes(bdAmount);

        bdAmount  = applyDeductions(Utility.DEDUCTION_TYPE.POST_TAX, bdAmount );

        this.employeeSalary.setNetAmount( Utility.fixPennyDecimal(bdAmount).doubleValue() );
    }


    /**
     * Calculates total deduction based on whether it is Pre Tax of Post Tax
     * Populates the EmployeeSalary object with calculated values.
     * @param deductionType
     * @return BigDecimal
     */
    private BigDecimal getDeductions( Utility.DEDUCTION_TYPE deductionType ){
        ArrayList<Deduction> arrDeduction = employeeContract.getDeductions();
        BigDecimal bdDeduction = new BigDecimal(0.0);
        if(arrDeduction!=null && !arrDeduction.isEmpty()) {
            for( Deduction deduction: arrDeduction ){
                if( (deductionType == Utility.DEDUCTION_TYPE.PRE_TAX && deduction.isPreTax()) ||
                        (deductionType == Utility.DEDUCTION_TYPE.POST_TAX && !deduction.isPreTax())   ) {
                    bdDeduction = bdDeduction.add(new BigDecimal(deduction.getAmount()));
                }
            }
        }


        Double deduction = Utility.fixPennyDecimal(bdDeduction).doubleValue();
        if( deductionType == Utility.DEDUCTION_TYPE.PRE_TAX ) {
            this.employeeSalary.setPreTaxDeductionTotal( deduction );
        } else if( deductionType == Utility.DEDUCTION_TYPE.POST_TAX){
            this.employeeSalary.setPostTaxDeductionTotal( deduction );
        }

        return bdDeduction;
    }


    private BigDecimal applyDeductions(Utility.DEDUCTION_TYPE deductionType , BigDecimal bdAmount ){
        BigDecimal bdDeduction = getDeductions(deductionType );
        bdAmount = bdAmount.subtract( bdDeduction );
        return bdAmount;
    }


    /**
     * The tax amount is subtracted from the total amount here.
     * @param bdAmount  The taxable amount before subtracting taxes.
     * @return BigDecimal - amount after deducting taxes
     */
    private BigDecimal applyTaxes( BigDecimal bdAmount ){

        BigDecimal bdFederalTaxAmount = Utility.fixPennyDecimal(Calculator.calculateTaxAmount(bdAmount, new BigDecimal(employeeContract.getFederalTaxRate())));
        BigDecimal bdStateTaxAmount = Utility.fixPennyDecimal(Calculator.calculateTaxAmount(bdAmount, new BigDecimal(employeeContract.getStateTaxRate())));

        this.employeeSalary.setFederalTaxAmount(bdFederalTaxAmount.doubleValue());
        this.employeeSalary.setStateTaxAmount(bdStateTaxAmount.doubleValue());

        bdAmount = bdAmount.subtract(bdFederalTaxAmount).subtract(bdStateTaxAmount);

        return bdAmount;
    }


    private void bootstrapEmployeeData(  ) throws IOException, NoSuchMethodException {
        JsonData jsonData = (JsonData)requestData.getData();
        this.employeeContract = Utility.convertJsonToPOJO( jsonData.getDataAsString(),EmployeeContract.class );

        this.employeeSalary = new EmployeeSalary();
        this.employeeSalary.setFirstName( Utility.removeCommas(this.employeeContract.getFirstName()) );
        this.employeeSalary.setLastName( Utility.removeCommas(this.employeeContract.getLastName()));
        this.employeeSalary.setGrossAmount( this.employeeContract.getGrossAmount() );
    }

    /**
     * Generating the ResponseData here with JSON data.
     * @return
     */
    private ResponseData generateResponse() throws JsonProcessingException {
        String jsonResponse = Utility.convertPOJOToJson(this.employeeSalary);
        JsonData jsonResultData = new JsonData( jsonResponse );
        ResponseData responseData = new ResponseData( jsonResultData );
        return responseData;
    }



    /**
     * Generating the ResponseData here with Exception.
     * @return
     */
    private ResponseData generateExceptionResponse(Exception e) {
        String jsonResponse = "{\"exception\":\"Oops!! Something went wrong, please try again later. Please check and make sure you have used a valid input.\"}";
        JsonData jsonResultData = new JsonData( jsonResponse );
        ResponseData responseData = new ResponseData( jsonResultData );
        return responseData;
    }
}
