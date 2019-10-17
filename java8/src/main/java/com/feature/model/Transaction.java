package com.feature.model;

public class Transaction {
    /**
     * 货币
     */
    private String currency;

    /**
     * 金额
     */
    private int price;

    public Transaction(String currency, int price) {
        this.currency = currency;
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "currency='" + currency + '\'' +
                ", price=" + price +
                '}';
    }
}
