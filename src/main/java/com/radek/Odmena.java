package com.radek;

public class Odmena {
    private double odmena;
    private Ridic ridic;
    private String mesic;

    public Odmena(double odmena, Ridic ridic,String mesic) {
        this.odmena = odmena;
        this.ridic = ridic;
        this.mesic = mesic;
    }

    public double getOdmena() {
        return odmena;
    }

    public Ridic getRidic() {
        return ridic;
    }

    public String getMesic() {
        return mesic;
    }
}
