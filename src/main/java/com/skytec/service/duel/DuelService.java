package com.skytec.service.duel;


import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

public interface DuelService {
    String isDuel(Long sessionId, ModelMap modelMap);

    String searchEnemy(Long sessionId, ModelMap modelMap);

    String attack(Long sessionId, ModelMap modelMap);
}
