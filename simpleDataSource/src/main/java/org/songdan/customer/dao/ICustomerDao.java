package org.songdan.customer.dao;

import org.songdan.customer.model.Customer;

/**
 * @author Songdan
 * @date 2017/4/12 11:45
 */
public interface ICustomerDao {

    Customer insert(Customer customer);

    Customer selectById(String customerId);

    void updateBalanceById(Customer customer);

    void updateByCardNo(Customer customer);
}
