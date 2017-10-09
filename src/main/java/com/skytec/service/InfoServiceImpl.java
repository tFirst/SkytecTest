package com.skytec.service;

import com.skytec.bean.Duels;
import com.skytec.bean.Session;
import com.skytec.bean.User;
import com.skytec.repository.InfoRepository;
import com.skytec.repository.SessionsRepository;
import com.skytec.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;

@Service
public class InfoServiceImpl implements InfoService {

    private final UserRepository userRepository;
    private final InfoRepository infoRepository;
    private final SessionsRepository sessionsRepository;

    private static final String WAIT_STRING = "Подождите, идет поиск соперника...";

    @Autowired
    public InfoServiceImpl(InfoRepository infoRepository,
                           SessionsRepository sessionsRepository,
                           UserRepository userRepository) {
        this.infoRepository = infoRepository;
        this.sessionsRepository = sessionsRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Collection<Duels> findAll() {
        return infoRepository.findAll();
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
