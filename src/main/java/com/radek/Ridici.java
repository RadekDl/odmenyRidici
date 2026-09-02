package com.radek;

public class Ridici {
    private String jmeno;
    private String prijmeni;
    private String kartaRidiceCislo;
    private Auto auto;

    public Ridici(String jmeno, String prijmeni, String kartaRidiceCislo, Auto auto) {
        this.jmeno = jmeno;
        this.prijmeni = prijmeni;
        this.kartaRidiceCislo = kartaRidiceCislo;
        this.auto = auto;
    }
    public Ridici(String prijmeni){
        this("není",prijmeni,"není",null);
    }

    public String getJmeno() {
        return jmeno;
    }

    public String getPrijmeni() {
        return prijmeni;
    }

    public String getKartaRidiceCislo() {
        return kartaRidiceCislo;
    }

    public void setKartaRidiceCislo(String kartaRidiceCislo) {
        this.kartaRidiceCislo = kartaRidiceCislo;
    }

    public Auto getAuto() {
        return auto;
    }


}
