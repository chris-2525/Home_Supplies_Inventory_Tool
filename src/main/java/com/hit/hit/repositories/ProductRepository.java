package com.hit.hit.repositories;

import com.hit.hit.model.Product;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;


@ResponseBody
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
            rowObject.setProductName(r.getString("name"));
            rowObject.setMinStock(r.getInt("min_stock"));
            rowObject.setCurrentStock(r.getInt("current_stock"));
            return rowObject;

        };

        return jdbc.query(sql, productRowMapper);

    }


}
