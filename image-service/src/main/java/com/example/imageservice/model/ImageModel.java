package com.example.imageservice.model;

import lombok.Data;

@Data
public class ImageModel {

    int index;
    String url;

    public ImageModel(int index, String url){
        this.index = index;
        this.url = url;
    }

}
