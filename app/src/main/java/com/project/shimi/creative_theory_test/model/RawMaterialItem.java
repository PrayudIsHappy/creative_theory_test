package com.project.shimi.creative_theory_test.model;

import org.parceler.Parcel;

@Parcel
public class RawMaterialItem {
    String id;
    String name;
    String picture;
    long defaultPrice;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public long getDefaultPrice() {
        return defaultPrice;
    }

    public void setDefaultPrice(long defaultPrice) {
        this.defaultPrice = defaultPrice;
    }

}
