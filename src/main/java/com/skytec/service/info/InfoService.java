package com.skytec.service.info;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

public interface InfoService {

    ModelAndView isWait(ModelMap modelMap);

    String isInfo(Long sessionId, ModelMap modelMap);
}
