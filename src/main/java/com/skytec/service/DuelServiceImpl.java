package com.skytec.service;

import com.skytec.bean.ReadyToDuel;
import com.skytec.bean.Session;
import com.skytec.bean.User;
import com.skytec.repository.ReadyToDuelRepository;
import com.skytec.repository.SessionsRepository;
import com.skytec.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@Service
public class DuelServiceImpl implements DuelService {

    private ModelMap modelMap;

    private final SessionsRepository sessionsRepository;
    private final UserRepository userRepository;
    private final ReadyToDuelRepository readyToDuelRepository;

    @Autowired
    public DuelServiceImpl(SessionsRepository sessionsRepository, UserRepository userRepository,
                           ReadyToDuelRepository readyToDuelRepository) {
        this.sessionsRepository = sessionsRepository;
        this.userRepository = userRepository;
        this.readyToDuelRepository = readyToDuelRepository;
    }

    @Override
    public String isDuel(Long sessionId, ModelMap model) {
        modelMap = model;

        Session session = sessionsRepository.findBySessionId(sessionId);
        User user = userRepository.findById(session.getUserId());

        readyToDuelRepository.save(ReadyToDuel.builder()
                .userId(user.getId())
                .build());

        modelMap.put("yourLogin", user.getLogin());
        modelMap.put("yourHealth", user.getHealth());
        modelMap.put("yourDamage", user.getDamage());

        return "duel";
    }

    private ReadyToDuel getReadyToDuel(User user) {
        List<ReadyToDuel> listRTD = (List<ReadyToDuel>) readyToDuelRepository.findAll();
        Long[] arrayUserIdsRTD = new Long[listRTD.size()-1];
        if (arrayUserIdsRTD.length == 0) return null;

        for (int i = 0; i < listRTD.size(); i++) {
            if (!listRTD.get(i).getUserId().equals(user.getId())) {
                arrayUserIdsRTD[i] = listRTD.get(i).getUserId();
            } else {
                i--;
            }
        }
        Random random = new Random();
        Integer randomUserId = random.nextInt(arrayUserIdsRTD.length);
        return listRTD.get(randomUserId);
    }

    @Override
    public String searchEnemy(Long sessionId, ModelMap modelMap) {

        Session session = sessionsRepository.findBySessionId(sessionId);
        User user = userRepository.findById(session.getUserId());

        ReadyToDuel readyToDuelEnemy = getReadyToDuel(user);

        if (readyToDuelEnemy == null) {
            modelMap.put("labelWaitValue", "Соперник не найден. Возобновите поиск позже");
        } else {
            User userEnemy = userRepository.findById(readyToDuelEnemy.getUserId());
            modelMap.put("enemyLogin", userEnemy.getLogin());
            modelMap.put("enemyHealth", userEnemy.getHealth());
            modelMap.put("enemyDamage", userEnemy.getDamage());
            modelMap.put("labelWaitValue", "Соперник найден. До начала боя осталась 1 минута");
        }

        return "duel";
    }
}
