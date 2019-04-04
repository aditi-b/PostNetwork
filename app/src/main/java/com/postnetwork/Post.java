package com.postnetwork;

import com.google.gson.annotations.SerializedName;

class Post {

    private int userId;

    private int id;

    private String title;

    @SerializedName("body")
    private String text;

    Post(int userId, String title, String text) {
        this.userId = userId;
        this.title = title;
        this.text = text;
    }

    int getUserId() {
        return userId;
    }

    int getId() {
        return id;
    }

    String getTitle() {
        return title;
    }

    String getText() {
        return text;
    }
}
