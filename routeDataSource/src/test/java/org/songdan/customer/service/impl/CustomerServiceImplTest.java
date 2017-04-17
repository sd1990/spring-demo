package org.songdan.customer.service.impl;

import org.junit.Test;
import org.songdan.base.JunitBase;
import org.songdan.customer.model.Customer;
import org.songdan.customer.service.ICustomerService;
import org.songdan.goods.service.IGoodsService;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

public class CustomerServiceImplTest extends JunitBase {

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private IGoodsService goodsService;

    @Test
    public void testSaveCustomer() throws Exception {
        Customer customer = new Customer();
        customer.setId("000004");
        customer.setName("songdan");
        customer.setAddress("北京市朝阳区");
        customer.setBalance(new BigDecimal(100));
        try {

            customerService.saveCustomer(customer);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


}
