package startApp.controller;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/login")
public class LoginController {

    @GetMapping
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");


        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if(!auth.getName().equals("anonymousUser")) {
                return new ModelAndView("redirect:/");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        return modelAndView;
    }
}