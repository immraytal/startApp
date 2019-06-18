package startApp.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import startApp.entities.Order;
import startApp.entities.Product;
import startApp.entities.User;
import startApp.service.OrderService;
import startApp.service.ProductService;
import startApp.service.UserService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping({"/","/main"})
public class MainController {

    @Autowired
    UserService userService;

    @Autowired
    OrderService orderService;

    @Autowired
    ProductService productService;

    @GetMapping
    public ModelAndView main(){
        ModelAndView modelAndView = new ModelAndView();
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User user = userService.findByUsername(auth.getName());
            modelAndView.addObject("user", user);
        } catch (Exception e){
            e.printStackTrace();
        }
        modelAndView.setViewName("main");
        return modelAndView;
    }

    @PostMapping
    @Transactional
    public ModelAndView deleteOrderByUser(@Valid @ModelAttribute("user") User user){
        User target = userService.findByUsername(user.getUsername());
        Order order = orderService.findByUserId(target.getId());

        List<Product> products = order.getProducts();
        for(Product product: products){
            product.setInOrder(false);
        }

        orderService.deleteByUser(target);
        return main();
    }

}
