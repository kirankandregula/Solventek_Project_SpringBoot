package com.solventek.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.solventek.entity.Product;
import com.solventek.entity.User;
import com.solventek.repository.UserRepository;
import com.solventek.service.InsuranceService;


@RestController
@RequestMapping("/product/insurance")
public class InsuranceProductController {
	
	@Autowired
    private  InsuranceService insuranceService;
    
	@Autowired
    private UserRepository userRepository;
	
    @GetMapping("/get")
    public List<Product> getProducts() {
        return insuranceService.getProducts();
    }

    @PostMapping("/add")
    public Product addProduct(@RequestBody Product product) {
        return insuranceService.addProduct(product);
    }

    @GetMapping("/get/{id}")
    public Product getProductByid(@PathVariable Integer id) {
        return insuranceService.getProductByid(id);
    }

    @PutMapping("/update/{id}")
    public Product updateProduct(@PathVariable Integer id, @RequestBody Product product) {
        return insuranceService.updateProduct(product, id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProduct(@PathVariable Integer id) {
        insuranceService.deleteProduct(id);
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        List<User> users = userRepository.findAll();
        for (User u : users) {
            if (u.getUserName().equals(user.getUserName())) {
                if (verifyPassword(user.getPassWord(), u.getPassWord())) {
                    // Check the role of the authenticated user
                    switch (u.getRole()) {
                        case ADMIN:
                            return "Welcome to admin";
                        case MANAGER:
                            return "Welcome to manager";
                        case CUSTOMER:
                            return "Welcome to customer";
                        default:
                            return "Unknown role";
                    }
                } else {
                    return "Invalid password";
                }
            }
        }
        return "User not found";
    }

    // Method to verify password (for demonstration purposes)
    private boolean verifyPassword(String inputPassword, String storedPassword) {
        // Implement your logic to verify the password against the stored hashed password
        // For demonstration, we're assuming a simple comparison
        return inputPassword.equals(storedPassword);
    }

}
