package com.hit.hit.controllers;

import com.hit.hit.model.Product;
import com.hit.hit.repositories.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


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

    @GetMapping("/")
    public String home() {
        return "home.html";
    }

    @GetMapping("/showProducts")
    public String viewProducts(Model model) {
        var products = productRepository.findAllProducts();
        model.addAttribute("products", products);
        return "product.html";
    }

    @GetMapping("/editProduct/{id}")
    public String editProduct(Model model,@PathVariable int id) {
        var product = productRepository.findProductById(id);
        model.addAttribute("product", product);
        return "editProduct.html";
    }

    @PutMapping("/product")
    public String updateProduct(Model model, @RequestBody Product product) {
        var updatedProduct = productRepository.updateProduct(product);
        model.addAttribute("product", updatedProduct);
        return "editProduct.html";
    }

    @DeleteMapping("/deleteProduct/{id}")
    public String deleteProduct(Model model,@PathVariable int id) {
        var deletedProduct = productRepository.deleteProductById(id);
        return "home.html";
    }




}
