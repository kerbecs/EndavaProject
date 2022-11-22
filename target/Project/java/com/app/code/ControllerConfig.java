package com.app.code;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


@Controller
@Validated
public class ControllerConfig {
    AnnotationConfigApplicationContext config = new AnnotationConfigApplicationContext(appContext.class);

    @RequestMapping("/")
    public String showMainPage(){
        return "home";
    }

    @RequestMapping("/home")
    public String showMainPage2(){
        return "home";
    }

    @RequestMapping("/login")
    public String showLoginPage(Model model){
        User user = config.getBean("user",User.class);

        model.addAttribute("userTest",user);
        return "login";
    }

    @RequestMapping("/register")
    public String showRegisterPage(Model model){
        UserRegistration user = config.getBean("userRegistration",UserRegistration.class);

        model.addAttribute("registerTest",user);
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

    @RequestMapping(value="/loginCheck",method= RequestMethod.POST)
    public String showLogged(@Valid @ModelAttribute("userTest")
    User user, BindingResult bindingResult, Model model){

        if(bindingResult.hasErrors()){
            return "login";
        }
        else {
            return "homeLogged";
        }
    }
    @RequestMapping(value = "/registeredCheck", method = RequestMethod.POST)
    public String showRegistered(@Valid @ModelAttribute("registerTest")UserRegistration user, BindingResult bindingResult, Model model){
        System.out.println(user);
        if(bindingResult.hasErrors() || !user.checkPassword()){

            return "register";
        }
        else{
            return "registered";
        }
    }
}
