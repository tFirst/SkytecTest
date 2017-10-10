package com.skytec.service.info;

import com.skytec.bean.Session;
import com.skytec.bean.User;
import com.skytec.repository.SessionsRepository;
import com.skytec.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

@Service
public class InfoServiceImpl implements InfoService {

    private final UserRepository userRepository;
    private final SessionsRepository sessionsRepository;

    @Autowired
    public InfoServiceImpl(SessionsRepository sessionsRepository,
                           UserRepository userRepository) {
        this.sessionsRepository = sessionsRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ModelAndView isWait(ModelMap modelMap) {
        return new ModelAndView("redirect:/duel", modelMap);
    }

    @Override
    public String isInfo(Long sessionId, ModelMap modelMap) {
        Session session = sessionsRepository.findBySessionId(sessionId);
        User user = userRepository.findById(session.getUserId());

        modelMap.put("login", user.getLogin());
        modelMap.put("health", user.getHealth());
        modelMap.put("damage", user.getDamage());
        modelMap.put("rating", user.getRating());

        return "info";
    }
}
