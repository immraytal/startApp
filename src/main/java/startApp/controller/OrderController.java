package startApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import startApp.entities.Order;
import startApp.entities.Product;
import startApp.service.OrderService;
import startApp.service.ProductService;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    ProductService productService;

    @GetMapping("/{id}")
    public ModelAndView orderGet(@PathVariable("id")long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("order");
        Product product = productService.getById(id);
        modelAndView.addObject("prod", product);
        Order order = new Order();
        modelAndView.addObject("order" , order);
        return modelAndView;
    }

    @PostMapping("/{id}")
    public ModelAndView createOrder(@PathVariable("id") long id, @SessionAttribute(value = "id") long UserId, @ModelAttribute("order") Order order) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("successOrder");
        modelAndView.addObject("order", order);
        orderService.saveOrder(order);
        return modelAndView;
    }
}
