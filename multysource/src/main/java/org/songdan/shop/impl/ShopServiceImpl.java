package org.songdan.shop.impl;

import org.songdan.customer.model.Customer;
import org.songdan.customer.service.ICustomerService;
import org.songdan.goods.model.Goods;
import org.songdan.goods.service.IGoodsService;
import org.songdan.shop.IShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Songdan
 * @date 2017/4/12 15:20
 */
@Service
public class ShopServiceImpl implements IShopService {

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private IGoodsService goodsService;

    @Override
    @Transactional
    public void shopping(String customerId, String goodsId) {
        Customer customer = customerService.getCustomerById(customerId);
        Goods goods = goodsService.getById(goodsId);
        // 用户扣余额
        customer.setBalance(customer.getBalance().subtract(goods.getPrice()));
        customerService.updateBalanceById(customer);
        // 商品扣库存
        goodsService.decreaseGoodsStock(goods.getId());
    }

    @Override
    @Transactional
    public void shoppingError(String customerId, String goodsId) {
        Customer customer = customerService.getCustomerById(customerId);
        Goods goods = goodsService.getById(goodsId);
        // 用户扣余额
        customer.setBalance(customer.getBalance().subtract(goods.getPrice()));
        customerService.updateBalanceById(customer);
        // 商品扣库存
        goodsService.decreaseGoodsStock(goods.getId());
        //在此处抛出异常
        throw new RuntimeException("故意抛出异常，来测试事务回滚");
    }
}
