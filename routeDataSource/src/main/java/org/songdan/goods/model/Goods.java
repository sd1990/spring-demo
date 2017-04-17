package org.songdan.goods.model;

import java.math.BigDecimal;

/**
 * @author Songdan
 * @date 2017/4/12 13:59
 */
public class Goods {

    private String id;

    private String goodsName;

    private BigDecimal price;

    private int stock;

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Goods{" + "goodsName='" + goodsName + '\'' + ", id='" + id + '\'' + ", price='" + price + '\'' + '}';
    }
}
