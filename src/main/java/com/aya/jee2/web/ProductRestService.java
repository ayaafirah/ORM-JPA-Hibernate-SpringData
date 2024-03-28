package com.aya.jee2.web;

import com.aya.jee2.entities.Product;
import com.aya.jee2.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductRestService {
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/products")
    public List<Product> products(){
        return productRepository.findAll();
    }
    @GetMapping("/products/{id}")
    public Product findProduct(@PathVariable Long id){
        Product product = productRepository.findById(id).orElse(null);
        return product;
    }
}
