package com.example.coco_spring.Entity;

public class Payment {
    public enum Currency{
        usd, eur;
    }

    private int amount;
    private Currency currency;
    private String paymentMethodToken;
    public String getDescription() {
        return "aaa";
    }
    public int getAmount() {
        return amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }
    public Currency getCurrency() {
        return currency;
    }
    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public String getPaymentMethodToken() {
        return paymentMethodToken;
    }

    public void setPaymentMethodToken(String paymentMethodToken) {
        this.paymentMethodToken = paymentMethodToken;
    }

}

