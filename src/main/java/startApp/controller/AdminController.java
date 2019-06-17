package startApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import startApp.entities.User;
import startApp.service.UserService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController{

    @Autowired
    UserService userService;

    @GetMapping
    public ModelAndView adminGet() {
        ModelAndView modelAndView = new ModelAndView();
        List<User> users = userService.findAll();
        modelAndView.addObject("users", users);
        modelAndView.setViewName("admin");
        return modelAndView;
    }

    @PostMapping
    @Transactional
    public ModelAndView adminEdit(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) {
        userService.deleteByUsername(user.getUsername());
        return adminGet();
    }
}
