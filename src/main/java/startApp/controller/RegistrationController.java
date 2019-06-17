package startApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import startApp.entities.User;
import startApp.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ModelAndView registrationGet(){

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("registration");
        User user = new User();
        modelAndView.addObject("user", user);
        return modelAndView;
    }


    @PostMapping
    public ModelAndView createNewUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) {

        ModelAndView modelAndView = new ModelAndView();
        User userExists = userService.findByUsername(user.getUsername());

        if (userExists != null) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "There is already a user registered with the email provided");
        }

        if(!user.getPassword().equals(user.getMatchingPassword())){
            bindingResult
                    .rejectValue("matchingPassword", "error.user",
                            "Failed in Confirming password.");
        }

        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("registration");
        } else {
            userService.saveUser(user);
            modelAndView.setViewName("redirect:/login");
        }

        return modelAndView;
    }
}
