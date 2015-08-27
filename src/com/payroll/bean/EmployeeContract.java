package com.payroll.bean;

import java.util.ArrayList;

/**
 * Created by root on 8/27/15.
 */
public class EmployeeContract {

    private String firstName = "";
    private String lastName = "";
    private Double grossAmount = 0.0;
    private Double federalTaxRate = 0.0;
    private Double stateTaxRate = 0.0;

    private ArrayList<Deduction> deductions = new ArrayList<Deduction>();

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Double getGrossAmount() {
        return grossAmount;
    }

    public void setGrossAmount(Double grossAmount) {
        this.grossAmount = grossAmount;
    }

    public Double getFederalTaxRate() {
        return federalTaxRate;
    }

    public void setFederalTaxRate(Double federalTaxRate) {
        this.federalTaxRate = federalTaxRate;
    }

    public Double getStateTaxRate() {
        return stateTaxRate;
    }

    public void setStateTaxRate(Double stateTaxRate) {
        this.stateTaxRate = stateTaxRate;
    }

    public ArrayList<Deduction> getDeductions() {
        return deductions;
    }

    public void setDeductions(ArrayList<Deduction> deductions) {
        this.deductions = deductions;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("EmployeeContract{");
        sb.append("firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", grossAmount=").append(grossAmount);
        sb.append(", federalTaxRate=").append(federalTaxRate);
        sb.append(", stateTaxRate=").append(stateTaxRate);
        sb.append(", deductions=").append(deductions);
        sb.append('}');
        return sb.toString();
    }
}
