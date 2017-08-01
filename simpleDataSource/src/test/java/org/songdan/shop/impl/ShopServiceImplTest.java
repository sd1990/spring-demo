package org.songdan.shop.impl;

import org.junit.Test;
import org.songdan.base.JunitBase;
import org.songdan.customer.model.Customer;
import org.songdan.customer.service.ICustomerService;
import org.songdan.goods.model.Goods;
import org.songdan.goods.service.IGoodsService;
import org.songdan.shop.IShopService;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

import static junit.framework.Assert.assertEquals;

public class ShopServiceImplTest extends JunitBase {

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private IGoodsService goodsService;

    @Autowired
    private IShopService shopService;

    @Test
    public void testShopping() throws Exception {
        String customerId = "000001";
        Customer customer = customerService.getCustomerById(customerId);
        String goodsId = "g0000002";
        Goods dbGoods = goodsService.getById(goodsId);
        Goods goods = new Goods();
        goods.setId(goodsId);
        shopService.shopping(customerId, goodsId);
        assertEquals(customer.getBalance().subtract(new BigDecimal(10)), customerService.getCustomerById(customerId)
                .getBalance());
        assertEquals(dbGoods.getStock() - 1, goodsService.getById(goodsId).getStock());
    }

    @Test
    public void testShoppingError() throws Exception {
        String customerId = "000001";
        Customer customer = customerService.getCustomerById(customerId);
        String goodsId = "g0000002";
        Goods dbGoods = goodsService.getById(goodsId);
        Goods goods = new Goods();
        goods.setId(goodsId);
        try {

            shopService.shoppingError(customerId, goodsId);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(customer.getBalance(), customerService.getCustomerById(customerId)
                .getBalance());
        assertEquals(dbGoods.getStock(), goodsService.getById(goodsId).getStock());
    }
}
