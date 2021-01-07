package com.h5170043.muhammed_cagatay_mercan_final.model;

import com.google.gson.annotations.SerializedName;

public class Movie {
    @SerializedName("logoUrl")
    public String logoUrl;

    @SerializedName("imageUrl")
    public String imageUrl;

    @SerializedName("adi")
    public String name;

    @SerializedName("tarih")
    public String date;

    @SerializedName("konu")
    public String description;

    @SerializedName("htmlData")
    public String htmlData;
}
