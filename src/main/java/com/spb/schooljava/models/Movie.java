package com.spb.schooljava.models;

public class Movie {

    private int id;

    private String title;
    private int year;
    private String image;

    public Movie(int id, String title, int year, String image) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.image = image;
    }

    public Movie(int id, String title, int year) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.image = "default.jpg";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
