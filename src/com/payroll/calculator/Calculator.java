package com.payroll.calculator;

import java.math.BigDecimal;

/**
 * Created by root on 8/28/15.
 */
public class Calculator {
    /**
     * Calculates the amount of tax from the tax rate that is provided.
     * @param bdAmount - the amount to which tax has to be applied.
     * @param bdTaxRate - the tax rate percentage. (20 is equal to 20%)
     * @return BigDecimal
     */
    public static BigDecimal calculateTaxAmount(BigDecimal bdAmount, BigDecimal bdTaxRate){
        BigDecimal bdTaxAmount = new BigDecimal(0.0);
        if( bdAmount.doubleValue()>0.0 ){
            bdTaxAmount = bdAmount.multiply( bdTaxRate.divide( new BigDecimal(100)) );
        }
        return bdTaxAmount;
    }
}
