package startApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import startApp.entities.Product;
import startApp.entities.User;
import startApp.service.OrderService;
import startApp.service.ProductService;
import startApp.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/catalog")
public class CatalogController {

    @Autowired
    ProductService productService;

    @Autowired
    UserService userService;




    @GetMapping
    public ModelAndView catalogGet(){
        ModelAndView modelAndView = new ModelAndView();
        List<Product> productsFromBase = productService.findAllProducts();
        List<Product> products = new ArrayList<>();
        for (Product prod: productsFromBase){
            if (!prod.isInOrder()) {
                products.add(prod);
            }
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        User user = userService.findByUsername(auth.getName());
        modelAndView.addObject("user", user);
        modelAndView.setViewName("catalog");
        modelAndView.addObject("products", products);
        return modelAndView;
    }

    @PostMapping
    public ModelAndView createOrder(@ModelAttribute("product") Product product) {
        System.out.println(product.getId());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("order");
        modelAndView.addObject("prod", productService.getById(product.getId()));
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        modelAndView.addObject("user",userService.findByUsername(auth.getName()));
        return modelAndView;
    }
}
