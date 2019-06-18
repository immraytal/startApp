package startApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import startApp.entities.Product;
import startApp.repository.ProductRepository;
import startApp.service.ProductService;

import javax.validation.Valid;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping
    public ModelAndView createPageGet(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("product");
        Product product = new Product();
        modelAndView.addObject("product", product);
        return modelAndView;
    }

    @PostMapping
    public ModelAndView saveProduct(@Valid @ModelAttribute("product") Product product) {
        ModelAndView modelAndView = new ModelAndView();
        productService.saveProduct(product);
        modelAndView.setViewName("redirect:/catalog");
        return modelAndView;
    }
}
