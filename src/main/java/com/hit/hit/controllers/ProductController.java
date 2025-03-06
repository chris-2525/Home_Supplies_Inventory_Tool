package com.hit.hit.controllers;

import com.hit.hit.model.Product;
import com.hit.hit.repositories.ProductRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {this.productRepository = productRepository;}

    @GetMapping
    public List<Product> getProducts() {return productRepository.findAllProducts();}

    @PostMapping
    public void storeProduct(@RequestBody Product product) {productRepository.storeProduct(product);}


}
