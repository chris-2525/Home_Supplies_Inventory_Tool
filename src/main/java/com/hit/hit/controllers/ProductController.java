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

    @PostMapping("/editProduct/{id}")
    public String updateProduct(@PathVariable int id, @ModelAttribute Product product) {
        var existingProduct = productRepository.findProductById(id);

        existingProduct.setProductName(product.getProductName());
        existingProduct.setMinStock(product.getMinStock());
        existingProduct.setCurrentStock(product.getCurrentStock());

        productRepository.updateProduct(existingProduct);

        return "home.html";
    }

//    @GetMapping("/deleteProduct/{id}")
//    public String deleteProduct(Model model,@PathVariable int id) {
//        var deletedProduct = productRepository.deleteProductById(id);
//        model.addAttribute("product", deletedProduct);
//        return "product.html";
//    }

    @DeleteMapping("/deleteProduct/{id}")
    public String deleteProduct(Model model,@PathVariable int id) {
        productRepository.deleteProductById(id);
        return "redirect:/products";
    }




}
