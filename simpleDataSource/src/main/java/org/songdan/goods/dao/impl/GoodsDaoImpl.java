package org.songdan.goods.dao.impl;

import org.songdan.goods.dao.IGoodsDao;
import org.songdan.goods.model.Goods;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @author Songdan
 * @date 2017/4/12 14:00
 */
@Repository
public class GoodsDaoImpl implements IGoodsDao {

    @Resource(name = "goodsJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @Override
    public Goods insert(Goods goods) {
        String sql = "insert into goods(`id`,`goods_name`,`price`) VALUES (?,?,?)";
        Object[] paramers = new Object[] { goods.getId(), goods.getGoodsName(), goods.getPrice() };
        jdbcTemplate.update(sql, paramers);
        return goods;
    }

    @Override
    public void decreaseGoodsStock(String goodsId) {
        String sql = "update goods set stock = stock -1 where id = ?";
        jdbcTemplate.update(sql, goodsId);
    }

    @Override
    public Goods selectByGoodsId(String goodsId) {
        String sql = "select * from goods where id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[] { goodsId }, (rs,rowNum)-> {
            Goods goods = new Goods();
            goods.setId(rs.getString("id"));
            goods.setGoodsName(rs.getString("goods_name"));
            goods.setPrice(rs.getBigDecimal("price"));
            goods.setStock(rs.getInt("stock"));
            return goods;
        });
    }
}
