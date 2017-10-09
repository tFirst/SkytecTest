package com.skytec.web;

import com.skytec.service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class InfoController {

    private ModelMap modelMap;

    private final InfoService infoService;

    @Autowired
    public InfoController(InfoService infoService) {
        this.infoService = infoService;
    }

    @GetMapping("info")
    public String info(@ModelAttribute("long") Long sessionId,
                        ModelMap model) {
        modelMap = model;
        return infoService.isInfo(sessionId, modelMap);
    }

    @PostMapping("duel")
    public ModelAndView duel() {
        return infoService.isWait(modelMap);
    }

    @RequestMapping(value = "stopSearch", params = "stopSearch")
    public String stopSearch() {
        return "info";
    }
}
