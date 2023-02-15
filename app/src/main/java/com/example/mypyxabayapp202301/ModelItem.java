package com.example.mypyxabayapp202301;

public class ModelItem {

    private String imageUrl;
    private String author;
    private int likes;

    public ModelItem() {
    }

    public ModelItem(String imageUrl, String author, int likes) {
        this.imageUrl = imageUrl;
        this.author = author;
        this.likes = likes;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getAuthor() {
        return author;
    }

    public int getLikes() {
        return likes;
    }
}
