package com.yilongzhu.rrg;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Search {
    private int total;
    private Business[] businesses;

    public Search() {
    }

    public int getTotal() {
        return this.total;
    }

    public Business[] getBusinesses() {
        return this.businesses;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setBusinesses(Business[] businesses) {
        this.businesses = businesses;
    }

    @Override
    public String toString() {
        return "Total number of results: " + this.total;
    }
}