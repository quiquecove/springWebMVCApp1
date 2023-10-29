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
            new Product(null, "iPhone 15Pro", 999.99, 1),
            new Product(null, "Samsung Galaxy S23", 799.99, 1),
            new Product(null, "Sony PlayStation 5", 499.99, 1),
            new Product(null, "MacBook Air", 1199.99, 1),
            new Product(null, "Nike Air Max 270", 129.99, 1),
            new Product(null, "Samsung 4K QLED TV", 1499.99, 1),
            new Product(null, "Canon EOS 5D Mark IV", 2499.99, 1),
            new Product(null, "Sony WH-1000XM4 Wireless Headphones", 349.99, 1),
            new Product(null, "Apple AirPods Pro", 199.99, 1),
            new Product(null, "Dell XPS 13 Laptop", 1299.99, 1)
    );
        repository.saveAll(products);
    }
}
