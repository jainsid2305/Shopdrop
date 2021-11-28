package com.example.shopdrop.controllers;

        import com.example.shopdrop.models.Cart;
        import com.example.shopdrop.models.Product;
        import com.example.shopdrop.models.User;
        import com.example.shopdrop.repository.CartRepository;
        import com.example.shopdrop.repository.ProductRepository;
        import com.example.shopdrop.repository.UserRepository;
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

@Controller
public class ShopSingleController{

    @Autowired
    ProductRepository productRepository;

    @Autowired
    SecurityService securityService;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    UserService userService;

    @GetMapping("/shop/{id}")
    public String shopSingle(@PathVariable int id,Model model){
        //model.addAttribute("user", new User());
        String loggedInUserName = securityService.findLoggedInUsername();
        if(loggedInUserName==null){
            return "redirect:/login";
        }
        User user = userService.findByUsername(loggedInUserName);
        System.out.println(user.getUserId());
        Product product = productRepository.findByProductId(id);
        System.out.println(product.getProductId());
        //model.addAttribute("product",product);
        model.addAttribute("currentUser",user);
        model.addAttribute("product",product);
        String msg = "Item has been added to your Cart";
        model.addAttribute("msg",msg);
        return "shop-single";
    }

    /*@PostMapping({"/shop/{id}"})
    public String shopSingle(@PathVariable String id, @ModelAttribute("cart") Cart cart, BindingResult result, WebRequest request, Model model, RedirectAttributes attributes) {

        System.out.println(cart.getQuantity());
        System.out.println(cart.getUserId());
        System.out.println(cart.getProductId());
        cartRepository.save(cart);
        System.out.println(cart.getUserId());
        System.out.println(cart.getProductId());
        return "shop-single";
    }*/


}
