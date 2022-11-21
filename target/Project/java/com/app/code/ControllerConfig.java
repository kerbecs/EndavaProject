package com.app.code;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ControllerConfig {

    @RequestMapping("/")
    public String showMainPage(){
        return "home";
    }

    @RequestMapping("/home")
    public String showMainPage2(){
        return "home";
    }

    @RequestMapping("/login")
    public String showLoginPage(){
        return "login";
    }

    @RequestMapping("/register")
    public String showRegisterPage(){
        return "register";
    }

    @RequestMapping("/aboutUs")
    public String showAboutUsPage(){
        return "about";
    }

    @RequestMapping("/order")
    public String showOrderPage(){
        return "order";
    }
    @RequestMapping("/profile")
    public String showProfile(){
        return "profile";
    }




    @RequestMapping("/aboutUsLogged")
    public String showAboutUsPageLogged(){
        return "aboutLogged";
    }

    @RequestMapping("/orderLogged")
    public String showOrderPageLogged(){
        return "orderLogged";
    }

    @RequestMapping("/homeLogged")
    public String mainPageLogged(){
        return "homeLogged";
    }
}
