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
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


@Controller
@Validated
@SessionAttributes(value = {"globalUser","infoUser","registerTest"})
public class ControllerConfig {
    AnnotationConfigApplicationContext config = new AnnotationConfigApplicationContext(appContext.class);

    @ModelAttribute("globalUser")
    public User globalUser(){
        User user = config.getBean("user",User.class);
        user.setCustomer(config.getBean("customer", Customer.class));
        return user;
    }
    @ModelAttribute("registerTest")
    public UserRegistration userRegistration(){
        return config.getBean("userRegistration",UserRegistration.class);
    }

    @RequestMapping("/")
    public String showMainPage(@ModelAttribute("globalUser")User user,Model model, @ModelAttribute("registerTest")UserRegistration userLogged){
        if(userLogged.isLogged()) {
            model.addAttribute("userLogged",user);
            return "homeLogged";
        }
        return "home";

    }

    @RequestMapping("/home")
    public String showMainPage2(@ModelAttribute("globalUser")User user,Model model, @ModelAttribute("registerTest")UserRegistration userLogged){
        if(userLogged.isLogged()) {
            model.addAttribute("userLogged",user);
            return "homeLogged";
        }
        return "home";
    }
    @RequestMapping("/login")
    public String showLoginPage(@ModelAttribute("globalUser")User user, Model model,@ModelAttribute("registerTest")UserRegistration userLogged){
        if(!userLogged.isLogged()) {
            model.addAttribute("userTest", user);
            return "login";
        }
        else
            return "home";
    }

    @RequestMapping("/register")
    public String showRegisterPage(@ModelAttribute("registerTest")UserRegistration userLogged,Model model){
        if(!userLogged.isLogged()) {
            model.addAttribute("registerTest", userLogged);
            return "register";
        }
        else
            return "home";
    }

    @RequestMapping("/about")
    public String showAboutUsPage(@ModelAttribute("registerTest")UserRegistration user){
        if(user.isLogged())
          return "aboutLogged";
        return "about";
    }


    @RequestMapping(value="/order",method = RequestMethod.GET)
    public ModelAndView showOrderLoggedPage(@ModelAttribute("globalUser")User user,Model model,@ModelAttribute("registerTest")UserRegistration userLogged){
        model.addAttribute("list",new doCommand());
        if(userLogged.isLogged()) {
            model.addAttribute("infoUser", user);
            return new ModelAndView("orderLogged", "products", HibernateClass.getAllProducts());
        }
        return new ModelAndView("order","products",HibernateClass.getAllProducts());
    }
    @RequestMapping("/profile")
    public String showProfile(@ModelAttribute("globalUser")User user,Model model,@ModelAttribute("registerTest")UserRegistration userLogged){
       if(!userLogged.isLogged())
           return "notLogged";

        user.setCustomer(HibernateClass.getCustomerDetail(user));
        UserRegistration userRegistration = new UserRegistration();
        userRegistration.setLogged(true);
        userRegistration.copy(user,user.getCustomer());
        System.out.println("Profile "+userLogged);

        model.addAttribute("infoUser",userRegistration);
        System.out.println("---------------------------- "+user);
        return "profile";
    }

    @RequestMapping(value="updateProfile",method = RequestMethod.POST)
    public ModelAndView updateProfile(@ModelAttribute("infoUser")UserRegistration infoUser,@ModelAttribute("globalUser")User globalUser,Model model){

        System.out.println("Update Profile11111111111 "+globalUser);
        System.out.println("Update Profile22222222222 "+infoUser);
        if(!infoUser.isLogged())
            return new ModelAndView("home");
        if(!HibernateClass.updateProfile(infoUser))
        {
            return new ModelAndView("profile", "error", "You have some mistakes in your profile information.");
        }
        else{
            model.addAttribute("infoUser", infoUser);
            return new ModelAndView("profile");
        }
    }

    @RequestMapping(value="/loginCheck",method= RequestMethod.POST)
    public ModelAndView showLogged(@Valid @ModelAttribute("userTest")
    User user,BindingResult bindingResult,ModelAndView modelAndView,@ModelAttribute("globalUser") User globalUser,  Model model,@ModelAttribute("registerTest")UserRegistration userRegistration){
        if(bindingResult.hasErrors() || !HibernateClass.searchUser(user)){
            user.setPassword("");
            return new ModelAndView("login","invalid","Invalid username or/and password");
        }
        else {
            globalUser.setPassword(user.getPassword());
            globalUser.setUsername(user.getUsername());
            model.addAttribute("User",globalUser);

            userRegistration.setLogged(true);

            return new ModelAndView("homeLogged");
        }
    }
    @RequestMapping(value = "/registeredCheck", method = RequestMethod.POST)
    public ModelAndView showRegistered(@Valid @ModelAttribute("registerTest")UserRegistration user,BindingResult bindingResult,@ModelAttribute("globalUser")User globalUser, Model model){
        if(bindingResult.hasErrors()){
            return new ModelAndView("register");
        }
        else if(!user.checkPassword()){
            return new ModelAndView("register","pass","The passwords doesn't match");
        }
        else if(HibernateClass.verifyUsernameExist(user)){
            return new ModelAndView("register","busy","This username is taken.");
        }
        else{
            UserRegistration.createUser(user);
            globalUser.setPassword(user.getPassword1());
            globalUser.setUsername(user.getUsername());
            return new ModelAndView("registered");
        }
    }

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logout(@ModelAttribute("registerTest")UserRegistration infoUser){
        infoUser.setLogged(false);
        return "home";
    }


    @RequestMapping("/validateOrder")
    public String validateOrder(@ModelAttribute("globalUser")User user,@ModelAttribute("list")doCommand list, Model model){
        HibernateClass.addCommand(list,user);
        return "thx";
    }
    @RequestMapping("/notLogged")
    public String notLogged(){
        return "notLogged";
    }
}
