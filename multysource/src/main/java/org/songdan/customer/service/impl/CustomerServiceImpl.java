package org.songdan.customer.service.impl;

import org.songdan.customer.dao.ICustomerDao;
import org.songdan.customer.model.Customer;
import org.songdan.customer.service.ICustomerService;
import org.songdan.goods.model.Goods;
import org.songdan.goods.service.IGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Customer saveCustomer(Customer customer) {
        return customerDao.insert(customer);
    }

    @Override
    public Customer getCustomerById(String customerId) {
        return customerDao.selectById(customerId);
    }

    @Override
    public void updateBalanceById(Customer customer) {
        customerDao.updateBalanceById(customer);
    }

}
