package hu.unideb.inf.Controller;

import hu.unideb.inf.Entity.User;
import hu.unideb.inf.Service.OrdersService;
import hu.unideb.inf.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
public class UserController {
    private UserService userService;
    private OrdersService orderService;

    @Autowired
    public void setServices(UserService userService, OrdersService orderService) {
        this.userService = userService;
        this.orderService = orderService;
    }

    @RequestMapping("/profile")
    public String profile(Model model, Principal principal){
        String userName = principal.getName();
        model.addAttribute("user", userService.findUserByUserName(userName));
        model.addAttribute("orders", orderService.findOrdersByUserName(userName));
        return "profile";
    }

    @RequestMapping("/registration")
    public String registration(Model model){
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/reg")
    public String reg(@ModelAttribute User user) {
        String registerCheck = userService.registerUser(user);

        if (registerCheck.equals("ok"))
            return "auth/login";
        else
            return "redirect:/registration?alreadyExists";
    }
}