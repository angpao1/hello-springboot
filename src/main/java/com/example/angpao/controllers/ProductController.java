package com.example.angpao.controllers;

import com.example.angpao.models.Product;
import com.example.angpao.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }

    @GetMapping
    public Iterable<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    public Product getProductById(@PathVariable String id) {
        return productRepository.findById(id).orElse(null);
    }

    @PutMapping(value = "/{id}")
    public Product updateProduct(@PathVariable String id, @RequestBody Product product) {
        Product productDetail = productRepository.findById(id).orElse(null);
        productDetail.setProductName(product.getProductName());
        productDetail.setCategory(product.getCategory());
        productDetail.setPrice(product.getPrice());
        return productRepository.save(productDetail);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteProduct(@PathVariable String id){
        productRepository.deleteById(id);
    }

    @DeleteMapping()
    public void deleteAllProducts() {
        productRepository.deleteAll();
    }

}
