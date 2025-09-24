package com.example.mod4_act1.models;

public class PicsModel {
    int picLikes, pics;

    public PicsModel(int pics,int picLikes) {
        this.picLikes = picLikes;
        this.pics = pics;
    }

    public int getPicLikes() {
        return picLikes;
    }

    public void setPicLikes(int picLikes) {
        this.picLikes = picLikes;
    }

    public int getPics() {
        return pics;
    }

    public void setPics(int pics) {
        this.pics = pics;
    }
}
