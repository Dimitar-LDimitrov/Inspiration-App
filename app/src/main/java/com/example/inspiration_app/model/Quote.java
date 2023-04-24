package com.example.inspiration_app.model;

import android.graphics.drawable.Drawable;

public class Quote {
    private int id;
    private String author;
    private String quote;
    private String image;

    public Quote(int id, String author, String quote, String image) {
        this.id = id;
        this.author = author;
        this.quote = quote;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
