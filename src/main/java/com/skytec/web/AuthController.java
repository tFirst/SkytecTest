package com.skytec.web;


import com.skytec.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@RestController
@SessionAttributes("sessionId")
public class AuthController extends WebMvcConfigurerAdapter {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/", "/index");

        registry.addViewController("/index")
                .setViewName("/index");
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index() {
        return new ModelAndView("index");
    }

    @RequestMapping(value = "/main", method = RequestMethod.POST)
    public ModelAndView main(@RequestParam("login") String login,
                               @RequestParam("password") String password,
                               ModelMap modelMap) {
        return new ModelAndView(userService.isAuth(login, password, modelMap));
    }
}
