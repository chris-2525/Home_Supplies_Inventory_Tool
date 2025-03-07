package com.hit.hit.repositories;

import com.hit.hit.model.Product;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

@Repository
public class ProductRepository {

    private final JdbcTemplate jdbc;

    public ProductRepository(JdbcTemplate jdbc) {this.jdbc = jdbc;}

    public void storeProduct(Product product) {
        String sql = "INSERT INTO product VALUES (NULL, ?, ?, ?)";
        jdbc.update(sql, product.getProductName(), product.getMinStock(), product.getCurrentStock());
    }

    public List<Product> findAllProducts(){
        String sql = "SELECT * FROM product";

        RowMapper<Product> productRowMapper = (r, i) -> {
            Product rowObject = new Product();
            rowObject.setId(r.getInt("id"));
            rowObject.setProductName(r.getString("productName"));
            rowObject.setMinStock(r.getInt("minStock"));
            rowObject.setCurrentStock(r.getInt("currentStock"));
            return rowObject;

        };

        return jdbc.query(sql, productRowMapper);

    }

    //find product by id
    public Product findProductById(int productId) {
        String sql = "SELECT * FROM product WHERE id = ?";
        return jdbc.queryForObject(sql, BeanPropertyRowMapper.newInstance(Product.class), productId);
    }

    //update
    public int updateProduct(Product product) {
        String sql = "UPDATE product set id = ?, productName = ?, minStock = ?, currentStock = ? where id = ?";
        return jdbc.update(sql, product.getProductName(), product.getMinStock(), product.getCurrentStock(), product.getId());
    }

    //delete
    public int deleteProductById(int productId) {
        String sql = "DELETE FROM product WHERE id = ?";
        return jdbc.update(sql, productId);
    }


}
