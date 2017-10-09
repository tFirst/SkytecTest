package com.skytec.service;

import com.skytec.bean.Duels;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;

public interface InfoService {

    Collection<Duels> findAll();

    ModelAndView isWait(ModelMap modelMap);

    String isInfo(Long sessionId, ModelMap modelMap);
}
