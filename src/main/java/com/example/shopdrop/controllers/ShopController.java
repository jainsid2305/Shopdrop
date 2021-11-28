package com.example.shopdrop.controllers;

import com.example.shopdrop.models.Product;
import com.example.shopdrop.models.User;
import com.example.shopdrop.repository.ProductRepository;
import com.example.shopdrop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.List;

@Controller
public class ShopController {

    @Autowired
    ProductRepository productRepository;

    @GetMapping("/shop")
    public String shop(Model model){
        List<Product> products = productRepository.allProducts();
        model.addAttribute("products", products);
        return "shop";
    }


}
