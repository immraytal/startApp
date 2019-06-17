package startApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import startApp.entities.Order;
import startApp.entities.Product;
import startApp.entities.User;
import startApp.repository.UserRepository;
import startApp.service.OrderService;
import startApp.service.ProductService;
import startApp.service.UserService;

import java.util.ArrayList;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    ProductService productService;

    @Autowired
    UserService userService;

    @GetMapping
    public ModelAndView orderGet(@ModelAttribute("product") Product product) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("order");
        Product product1 = productService.getById(product.getId());
        modelAndView.addObject("prod", product1);
        return modelAndView;
    }

    @PostMapping
    public ModelAndView confirmOrder(@ModelAttribute("product") Product product) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("successorder");

        Product product1 = productService.getById(product.getId());
        modelAndView.addObject("product", product1);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUsername(auth.getName());

        Order order = orderService.findByUserId(user.getId());
        if (order==null) {
            order= new Order(1L, product1.getPrice(), "created", user, new ArrayList<Product>());
            order.getProducts().add(product1);
            System.out.println("new");
            orderService.saveOrder(order);
            return modelAndView;
        }
        System.out.println("old");
        order.getProducts().add(product1);
        order.setOrderPrice(order.getOrderPrice() + product1.getPrice());
        orderService.saveOrder(order);
        return modelAndView;
    }
}
