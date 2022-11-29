package com.app.code;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import javax.validation.Valid;


@Controller
@Validated
@SessionAttributes(value = {"globalUser","infoUser","registerTest"})
public class ControllerConfig {
    private AnnotationConfigApplicationContext config = new AnnotationConfigApplicationContext(appContext.class);
    private final Logger logger = LoggerFactory.getLogger(ControllerConfig.class);

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
        else {
            return "home";
        }
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
        model.addAttribute("list",config.getBean("doANewCommand",doCommand.class));
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
        UserRegistration userRegistration = config.getBean("userRegistration",UserRegistration.class);
        userRegistration.setLogged(true);
        userRegistration.copy(user,user.getCustomer());

        model.addAttribute("infoUser",userRegistration);
        return "profile";
    }

    @RequestMapping(value="updateProfile",method = RequestMethod.POST)
    public ModelAndView updateProfile(@ModelAttribute("infoUser")UserRegistration infoUser,@ModelAttribute("globalUser")User globalUser,Model model){

        if(!infoUser.isLogged())
            return new ModelAndView("home");
        if(!HibernateClass.updateProfile(infoUser))
        {
            logger.debug("Can't modify profile of user "+globalUser.getUsername());
            return new ModelAndView("profile", "error", "You have some mistakes in your profile information.");
        }
        else{
            model.addAttribute("infoUser", infoUser);
            logger.debug("User profile for user "+globalUser.getUsername()+" was successfully edited.");
            return new ModelAndView("profile");
        }
    }

    @RequestMapping(value="/loginCheck",method= RequestMethod.POST)
    public ModelAndView showLogged(@Valid @ModelAttribute("userTest")
    User user,BindingResult bindingResult,ModelAndView modelAndView,@ModelAttribute("globalUser") User globalUser,  Model model,@ModelAttribute("registerTest")UserRegistration userRegistration){
        if(bindingResult.hasErrors() || !HibernateClass.searchUser(user)){
            user.setPassword("");
            logger.debug("Fail Logging. Invalid user or password for username "+user.getUsername());
            return new ModelAndView("login","invalid","Invalid username or/and password");
        }
        else {
            globalUser.setPassword(user.getPassword());
            globalUser.setUsername(user.getUsername());
            model.addAttribute("User",globalUser);

            userRegistration.setLogged(true);
            logger.debug("User "+user.getUsername()+" was successfully logged.");

            return new ModelAndView("homeLogged");
        }
    }
    @RequestMapping(value = "/registeredCheck", method = RequestMethod.POST)
    public ModelAndView showRegistered(@Valid @ModelAttribute("registerTest")UserRegistration user,BindingResult bindingResult,@ModelAttribute("globalUser")User globalUser, Model model){
        if(bindingResult.hasErrors()){
            logger.debug("Registration failed.");
            return new ModelAndView("register");
        }
        else if(!user.checkPassword()){
            return new ModelAndView("register","pass","The passwords doesn't match");
        }
        else if(HibernateClass.verifyUsernameExist(user)){
            logger.debug("A user with username "+user.getUsername()+" already exists.");
            return new ModelAndView("register","busy","This username is taken.");
        }
        else{
            UserRegistration.createUser(user);
            globalUser.setPassword(user.getPassword1());
            globalUser.setUsername(user.getUsername());
            logger.debug("User with username "+user.getUsername()+" was successfully registered.");
            return new ModelAndView("registered");
        }
    }

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logout(@ModelAttribute("registerTest")UserRegistration infoUser){
        infoUser.setLogged(false);
        logger.debug("User "+infoUser.getUsername()+" was successfully disconnected.");
        return "home";
    }


    @RequestMapping("/validateOrder")
    public String validateOrder(@ModelAttribute("globalUser")User user,@ModelAttribute("list")doCommand list, Model model){
        HibernateClass.addCommand(list,user);
        logger.debug("User "+user.getUsername()+" successfully made an order");
        return "thx";
    }
    @RequestMapping("/notLogged")
    public String notLogged(){
        return "notLogged";
    }
}
