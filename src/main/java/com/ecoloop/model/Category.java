package com.ecoloop.model;

// Tool categories used across the app. Using an enum keeps the list consistent.
public enum Category {
    POWER_TOOLS("Power Tools"),
    GARDEN("Garden"),
    CLEANING("Cleaning"),
    LADDERS("Ladders"),
    HAND_TOOLS("Hand Tools"),
    OTHER("Other");

    private final String displayName;

    Category(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
