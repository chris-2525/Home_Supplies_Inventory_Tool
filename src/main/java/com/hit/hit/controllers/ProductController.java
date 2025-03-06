package com.hit.hit.controllers;

import com.hit.hit.model.Product;
import com.hit.hit.repositories.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

//@RestController
//@RequestMapping("/product")
@Controller
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {this.productRepository = productRepository;}

//    @GetMapping
//    public List<Product> findProducts() {return productRepository.findAllProducts();}

//    @PostMapping
//    public void storeProduct(@RequestBody Product product) {productRepository.storeProduct(product);}

    @GetMapping("/showProducts")
    public String viewProducts(Model model) {
        var products = productRepository.findAllProducts();
        model.addAttribute("products", products);
        return "product.html";
    }


}
