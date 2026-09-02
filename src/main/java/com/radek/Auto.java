package com.radek;

public class Auto {
    private String spz;
    private String model;
    private String interniCislo;



    public Auto(String spz, String model,String interniCislo) {
        this.spz =spz;
        this.model = model;
        this.interniCislo = interniCislo;
    }


    public String getSpz() {
        return spz;
    }

    public String getModel() {
        return model;
    }

}
