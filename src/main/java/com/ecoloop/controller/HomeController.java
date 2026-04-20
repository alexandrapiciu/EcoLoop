package com.ecoloop.controller;

import com.ecoloop.service.ToolService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

// Renders the landing / home page.
@Controller
public class HomeController {

    private final ToolService toolService;

    public HomeController(ToolService toolService) {
        this.toolService = toolService;
    }

    @GetMapping("/")
    public String home(Model model) {
        // Show a few featured tools on the home page hero section.
        model.addAttribute("featuredTools",
                toolService.findAll().stream().limit(3).toList());
        return "index";
    }
}
