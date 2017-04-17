package org.songdan.base;

import org.junit.Before;
import org.songdan.customer.model.Customer;
import org.songdan.customer.service.ICustomerService;
import org.songdan.goods.model.Goods;
import org.songdan.goods.service.IGoodsService;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

/**
 * 测试没有任何事务的情况下多数据源的保存
 * @author Songdan
 * @date 2017/4/12 14:15
 */
public class IntegrationTestNoTx extends JunitBase {

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private IGoodsService goodsService;

    private Customer customer;

    private Goods goods;

    @Before
    public void init() {
        customer = new Customer();
        customer.setId("000002");
        customer.setName("校长");
        customer.setAddress("新东方厨师学校");
        goods = new Goods();
        goods.setId("g0000002");
        goods.setGoodsName("大刀");
        goods.setPrice(new BigDecimal("10.01"));
    }

    @org.junit.Test
    public void test(){
        customerService.saveCustomer(customer);
        goodsService.save(goods);
    }

}
