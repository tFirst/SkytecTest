package com.skytec.web;

import com.skytec.service.duel.DuelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
public class DuelController {

    private Long sessionId;

    private final DuelService duelService;

    @Autowired
    public DuelController(DuelService duelService) {
        this.duelService = duelService;
    }

    @GetMapping("duel")
    public String duel(@ModelAttribute("long") Long sessionId,
                       ModelMap modelMap) {
        this.sessionId = sessionId;
        return duelService.isDuel(sessionId, modelMap);
    }

    @RequestMapping("search")
    public String search(ModelMap modelMap) {
        return duelService.searchEnemy(sessionId, modelMap);
    }

    @RequestMapping("attack")
    public String attack(ModelMap modelMap) {
        return duelService.attack(sessionId, modelMap);
    }
}
