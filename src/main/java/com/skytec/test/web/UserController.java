package com.skytec.test.web;


import com.skytec.test.bean.User;
import com.skytec.test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

@RestController
@RequestMapping("/")
public class UserController extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/", "index");

        registry.addViewController("/index")
                .setViewName("/index");
    }

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<User>> showAll() {
        User user = new User();
        user.setLogin("john");
        user.setPassword("pass");
        userRepository.save(user);
        return new ResponseEntity<>
                (userRepository.findAll(), HttpStatus.OK);
    }
}
