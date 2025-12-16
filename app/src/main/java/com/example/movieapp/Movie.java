package com.example.movieapp;

public class Movie {

    private String title;
    private String year;
    private int imageResId;
    private String category;
    private String rating;
    private String duration;
    private String description;

    public Movie(String title, String year, int imageResId,
                 String category, String rating,
                 String duration, String description) {

        this.title = title;
        this.year = year;
        this.imageResId = imageResId;
        this.category = category;
        this.rating = rating;
        this.duration = duration;
        this.description = description;
    }

    public String getTitle() { return title; }
    public String getYear() { return year; }
    public int getImageResId() { return imageResId; }
    public String getCategory() { return category; }
    public String getRating() { return rating; }
    public String getDuration() { return duration; }
    public String getDescription() { return description; }
}
