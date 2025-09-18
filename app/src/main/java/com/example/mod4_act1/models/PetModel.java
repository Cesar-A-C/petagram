package com.example.mod4_act1.models;

import java.io.Serializable;

public class PetModel{
    private String petName;
    private int petPicture, petLikes;
    private Boolean favorite;



    public PetModel(String petName, int petPicture, Boolean favorite, int petLikes){
        this.petName = petName;
        this.petPicture = petPicture;
        this.favorite = favorite;
        this.petLikes = petLikes;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public int getPetPicture() {
        return petPicture;
    }

    public void setPetPicture(int petPicture) {
        this.petPicture = petPicture;
    }

    public Boolean getFavorite() {
        return favorite;
    }

    public void setFavorite(Boolean favorite) {
        this.favorite = favorite;
    }

    public int getPetLikes() {
        return petLikes;
    }

    public void setPetLikes(int petLikes) {
        this.petLikes = petLikes;
    }
}
