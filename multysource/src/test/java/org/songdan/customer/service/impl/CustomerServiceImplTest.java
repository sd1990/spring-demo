package org.songdan.customer.service.impl;

import org.junit.Test;
import org.songdan.base.JunitBase;
import org.songdan.customer.model.Customer;
import org.songdan.customer.service.ICustomerService;
import org.songdan.goods.model.Goods;
import org.songdan.goods.service.IGoodsService;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

import static junit.framework.Assert.assertEquals;

public class CustomerServiceImplTest extends JunitBase {

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private IGoodsService goodsService;

    @Test
    public void testSaveCustomer() throws Exception {
        Customer customer = new Customer();
        customer.setId("000001");
        customer.setName("songdan");
        customer.setAddress("北京市朝阳区");
        customerService.saveCustomer(customer);
    }


}
