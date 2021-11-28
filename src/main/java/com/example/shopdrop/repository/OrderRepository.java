package com.example.shopdrop.repository;

import com.example.shopdrop.models.*;
import com.example.shopdrop.services.SecurityService;
import com.example.shopdrop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
@Service
public class OrderRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    SecurityService securityService;

    @Autowired
    UserService userService;

    @Autowired
    ProductRepository productRepository;

    private RowMapper<Order> orderRowMapper = new RowMapper<Order>() {
        @Override
        public Order mapRow(ResultSet rs, int rowNum) throws SQLException {

            Order order = new Order();
            order.setOrderId(rs.getInt("orderId"));
            order.setAddress(rs.getString("address"));
            order.setAmt(rs.getInt("amount"));
            order.setUserId(rs.getInt("user_Id"));

            return order;
        }
    };

    private RowMapper<OrderItem> orderItemRowMapper = new RowMapper<OrderItem>() {
        @Override
        public OrderItem mapRow(ResultSet rs, int rowNum) throws SQLException {

            OrderItem orderItem = new OrderItem();
            orderItem.setOrderItemId(rs.getInt("orderItemId"));
            orderItem.setQuantity(rs.getInt("quantity"));
            orderItem.setProductId(rs.getInt("productId"));
            orderItem.setOrderId(rs.getInt("orderId"));

            return orderItem;
        }
    };

    @Autowired
    public OrderRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void saveOrder(Order order) {
        String query = "insert into orders(address,amount,user_Id) values(?,?,?)";
        //System.out.println(cart.getPassword());
        jdbcTemplate.update(query, order.getAddress(),order.getAmt(),order.getUserId());

    }

    public void saveOrderItem(OrderItem orderItem) {
        String query = "insert into orderitem(quantity,productId,orderId) values(?,?,?)";
        jdbcTemplate.update(query, orderItem.getQuantity(),orderItem.getProductId(),orderItem.getOrderId());

    }

    public int getLastAddedOrder() {
        String query = "select * from orders ORDER BY orderId DESC LIMIT 1;";
        Order order = jdbcTemplate.queryForObject(query,orderRowMapper);
        return order.getOrderId();

    }
}
