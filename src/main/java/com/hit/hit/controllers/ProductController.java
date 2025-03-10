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

        return "redirect:/showProducts";
    }

    @GetMapping("/addProduct")
    public String addProduct(Model model) {
        model.addAttribute("product", new Product());
        return "addProduct.html";
    }

    @PostMapping("/addProduct")
    public String addProduct(@ModelAttribute Product product, Model model) {

        model.addAttribute("product", product);
        productRepository.storeProduct(product);

        return "redirect:/showProducts";
    }

    @GetMapping("/deleteProduct/{id}")
    public String deleteProduct(Model model, @PathVariable int id) {
        var product = productRepository.findProductById(id);
        model.addAttribute("product", product);
        return "deleteProduct.html";
    }

    @PostMapping("/deleteProduct/{id}")
    public String deleteProduct(@PathVariable int id, @ModelAttribute Product product) {
        var findProduct = productRepository.findProductById(id);
        if(findProduct != null) {
            productRepository.deleteProductById(id);
            System.out.println("Deleted product");
        }
        return "redirect:/showProducts";
    }

}
