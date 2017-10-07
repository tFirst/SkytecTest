package com.skytec.service;

import com.skytec.bean.User;
import com.skytec.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.Collection;

@Service
public class UserServiceImpl implements UserService {

    private static final Long DEFAULT_HEALTH_LEVEL = 100L;
    private static final Long DEFAULT_DAMAGE_LEVEL = 10L;
    private static final Long DEFAULT_RATING = 100L;

    private static final String SUCCESSFULL_REGISTRATION = "Регистрация прошла успешно";
    private static final String SUCCESSFULL_AUTH = "Авторизация прошла успешно";
    private static final String INVALID_AUTH = "Неверный логин или пароль";

    private final UserRepository repository;

    @Autowired
    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public Collection<User> findAll() {
        return repository.findAll();
    }

    @Override
    public String isAuth(String login, String password, ModelMap modelMap) {
        User user = repository.findByLogin(login);
        if (user == null) {
            repository.save(User.builder()
                    .login(login)
                    .password(password)
                    .damage(DEFAULT_DAMAGE_LEVEL)
                    .health(DEFAULT_HEALTH_LEVEL)
                    .rating(DEFAULT_RATING)
                    .build());
            modelMap.put("login", login);
            modelMap.put("password", password);
            modelMap.put("health", DEFAULT_HEALTH_LEVEL);
            modelMap.put("damage", DEFAULT_DAMAGE_LEVEL);
            modelMap.put("rating", DEFAULT_RATING);
            modelMap.put("labelResultValue", SUCCESSFULL_REGISTRATION);
            return "result";
        } else {
            if (user.getPassword().equals(password)) {
                modelMap.put("login", login);
                modelMap.put("password", password);
                modelMap.put("health", user.getHealth());
                modelMap.put("damage", user.getDamage());
                modelMap.put("rating", user.getRating());
                modelMap.put("labelResultValue", SUCCESSFULL_AUTH);
                return "result";
            } else {
                modelMap.put("labelValue", INVALID_AUTH);
                return "index";
            }
        }
    }
}
