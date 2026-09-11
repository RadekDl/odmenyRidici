package com.radek;

public class Tonaz {
    private String interniCislo;
    private String spz;
    private double tonaz;

    public Tonaz(String interniCislo, String spz, double tonaz) {
        this.interniCislo = interniCislo;
        this.spz = spz;
        this.tonaz = tonaz;
    }

    public String getInterniCislo() {
        return interniCislo;
    }

    public String getSpz() {
        return spz;
    }

    public double getTonaz() {
        return tonaz;
    }

    @Override
    public String toString() {
        return String.format("%-10s %-10s %6.2f t", interniCislo, spz, tonaz);
    }
}
