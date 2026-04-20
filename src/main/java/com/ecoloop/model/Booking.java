package com.ecoloop.model;

import java.time.LocalDate;

// Represents a booking request a user made for a tool.
public class Booking {
    private Long id;
    private Long toolId;
    private String toolName;
    private String userName;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status; // ACTIVE, COMPLETED, UPCOMING

    public Booking() {}

    public Booking(Long id, Long toolId, String toolName, String userName,
                   LocalDate startDate, LocalDate endDate, String status) {
        this.id = id;
        this.toolId = toolId;
        this.toolName = toolName;
        this.userName = userName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getToolId() { return toolId; }
    public void setToolId(Long toolId) { this.toolId = toolId; }

    public String getToolName() { return toolName; }
    public void setToolName(String toolName) { this.toolName = toolName; }

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
