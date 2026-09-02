package com.radek;

public class Auto {
    private String spz;
    private String model;


    public Auto(String spz, String model) {
        this.spz =spz;
        this.model = model;
    }

    public Auto(String spz) {
        this(spz, "není");
    }

    public String getSpz() {
        return spz;
    }

    public String getModel() {
        return model;
    }

}
