package com.ecoloop.service;

import com.ecoloop.model.Booking;
import com.ecoloop.model.RentalHistoryEntry;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

// Builds the rental history from the bookings list. This way, each booking
// the user makes automatically shows up in their history page.
@Service
public class RentalHistoryService {

    private final BookingService bookingService;

    public RentalHistoryService(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    public List<RentalHistoryEntry> findAll() {
        return bookingService.findAll().stream()
                .map(this::toEntry)
                .collect(Collectors.toList());
    }

    private RentalHistoryEntry toEntry(Booking b) {
        return new RentalHistoryEntry(b.getId(), b.getToolName(),
                b.getStartDate(), b.getEndDate(), b.getStatus());
    }
}
