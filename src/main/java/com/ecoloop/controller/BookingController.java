package com.ecoloop.controller;

import com.ecoloop.service.BookingService;
import com.ecoloop.service.UserService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

// Handles booking listing and creating new bookings from the tool details page.
@Controller
public class BookingController {

    private final BookingService bookingService;
    private final UserService userService;

    public BookingController(BookingService bookingService, UserService userService) {
        this.bookingService = bookingService;
        this.userService = userService;
    }

    // List all existing bookings.
    @GetMapping("/bookings")
    public String list(Model model) {
        model.addAttribute("bookings", bookingService.findAll());
        return "bookings";
    }

    // Creates a booking for a given tool, triggered by the details page form.
    @PostMapping("/tools/{id}/book")
    public String bookTool(@PathVariable Long id,
                           @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                           @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        String userName = userService.getCurrentUser().getName();
        bookingService.create(id, userName, startDate, endDate);
        return "redirect:/history";
    }
}
