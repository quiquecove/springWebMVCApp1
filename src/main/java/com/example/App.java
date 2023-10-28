package com.example;

import com.example.controller.ProductController;
import com.example.emtity.Product;
import com.example.repository.ProductRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext; // Import the correct ApplicationContext class

import java.util.List;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        // SpringApplication.run(App.class, args); // You should not need this line.
        ConfigurableApplicationContext context = SpringApplication.run(App.class, args); // Use ConfigurableApplicationContext
        var repository = context.getBean(ProductRepository.class);
    List<Product> products= List.of(
            new Product(null,"product1",5.99,1),
            new Product(null,"product1",5.99,1),
            new Product(null,"product1",5.99,1)

            );
        repository.saveAll(products);
    }
}
