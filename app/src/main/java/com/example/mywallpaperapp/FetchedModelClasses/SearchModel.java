package com.example.mywallpaperapp.FetchedModelClasses;

import com.example.mywallpaperapp.FetchedModelClasses.ImageModel;

import java.util.ArrayList;

public class SearchModel {
    private ArrayList<ImageModel> photos;

    public SearchModel(ArrayList<ImageModel> photos) {
        this.photos = photos;
    }

    public ArrayList<ImageModel> getPhotos() {
        return this.photos;
    }

    public void setPhotos(ArrayList<ImageModel> photos) {
        this.photos = photos;
    }
}
