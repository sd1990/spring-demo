package org.songdan.customer.service.impl;

import org.songdan.customer.dao.ICustomerDao;
import org.songdan.customer.model.Customer;
import org.songdan.customer.service.ICustomerService;
import org.songdan.goods.service.IGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Songdan
 * @date 2017/4/12 11:44
 */
@Service
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    private ICustomerDao customerDao;

    @Autowired
    private IGoodsService goodsService;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public Customer saveCustomer(Customer customer) {
        Customer dbCustomer = customerDao.insert(customer);
        return dbCustomer;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Customer getCustomerById(String customerId) {
        return customerDao.selectById(customerId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateBalanceById(Customer customer) {
        customerDao.updateBalanceById(customer);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateByCardNo(Customer customer) {
        customerDao.updateByCardNo(customer);
    }

}
