package startApp.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import startApp.entities.User;
import startApp.service.UserService;

@Controller
public class MainController {

    @Autowired
    UserService userService;

    @GetMapping
    @RequestMapping({"/","/main"})
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
}
