package com.radek;

import java.util.ArrayList;
import java.util.List;

public class ManagerSpotreby {
    private List<Ridic> ridicList = new ArrayList<>();
    private List<Auto>autoList = new ArrayList<>();

    public void pridatAuto(Auto auto){
        for (Auto a : autoList){
            if (a.getSpz().equalsIgnoreCase(auto.getSpz())){
                System.out.println(" SPZ " + a.getSpz() +" již existuje nelze přidat ");
                return;
            }
        }
        autoList.add(auto);
    }

    public boolean odebratAuto(Auto auto){
        return autoList.remove(auto);
    }

    public Auto najdiAutoDleSpz(String spz){
        for (Auto a : autoList){
            if (a.getSpz().equalsIgnoreCase(spz)){
                return a;
            }

        }
        System.out.println("spz "+spz+" nenalezena ");
        return null;
    }

    public void pridatRidice(Ridic ridic){
        for (Ridic r : ridicList){
            if (r.getJmeno().trim().equalsIgnoreCase(ridic.getJmeno().trim())){
                System.out.println("jmeno "+ r.getJmeno()+" již existuje nelze přidat ");
                return;
            }
        }
        ridicList.add(ridic);
    }

}
