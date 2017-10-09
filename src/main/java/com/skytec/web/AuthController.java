package com.skytec.web;


import com.skytec.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Controller
@SessionAttributes("sessionId")
public class AuthController extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/", "/index");

        registry.addViewController("/index")
                .setViewName("/index");
    }

    @Autowired
    private UserService userService;

    @GetMapping("index")
    public String index() {
        return "index";
    }

    @PostMapping("main")
    public ModelAndView main(@RequestParam("login") String login,
                             @RequestParam("password") String password,
                             ModelMap modelMap) {
        return userService.isAuth(login, password, modelMap);
    }
}
