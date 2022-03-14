package com.sparta.team5finalproject.model;

public enum WatchCategoryEnum {

    DIGITAL("digital"),
    COUPLE("couple");

    final private String category;

    WatchCategoryEnum(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }
}
