package com.skytec.service.user;

import com.skytec.bean.Session;
import com.skytec.bean.User;
import com.skytec.repository.SessionsRepository;
import com.skytec.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private static final Long DEFAULT_HEALTH_LEVEL = 100L;
    private static final Long DEFAULT_DAMAGE_LEVEL = 10L;
    private static final Long DEFAULT_RATING = 100L;

    private static final String SUCCESSFULL_REGISTRATION = "Registration successfull";
    private static final String SUCCESSFULL_AUTH = "Auth successfull";
    private static final String INVALID_AUTH = "Неверный логин или пароль";

    private static final Long MEASUREMENT = 1000000000000000L;

    private List<Long> sessionsIds = new ArrayList<>();

    private final UserRepository repository;
    private final SessionsRepository sessionsRepository;

    @Autowired
    public UserServiceImpl(UserRepository repository, SessionsRepository sessionsRepository) {
        this.repository = repository;
        this.sessionsRepository = sessionsRepository;
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
            modelMap.put("labelResultValue", SUCCESSFULL_REGISTRATION);

            Long sessionIdValue = getSessionId();
            sessionsIds.add(sessionIdValue);

            modelMap.addAttribute(sessionIdValue);

            sessionsRepository.save(Session.builder()
                    .userId(repository.findByLogin(login).getId())
                    .sessionId(sessionIdValue)
                    .build());

            return "redirect:/main?login=" + login +
                    "&password=" + password +
                    "&long=" + sessionIdValue +
                    "&labelResultValue=" + SUCCESSFULL_REGISTRATION;//new ModelAndView("redirect:/main", modelMap);
        } else {
            if (user.getPassword().equals(password)) {
                modelMap.put("login", login);
                modelMap.put("labelResultValue", SUCCESSFULL_AUTH);

                Long sessionIdValue = getSessionId();
                sessionsIds.add(sessionIdValue);

                if (!checkSession(user)) {
                    sessionsRepository.save(Session.builder()
                            .userId(user.getId())
                            .sessionId(sessionIdValue)
                            .build());
                } else {
                    sessionsIds.remove(sessionsRepository.findByUserId(user.getId()));
                    sessionsRepository.delete(sessionsRepository.findByUserId(user.getId()));

                    sessionsRepository.save(Session.builder()
                            .userId(user.getId())
                            .sessionId(sessionIdValue)
                            .build());
                }

                modelMap.addAttribute(sessionIdValue);
                return "redirect:/main?login=" + login +
                        "&long=" + sessionIdValue +
                        "&labelResultValue=" + SUCCESSFULL_AUTH;//new ModelAndView("redirect:/main", modelMap);
            } else {
                modelMap.put("labelValue", INVALID_AUTH);
                return "forward:/index";//new ModelAndView("redirect:/index", modelMap);
            }
        }
    }

    @Override
    public ModelAndView isInfo(ModelMap modelMap) {
        return new ModelAndView("redirect:/info", modelMap);
    }

    private Long getSessionId() {
        Long value = (long) (Math.random() * MEASUREMENT);
        return (!sessionsIds.contains(value)) ? value : getSessionId();
    }

    private Boolean checkSession(User user) {
        return sessionsRepository.findByUserId(user.getId()) != null;
    }
}
