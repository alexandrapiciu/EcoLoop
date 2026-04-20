package com.ecoloop.controller;

import com.ecoloop.service.RentalHistoryService;
import com.ecoloop.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

// Handles user-related pages such as the rental history.
@Controller
public class UserController {

    private final RentalHistoryService rentalHistoryService;
    private final UserService userService;

    public UserController(RentalHistoryService rentalHistoryService, UserService userService) {
        this.rentalHistoryService = rentalHistoryService;
        this.userService = userService;
    }

    @GetMapping("/history")
    public String history(Model model) {
        model.addAttribute("history", rentalHistoryService.findAll());
        model.addAttribute("user", userService.getCurrentUser());
        return "history";
    }
}
