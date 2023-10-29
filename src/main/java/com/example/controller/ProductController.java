package com.example.controller;

import com.example.emtity.Product;
import com.example.repository.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/products")
public class ProductController {

    private ProductRepository repository;

    public ProductController(ProductRepository repository) {
        this.repository = repository;
    }

    /*
     *GET http://localhost:8080/products
     * */
    @GetMapping
    public String findAll(Model model) {
        //List<Product> products=this.repository.findAll();
        model.addAttribute("products", this.repository.findAll());
        return "product-list";
    }

    /*
     *POST http://localhost:8080/products/new
     * */
    @GetMapping("/newProduct")
    public String getForm(Model model) {
        List<Product> products=this.repository.findAll();
        model.addAttribute("products", new Product());
        return "product-form";
    }

    @PostMapping("/newProduct")
    public String save(@ModelAttribute("product") Product product) {
        this.repository.save(product);
        return "redirect:/products";
    }

    /*
     * GET http://localhost:8080/products/{id}/view
     */
    @GetMapping("/{id}/view")
    public String viewProduct(@PathVariable Long id, Model model) {
        Optional<Product> productOptional = repository.findById(id);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            model.addAttribute("product", product);
            return "product-view";
        } else {
            // Handle the case where the product is not found
            return "product-not-found"; // You should create a view for this case
        }
    }


    /*
     * GET http://localhost:8080/products/{id}/edit
     */
    @GetMapping("/{id}/edit")
    public String editProduct(@PathVariable Long id, Model model) {
        Optional<Product> productOptional = repository.findById(id);

        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            model.addAttribute("product", product);
            return "product-ed";
        } else {
            // Handle the case where the product is not found
            return "product-not-found"; // You should create a view for this case
        }
    }

    @PostMapping("/{id}/edit")
    public String updateProduct(@PathVariable Long id, @ModelAttribute Product updatedProduct) {
        // Find the existing product in the database
        Optional<Product> productOptional = repository.findById(id);

        if (productOptional.isPresent()) {
            Product existingProduct = productOptional.get();

            // Update the existing product with the new data
            existingProduct.setTitle(updatedProduct.getTitle());
            existingProduct.setPrice(updatedProduct.getPrice());
            existingProduct.setQuantity(updatedProduct.getQuantity());

            // Save the updated product to the database
            repository.save(existingProduct);

           // return "redirect:/products/{id}/view"; // Redirect to the product view page
            return "redirect:/products/{id}/view"; // Redirect to the product view page

        } else {
            // Handle the case where the product is not found
            return "product-not-found"; // You should create a view for this case
        }
    }

    /*
     * GET http://localhost:8080/products/{id}/delete
     */
    @GetMapping("/{id}/delete")
    public String deleteProduct(@PathVariable Long id) {
        this.repository.deleteById(id);
        return "redirect:/products";
    }

    /*
     * GET http://localhost:8080/products/deleteAll
     */
    @GetMapping("/deleteAll")
    public String deleteAllProducts() {
        this.repository.deleteAll();
        return "redirect:/products";
    }

    /*
     *GET http://localhost:8080/products/{id}/view
     * */



    /*
     *GET http://localhost:8080/products/{id}/edit
     * */


    /*
     *GET http://localhost:8080/products/{id}/delete
     * */



    /*
     *GET http://localhost:8080/products/{id}/deleteAll
     * */
}
