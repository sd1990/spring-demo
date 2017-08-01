package org.songdan.shop;

/**
 * @author Songdan
 * @date 2017/4/12 15:19
 */
public interface IShopService {

    void shopping(String customerId,String goodsId);

    void shoppingError(String customerId,String goodsId);

}
