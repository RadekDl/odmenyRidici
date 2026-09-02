package com.radek;

public class Odmena {
    private double normaSpotreby;
    private Ridic ridic;
    private String mesic;

    public Odmena(double normaSpotreby, Ridic ridic,String mesic) {
        this.normaSpotreby = normaSpotreby;
        this.ridic = ridic;
        this.mesic = mesic;
    }

    public double getNormaSpotreby() {
        return normaSpotreby;
    }

    public Ridic getRidic() {
        return ridic;
    }
}
