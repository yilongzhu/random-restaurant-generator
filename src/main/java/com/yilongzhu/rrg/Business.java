package com.yilongzhu.rrg;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Business {
    private String id;
    private String name;
    private String imageURL;
    private double rating;
    private String price;
    private String[] address = new String[7];

    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    @JsonProperty("image_url")
    public String getImageURL() {
        return this.imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public double getRating() {
        return this.rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getPrice() {
        return this.price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String[] getAddress() {
        return this.address;
    }

    public void setAddress(String[] address) {
        this.address = address;
    }

    @JsonProperty("location")
    private void unpackNested(Map<String,Object> location) {
        address[0] = (String) location.get("address1");
        address[1] = (String) location.get("address2");
        address[2] = (String) location.get("address3");
        address[3] = (String) location.get("city");
        address[4] = (String) location.get("zip_code");
        address[5] = (String) location.get("state");
        address[6] = (String) location.get("country");
    }
}