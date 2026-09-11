package com.radek;

public class PrumSpotreba {
    private String interniCislo;
    private String spz;
    private double spotreba;

    public PrumSpotreba(String interniCislo, String spz, double spotreba) {
        this.interniCislo = interniCislo;
        this.spz = spz;
        this.spotreba = spotreba;
    }

    public String getInterniCislo() {
        return interniCislo;
    }

    public String getSpz() {
        return spz;
    }

    public double getSpotreba() {
        return spotreba;
    }

    @Override
    public String toString() {
        return String.format("%-10s %-10s %6.2f l/100km", interniCislo, spz, spotreba);
    }
}
