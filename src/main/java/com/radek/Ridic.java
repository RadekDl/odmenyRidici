package com.radek;

public class Ridic {
    private String jmeno;

    private double ujeteKm;
    private Auto auto;

    public Ridic(String jmeno, double ujeteKm, Auto auto) {
        this.jmeno = jmeno;
        this.ujeteKm = ujeteKm;
        this.auto = auto;
    }

    public String getJmeno() {
        return jmeno;
    }



    public double getUjeteKm() {
        return ujeteKm;
    }

    public void setUjeteKm(double ujeteKm) {
        this.ujeteKm = ujeteKm;
    }

    public Auto getAuto() {
        return auto;
    }


}
