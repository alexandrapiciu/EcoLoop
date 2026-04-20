package com.ecoloop.model;

// A tool listing. All fields are mock data - nothing is persisted to a DB.
public class Tool {
    private Long id;
    private String name;
    private String description;
    private String ownerName;
    private String location;
    private double pricePerDay;
    private String imageUrl;
    private Category category;
    private String availability;
    private double rating;
    private String reviewText;
    private ToolStatus status;

    public Tool() {}

    public Tool(Long id, String name, String description, String ownerName, String location,
                double pricePerDay, String imageUrl, Category category,
                String availability, double rating, String reviewText, ToolStatus status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.ownerName = ownerName;
        this.location = location;
        this.pricePerDay = pricePerDay;
        this.imageUrl = imageUrl;
        this.category = category;
        this.availability = availability;
        this.rating = rating;
        this.reviewText = reviewText;
        this.status = status;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getOwnerName() { return ownerName; }
    public void setOwnerName(String ownerName) { this.ownerName = ownerName; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public double getPricePerDay() { return pricePerDay; }
    public void setPricePerDay(double pricePerDay) { this.pricePerDay = pricePerDay; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }

    public String getAvailability() { return availability; }
    public void setAvailability(String availability) { this.availability = availability; }

    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }

    public String getReviewText() { return reviewText; }
    public void setReviewText(String reviewText) { this.reviewText = reviewText; }

    public ToolStatus getStatus() { return status; }
    public void setStatus(ToolStatus status) { this.status = status; }
}
