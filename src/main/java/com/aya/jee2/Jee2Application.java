package com.aya.jee2;

import com.aya.jee2.entities.Product;
import com.aya.jee2.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class Jee2Application implements CommandLineRunner {
   @Autowired
    private ProductRepository productRepository;
    public static void main(String[] args) {

        SpringApplication.run(Jee2Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //productRepository.save(new Product(null,"Computer",7600,8));
        //productRepository.save(new Product(null,"Printer",6400,6));
        //productRepository.save(new Product(null,"Phone",2400,43));
        List<Product> products = productRepository.findAll();
        products.forEach(p->{
            System.out.println(p.toString());
        });
        Product product=productRepository.findById(Long.valueOf(1)).get();
        System.out.println("*********");
        System.out.println(product.getId());
        System.out.println(product.getName());
        System.out.println(product.getQuantity());
        System.out.println("*********");
        System.out.println("-----------");
        List<Product> productList = productRepository.findByNameContains("C");
        productList.forEach(p->{
            System.out.println(p);
        });
        System.out.println("-----------");
        List<Product> productList2 = productRepository.search("%C%");
        productList2.forEach(p->{
            System.out.println(p);
        });
        System.out.println("-----------");
        List<Product> productList3 = productRepository.findByPriceGreaterThan(3000);
        productList3.forEach(p->{
            System.out.println(p);
        });
        System.out.println("-----------");
        List<Product> productList4 = productRepository.searchByPrice(3000);
        productList4.forEach(p->{
            System.out.println(p);
        });
    }
}
