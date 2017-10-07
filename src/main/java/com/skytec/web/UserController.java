package com.skytec.web;


import com.skytec.bean.User;
import com.skytec.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class UserController {

    private static final Long DEFAULT_HEALTH_LEVEL = 100L;
    private static final Long DEFAULT_DAMAGE_LEVEL = 10L;
    private static final Long DEFAULT_RATING = 100L;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("index")
    public String index() {
        return "index";
    }

    @PostMapping("/save")
    public String save(@RequestParam("login") String login,
                              @RequestParam("password") String password,
                              ModelMap modelMap) {
        Collection<User> users = userRepository.findAll();
        for (User userCurr : users) {
            if (userCurr.getLogin().equals(login)) {
                if (userCurr.getPassword().equals(password)) {
                    modelMap.put("login", login);
                    modelMap.put("password", password);
                    modelMap.put("health", userCurr.getHealth());
                    modelMap.put("damage", userCurr.getDamage());
                    modelMap.put("rating", userCurr.getRating());
                    modelMap.put("labelResultValue", "Авторизация прошла успешно");
                    return "result";
                } else {
                    modelMap.put("labelValue", "Неправильный логин или пароль!");
                    return "index";
                }
            } else {
                User user = new User();
                user.setLogin(login);
                user.setPassword(password);
                user.setHealth(DEFAULT_HEALTH_LEVEL);
                user.setDamage(DEFAULT_DAMAGE_LEVEL);
                user.setRating(DEFAULT_RATING);
                userRepository.save(user);
            }
        }

        modelMap.put("login", login);
        modelMap.put("password", password);
        modelMap.put("health", DEFAULT_HEALTH_LEVEL);
        modelMap.put("damage", DEFAULT_DAMAGE_LEVEL);
        modelMap.put("rating", DEFAULT_RATING);
        modelMap.put("labelResultValue", "Регистрация прошла успешно");
        return "result";
    }
}
