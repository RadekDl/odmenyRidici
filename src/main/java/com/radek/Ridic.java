package com.radek;

public class Ridic {
    private String jmeno;
    private String prijmeni;
    private String ujeteKm;
    private Auto auto;

    public Ridic(String jmeno, String prijmeni, String ujeteKm, Auto auto) {
        this.jmeno = jmeno;
        this.prijmeni = prijmeni;
        this.ujeteKm = ujeteKm;
        this.auto = auto;
    }
    public Ridic(String prijmeni){
        this("není",prijmeni,"není",null);
    }

    public String getJmeno() {
        return jmeno;
    }

    public String getPrijmeni() {
        return prijmeni;
    }

    public String getUjeteKm() {
        return ujeteKm;
    }

    public void setUjeteKm(String ujeteKm) {
        this.ujeteKm = ujeteKm;
    }

    public Auto getAuto() {
        return auto;
    }


}
