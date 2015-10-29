package com.shine.volley.model;

/**
 * Created by Vinod Akkepalli
 */

public class Inventory {

    private static final String TAG = Inventory.class.getSimpleName();

    private String title, imageUrl;
    private double rating;
    private int nRatings;
    private double fPrice;

    public Inventory(){
    }

    public Inventory(String title, String imageUrl, int nRatings, double rating, double fPrice) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.rating = rating;
        this.nRatings = nRatings;
        this.fPrice = fPrice;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getnRatings() {
        return nRatings;
    }

    public void setnRatings(int nRatings) {
        this.nRatings = nRatings;
    }

    public double getfPrice() {
        return fPrice;
    }

    public void setfPrice(double fPrice) {
        this.fPrice = fPrice;
    }

}