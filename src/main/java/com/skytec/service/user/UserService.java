package com.skytec.service.user;

import com.skytec.bean.User;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;


public interface UserService {

    Collection<User> findAll();

    String isAuth(String login, String password, ModelMap modelMap);

    ModelAndView isInfo(ModelMap modelMap);
}
