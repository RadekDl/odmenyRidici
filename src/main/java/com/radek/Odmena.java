package com.radek;

public class Odmena {
    private double odmena;
    private Ridic ridic;
    private String mesic;
    private double tonaz;
    private double spotreba;
    private double normaA;
    private double normaB;
    private double rozdil;

    public Odmena(double odmena, Ridic ridic, String mesic, double tonaz, double spotreba, double normaA, double normaB,double rozdil) {
        this.odmena = odmena;
        this.ridic = ridic;
        this.mesic = mesic;
        this.tonaz = tonaz;
        this.spotreba = spotreba;
        this.normaA = normaA;
        this.normaB = normaB;
        this.rozdil = rozdil;
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

    public double getTonaz() {
        return tonaz;
    }

    public double getSpotreba() {
        return spotreba;
    }

    public double getNormaA() {
        return normaA;
    }

    public double getNormaB() {
        return normaB;
    }

    public double getRozdil() {
        return rozdil;
    }

    @Override
    public String toString() {
        return String.format("%-25s %8s %10.2f Kč   %s   tonáž: %6.2f t   spotřeba: %6.2f l/100km   norma: %5.2f-%5.2f  rozdil: %6.2f l/100km ujeto: %10.2f km ",
                ridic.getJmeno(),ridic.getAuto().getInterniCislo(), odmena, mesic, tonaz, spotreba, normaA, normaB, rozdil, ridic.getUjeteKm());
    }
}
