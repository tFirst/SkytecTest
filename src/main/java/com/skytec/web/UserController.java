package com.skytec.web;


import com.skytec.bean.User;
import com.skytec.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("index")
    public String index() {
        return "index";
    }

    @PostMapping("/save")
    public String save(@RequestParam("loginSpace") String loginSpace,
                              @RequestParam("passwordSpace") String passwordSpace,
                              ModelMap modelMap) {
        List<User> users = userRepository.findAll();
        HashMap<String, String> hashMapDatas = new HashMap<>();
        for (User userCurr : users) {
            hashMapDatas.put(userCurr.getLogin(), userCurr.getPassword());
        }
        if (hashMapDatas.containsKey(loginSpace)) {
            if (hashMapDatas.get(loginSpace).equals(passwordSpace)) {
                modelMap.put("loginSpace", loginSpace);
                modelMap.put("passwordSpace", passwordSpace);
                modelMap.put("labelResultValue", "Logined successfull");
                return "result";
            } else {
                modelMap.put("labelValue", "Invalid login or password!");
                return "index";
            }
        } else {
            User user = new User();
            user.setLogin(loginSpace);
            user.setPassword(passwordSpace);
            userRepository.save(user);
        }
        modelMap.put("loginSpace", loginSpace);
        modelMap.put("passwordSpace", passwordSpace);
        modelMap.put("labelResultValue", "Registration successfull");
        return "result";
    }
}
