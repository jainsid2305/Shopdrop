package com.example.shopdrop.repository;

import com.example.shopdrop.models.Product;
import com.example.shopdrop.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@Service
public class ProductRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private RowMapper<Product> productRowMapper = new RowMapper<Product>() {
        @Override
        public Product mapRow(ResultSet rs, int rowNum) throws SQLException {

            Product product = new Product();
            product.setProductId(rs.getInt("productId"));
            product.setName(rs.getString("name"));
            product.setBrandName(rs.getString("brandName"));
            product.setSize(rs.getString("size"));
            product.setMrp(rs.getInt("mrp"));
            product.setSp(rs.getInt("sp"));
            product.setQuantityAvailable(rs.getInt("quantityAvailable"));
            return product;
        }
    };

    @Autowired
    public ProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Product> allProducts() {
        String query = "select * from product";
        return jdbcTemplate.query(query, productRowMapper);
    }

    public List<Product> searchedProducts(String searched) {
        String query = "select * from product where name Like ‘%${searched}%’";
        return jdbcTemplate.query(query, productRowMapper);
    }

    public Product findByProductId(int id) {
        String query = "select * from product where productId='" + id + "'";
        return jdbcTemplate.queryForObject(query, productRowMapper);
    }

    public void updateStock(int productId,int decrement){
        Product product = this.findByProductId(productId);
        int quantity = product.getQuantityAvailable() - decrement;
        String query = "update product set quantityAvailable='" + quantity + "' where productId='" + productId + "'";
        jdbcTemplate.update(query);
    }


}
