package org.songdan.customer.service.impl;

import org.junit.Test;
import org.songdan.base.JunitBase;
import org.songdan.customer.model.Customer;
import org.songdan.customer.service.ICustomerService;
import org.songdan.goods.service.IGoodsService;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

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

    @Test
    public void testInitMillionData() {
        for (int i = 0; i < 1000; i++) {
            Customer customer = new Customer();
            customer.setId(Integer.toString(i));
            customer.setCardNo(Integer.toString(i));
            customer.setName("songdan");
            customer.setAddress("北京市朝阳区");
            customer.setBalance(new BigDecimal(100));
            customerService.saveCustomer(customer);
        }
    }

    @Test
    public void testConcurrentModify() throws InterruptedException {
        ThreadPoolExecutor executor =
                new ThreadPoolExecutor(8, 16, 5, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
        for (int i = 0; i < 1000; i++) {
            final int finalI = i;
            executor.execute(() -> {
                Customer customer = new Customer();
                customer.setCardNo(Integer.toString(finalI));
                customer.setBalance(new BigDecimal(new Random().nextInt(100)));
                customerService.updateByCardNo(customer);
            });
        }
        executor.shutdown();
        if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
            executor.shutdownNow();
        }
    }


}
