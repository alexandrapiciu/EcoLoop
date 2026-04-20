package com.ecoloop.service;

import com.ecoloop.model.Booking;
import com.ecoloop.model.Tool;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

// Manages mock bookings. Each booking ties a username + tool + date range.
@Service
public class BookingService {

    private final List<Booking> bookings = new ArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(1);
    private final ToolService toolService;

    public BookingService(ToolService toolService) {
        this.toolService = toolService;
    }

    // Seed with a couple of example bookings so the bookings page isn't empty.
    @PostConstruct
    public void init() {
        LocalDate today = LocalDate.now();
        bookings.add(new Booking(idGenerator.getAndIncrement(), 1L, "Professional Power Drill",
                "Demo User", today.minusDays(3), today.minusDays(1), "COMPLETED"));
        bookings.add(new Booking(idGenerator.getAndIncrement(), 2L, "Gas-Powered Lawn Mower",
                "Demo User", today, today.plusDays(2), "ACTIVE"));
        bookings.add(new Booking(idGenerator.getAndIncrement(), 5L, "High-Pressure Washer",
                "Demo User", today.plusDays(5), today.plusDays(7), "UPCOMING"));
    }

    public List<Booking> findAll() {
        return bookings;
    }

    public Booking create(Long toolId, String userName, LocalDate startDate, LocalDate endDate) {
        Tool tool = toolService.findById(toolId).orElseThrow(
                () -> new IllegalArgumentException("Unknown tool id: " + toolId));
        String status = computeStatus(startDate, endDate);
        Booking booking = new Booking(idGenerator.getAndIncrement(), toolId, tool.getName(),
                userName, startDate, endDate, status);
        bookings.add(booking);
        return booking;
    }

    // Decides COMPLETED / ACTIVE / UPCOMING based on current date.
    private String computeStatus(LocalDate start, LocalDate end) {
        LocalDate today = LocalDate.now();
        if (end.isBefore(today)) return "COMPLETED";
        if (start.isAfter(today)) return "UPCOMING";
        return "ACTIVE";
    }
}
