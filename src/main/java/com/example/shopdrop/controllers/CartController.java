package com.example.shopdrop.controllers;

import com.example.shopdrop.models.*;
import com.example.shopdrop.repository.CartRepository;
import com.example.shopdrop.repository.OrderRepository;
import com.example.shopdrop.repository.ProductRepository;
import com.example.shopdrop.services.SecurityService;
import com.example.shopdrop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class CartController {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    SecurityService securityService;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserService userService;

    @Autowired
    OrderRepository orderRepository;

    @GetMapping("/cart")
    public String cart(Model model){

       //System.out.println("hbwjebsbchjsbcjkbjchbhjfdjfddjdhj");

        String loggedInUserName = securityService.findLoggedInUsername();
        User user = userService.findByUsername(loggedInUserName);

        List<Cart> cartOfCurrentUser = cartRepository.cartOfCurrentUser(user.getUserId());
        List<Product> productList = new ArrayList<Product>();
        List<Integer> quantityList = new ArrayList<Integer>();
        Integer amt=0;
        //System.out.println(amt);

        for (Cart cart : cartOfCurrentUser) {
            productList.add(productRepository.findByProductId(cart.getProductId()));
            quantityList.add(cart.getQuantity());
            amt=amt+cart.getQuantity()*productRepository.findByProductId(cart.getProductId()).getSp();
            //System.out.println(productRepository.findByProductId(cart.getProductId()));
            //System.out.println(cart.getQuantity());
            //System.out.println(amt);
        }


        Cart cart = new Cart();
        model.addAttribute("cartOfCurrentUser",cartOfCurrentUser);
        model.addAttribute("userId", user.getUserId());
        model.addAttribute("productList", productList);
        model.addAttribute("quantityList", quantityList);
        model.addAttribute("amt", amt);
        model.addAttribute("cart", cart);
        return "cart";
    }

    @PostMapping({"/cart"})
    public String addCart(@ModelAttribute("cart") Cart cart, BindingResult result, WebRequest request, Model model, RedirectAttributes attributes) {
        //System.out.println("hihihihihihii");

        cartRepository.save(cart);

        String loggedInUserName = securityService.findLoggedInUsername();
        User user = userService.findByUsername(loggedInUserName);

        List<Cart> cartOfCurrentUser = cartRepository.cartOfCurrentUser(user.getUserId());
        List<Product> productList = new ArrayList<Product>();
        List<Integer> quantityList = new ArrayList<Integer>();
        Integer amt=0;
        //System.out.println(amt);

        for (Cart tmpCart : cartOfCurrentUser) {
            productList.add(productRepository.findByProductId(tmpCart.getProductId()));
            quantityList.add(tmpCart.getQuantity());
            amt=amt+tmpCart.getQuantity()*productRepository.findByProductId(tmpCart.getProductId()).getSp();
            //System.out.println(productRepository.findByProductId(cart.getProductId()));
            //System.out.println(cart.getQuantity());
            //System.out.println(amt);
        }

        model.addAttribute("cartOfCurrentUser",cartOfCurrentUser);
        model.addAttribute("userId", user.getUserId());
        model.addAttribute("productList", productList);
        model.addAttribute("quantityList", quantityList);
        model.addAttribute("amt", amt);

        return "cart";
    }

    @PostMapping({"/orderPlaced"})
    public String placeOrder(@ModelAttribute("order") Order order, BindingResult result, WebRequest request, Model model, RedirectAttributes attributes){

        orderRepository.saveOrder(order);

        int orderId = orderRepository.getLastAddedOrder();

        String loggedInUserName = securityService.findLoggedInUsername();
        User user = userService.findByUsername(loggedInUserName);

        List<Cart> cartOfCurrentUser = cartRepository.cartOfCurrentUser(user.getUserId());

        for (Cart cart : cartOfCurrentUser) {
            System.out.println(cart.getQuantity());
            System.out.println(cart.getProductId());
            System.out.println(orderId);
            OrderItem orderItem1 = new OrderItem(1,cart.getQuantity(),cart.getProductId(),orderId);
            orderRepository.saveOrderItem(orderItem1);
            productRepository.updateStock(cart.getProductId(),cart.getQuantity());
        }
        cartRepository.deleteCart(user.getUserId());

        return "orderPlaced";
    }

    @PostMapping({"/remove"})
    public String remove(@ModelAttribute("id") int id, BindingResult result, WebRequest request, Model model, RedirectAttributes attributes){

        cartRepository.remove(id);
        String loggedInUserName = securityService.findLoggedInUsername();
        User user = userService.findByUsername(loggedInUserName);

        List<Cart> cartOfCurrentUser = cartRepository.cartOfCurrentUser(user.getUserId());

        //model.addAttribute("cartOfCurrentUser",cartOfCurrentUser);

        return "remove";
    }
}