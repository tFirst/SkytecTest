package com.skytec.service;

import com.skytec.bean.User;
import org.springframework.ui.ModelMap;

import java.util.Collection;

public interface UserService {
    Collection<User> findAll();

    String isAuth(String login, String password, ModelMap modelMap);
}
