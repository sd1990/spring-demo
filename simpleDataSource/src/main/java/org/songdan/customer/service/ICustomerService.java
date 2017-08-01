package org.songdan.customer.service;

import org.songdan.customer.model.Customer;

/**
 * 顾客服务
 * @author Songdan
 * @date 2017/4/12 11:42
 */
public interface ICustomerService {

    Customer saveCustomer(Customer customer);

    Customer getCustomerById(String customerId);

    void updateBalanceById(Customer customer);

    void updateByCardNo(Customer customer);
}
