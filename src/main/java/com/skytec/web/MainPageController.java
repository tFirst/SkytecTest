package com.skytec.web;


import com.skytec.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainPageController {

    private ModelMap modelMap;

    private final UserService userService;

    @Autowired
    public MainPageController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("main")
    public String main(@RequestParam("login") String login,
                             @RequestParam("labelResultValue") String labelResultValue,
                             @ModelAttribute("long") Long sessionId,
                             ModelMap modelMap) {
        this.modelMap = new ModelMap();
        this.modelMap.addAttribute(sessionId);
        modelMap.put("login", login);
        modelMap.put("labelResultValue", labelResultValue);
        return "main";
    }

    @PostMapping("index")
    public String index(SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        return "index";
    }

    @PostMapping("info")
    public ModelAndView info() {
        return userService.isInfo(this.modelMap);
    }
}
