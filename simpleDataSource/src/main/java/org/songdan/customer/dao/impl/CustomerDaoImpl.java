package org.songdan.customer.dao.impl;

import org.songdan.customer.dao.ICustomerDao;
import org.songdan.customer.model.Customer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @author Songdan
 * @date 2017/4/12 11:45
 */
@Repository
public class CustomerDaoImpl implements ICustomerDao {

    @Resource(name = "customerJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @Override
    public Customer insert(Customer customer) {
        String sql = "insert into customer(`id`,`card_no`,`name`,`address`,`balance`) VALUES (?,?,?,?,?)";
        Object[] paramers = new Object[] { customer.getId(), customer.getCardNo(), customer.getName(),
                customer.getAddress(), customer.getBalance() };
        jdbcTemplate.update(sql, paramers);
        return customer;
    }

    @Override
    public Customer selectById(String customerId) {
        String sql = "select * from customer where id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[] { customerId }, (rs, rowNum) -> {
            Customer customer = new Customer();
            customer.setId(rs.getString("id"));
            customer.setName(rs.getString("name"));
            customer.setAddress(rs.getString("address"));
            customer.setBalance(rs.getBigDecimal("balance"));
            return customer;
        });
    }

    @Override
    public void updateBalanceById(Customer customer) {
        String sql = "update customer set balance = ? where id = ?";
        Object[] paramers = new Object[] { customer.getBalance(), customer.getId() };
        jdbcTemplate.update(sql, paramers);
    }

    @Override
    public void updateByCardNo(Customer customer) {
        String sql = "update customer set balance = ? where card_no = ?";
        Object[] paramers = new Object[] { customer.getBalance(), customer.getCardNo() };
        jdbcTemplate.update(sql, paramers);
    }
}
