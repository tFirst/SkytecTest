package com.skytec.web;

import com.skytec.service.DuelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class DuelController extends HttpServlet {

    private Long sessionId;
    private ModelMap modelMap;

    private final DuelService duelService;

    @Autowired
    public DuelController(DuelService duelService) {
        this.duelService = duelService;
    }

    @GetMapping("duel")
    public String duel(@ModelAttribute("long") Long sessionId,
                       ModelMap model) {
        modelMap = model;
        this.sessionId = sessionId;
        return duelService.isDuel(sessionId, modelMap);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        duelService.searchEnemy(sessionId, modelMap);
        response.sendRedirect(request.getContextPath() + "/duel");
        //return duelService.searchEnemy(sessionId, modelMap);
    }
}
