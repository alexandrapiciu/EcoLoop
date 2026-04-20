package com.ecoloop.model;

import java.time.LocalDate;

// Entry shown in a user's rental history page.
public class RentalHistoryEntry {
    private Long id;
    private String toolName;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status; // COMPLETED, ACTIVE, UPCOMING

    public RentalHistoryEntry() {}

    public RentalHistoryEntry(Long id, String toolName, LocalDate startDate, LocalDate endDate, String status) {
        this.id = id;
        this.toolName = toolName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getToolName() { return toolName; }
    public void setToolName(String toolName) { this.toolName = toolName; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
