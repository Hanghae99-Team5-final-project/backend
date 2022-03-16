package com.sparta.team5finalproject.model;

public enum WatchCategory {

    DIGITAL("digital"),
    COUPLE("couple");

    final private String category;

    WatchCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }
}