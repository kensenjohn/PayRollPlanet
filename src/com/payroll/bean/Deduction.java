package com.payroll.bean;

/**
 * Created by root on 8/27/15.
 */
public class Deduction {
    private Double amount = 0.0;
    private boolean isPreTax = false;

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public boolean isPreTax() {
        return isPreTax;
    }

    public void setPreTax(boolean isPreTax) {
        this.isPreTax = isPreTax;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Deduction{");
        sb.append("amount=").append(amount);
        sb.append(", isPreTax=").append(isPreTax);
        sb.append('}');
        return sb.toString();
    }
}
