package startApp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
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
@RequestMapping("/admin")
public class AdminController{

    Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    UserService userService;

    @Autowired
    OrderService orderService;

    @Autowired
    ProductService productService;

    @GetMapping
    public ModelAndView adminGet() {
        ModelAndView modelAndView = new ModelAndView();
        List<User> users = userService.findAll();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User admin = userService.findByUsername(auth.getName());
        modelAndView.addObject("admin", admin);
        modelAndView.addObject("users", users);
        modelAndView.setViewName("admin");
        logger.info("Admin " + admin.getId() + " try get admin page");
        return modelAndView;
    }

    @PostMapping
    @Transactional
    public ModelAndView deleteUser(@Valid @ModelAttribute("user") User user) {
        userService.deleteByUsername(user.getUsername());
        return adminGet();
    }

    @PostMapping("/order")
    @Transactional
    public ModelAndView deleteOrder(@Valid @ModelAttribute("user") User user) {
        User target = userService.findByUsername(user.getUsername());
        Order order = orderService.findByUserId(target.getId());
        List<Product> products = order.getProducts();
        for(Product product: products){
            product.setInOrder(false);
        }
        orderService.deleteByUser(target);
logger.info("Order " + target.getId()+ " has been removed");
    return  adminGet();
    }
}
