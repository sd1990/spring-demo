package org.songdan.customer.model;

import java.math.BigDecimal;

/**
 * 顾客类
 * 
 * @author Songdan
 * @date 2017/4/12 11:46
 */
public class Customer {

    private String id;

    private String cardNo;

    private String name;

    private String address;

    private BigDecimal balance;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    @Override
    public String toString() {
        return "Customer{" + "address='" + address + '\'' + ", id='" + id + '\'' + ", name='" + name + '\'' + '}';
    }
}
