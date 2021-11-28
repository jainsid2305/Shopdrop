package com.example.shopdrop.repository;

import com.example.shopdrop.models.Cart;
import com.example.shopdrop.models.Product;
import com.example.shopdrop.models.User;
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
public class CartRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    SecurityService securityService;

    @Autowired
    UserService userService;

    @Autowired
    ProductRepository productRepository;

    private RowMapper<Cart> cartRowMapper = new RowMapper<Cart>() {
        @Override
        public Cart mapRow(ResultSet rs, int rowNum) throws SQLException {

            Cart cart = new Cart();
            cart.setCartItemId(rs.getInt("cartItemId"));
            cart.setQuantity(rs.getInt("quantity"));
            cart.setUserId(rs.getInt("userId"));
            cart.setProductId(rs.getInt("productId"));

            return cart;
        }
    };

    @Autowired
    public CartRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(Cart cart) {
        String query = "insert into cart(quantity,userId,productId) values(?,?,?)";
        //System.out.println(cart.getPassword());
        jdbcTemplate.update(query, cart.getQuantity(),cart.getUserId(),cart.getProductId());

    }

    public List<Cart> cartOfCurrentUser(int userId){
        String query = "select * from cart where userId = '" + userId + "'";
        List<Cart> cartOfCurrentUser = jdbcTemplate.query(query,cartRowMapper);
        return cartOfCurrentUser;

    }

    public void deleteCart(int userId){
        String query = "delete from cart where userId = '" + userId + "'";
        jdbcTemplate.update(query);
    }

    public void remove(int id){
        String query = "delete from cart where cartItemId = '" + id + "'";
        jdbcTemplate.update(query);
    }
}
