package com.skytec.service.info;

import com.skytec.bean.Duels;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;

public interface InfoService {

    ModelAndView isWait(ModelMap modelMap);

    String isInfo(Long sessionId, ModelMap modelMap);
}
