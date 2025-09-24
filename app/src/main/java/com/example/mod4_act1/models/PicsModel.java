package com.example.mod4_act1.models;

public class PicsModel {

    int picLikes, pic;

    public PicsModel(int picLikes, int pic) {
        this.picLikes = picLikes;
        this.pic = pic;
    }

    public int getPicLikes() {
        return picLikes;
    }

    public void setPicLikes(int picLikes) {
        this.picLikes = picLikes;
    }

    public int getPic() {
        return pic;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }
}
