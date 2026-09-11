package com.radek;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ManagerSpotreby {
    private List<Ridic> ridicList = new ArrayList<>();
    private List<Auto>autoList = new ArrayList<>();
    private List<PrumSpotreba> prumSpotrebaList = new ArrayList<>();
    private List<Tonaz> tonazList = new ArrayList<>();

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

    public Auto najdiAutoDleIntCisla(String interniCislo){
        for (Auto a : autoList){
            if (a.getInterniCislo().equalsIgnoreCase(interniCislo)){
                return a;
            }
        }
        System.out.println("interní číslo "+interniCislo+" nenalezeno ");
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

    public void nacitaniRidicu(String cesta) {
        try (BufferedReader br = new BufferedReader(new FileReader(cesta))) {
            String radek;
            boolean prvniRadek = true;

            while ((radek = br.readLine()) != null) {
                if (prvniRadek) {
                    prvniRadek = false;
                    continue; // přeskočení hlavičky
                }

                String[] sloupce = radek.split(Setting.ODDELOVAC);

                if (sloupce.length < 4) {
                    System.out.println("Řádek přeskočen, málo sloupců: " + radek);
                    continue;
                }

                String jmeno = sloupce[0].trim();
                String interniCislo = sloupce[1].trim();
                String kmText = sloupce[3].trim().replace(",", ".");

                Auto auto = najdiAutoDleIntCisla(interniCislo);
                if (auto == null) {
                    continue; // varování už vypsala najdiAutoDleIntCisla
                }

                try {
                    double ujeteKm = Double.parseDouble(kmText);
                    Ridic ridic = new Ridic(jmeno, ujeteKm, auto);
                    pridatRidice(ridic);
                } catch (NumberFormatException e) {
                    System.out.println("Chybný formát km na řádku: " + radek);
                }
            }
        } catch (IOException e) {
            System.out.println("Chyba při čtení souboru: " + e.getMessage());
        }
    }

    public void nacitaniAut(String cesta) {
        try (BufferedReader br = new BufferedReader(new FileReader(cesta))) {
            String radek;
            boolean prvniRadek = true;

            while ((radek = br.readLine()) != null) {
                if (prvniRadek) {
                    prvniRadek = false;
                    continue; // přeskočení hlavičky
                }

                String[] sloupce = radek.split(Setting.ODDELOVAC);

                if (sloupce.length < 4) {
                    System.out.println("Řádek přeskočen, málo sloupců: " + radek);
                    continue;
                }

                String interniCislo = sloupce[0].trim();
                String spz = sloupce[2].trim();
                String model = sloupce[3].trim();

                Auto auto = new Auto(spz, model, interniCislo);
                pridatAuto(auto);
            }
        } catch (IOException e) {
            System.out.println("Chyba při čtení souboru: " + e.getMessage());
        }
    }

    public void vypisRidicu(){
        for (Ridic r : ridicList){
            System.out.println(r);
        }
    }

    public void vypisAut(){
        for (Auto a : autoList){
            System.out.println(a);
        }
    }

    public void nacitaniSpotreby(String cesta) {
        try (BufferedReader br = new BufferedReader(new FileReader(cesta))) {
            String radek;

            while ((radek = br.readLine()) != null) {
                String[] sloupce = radek.split(Setting.ODDELOVAC);

                if (sloupce.length < 3) {
                    System.out.println("Řádek přeskočen, není zadána spotřeba: " + radek);
                    continue;
                }

                String interniCislo = sloupce[0].trim();
                String spz = sloupce[1].trim();
                String spotrebaText = sloupce[2].trim();

                if (spotrebaText.isEmpty()) {
                    System.out.println("Auto " + spz + " nepočítáno, není zadaná spotřeba nebo je nula");
                    continue;
                }

                try {
                    double spotreba = Double.parseDouble(spotrebaText.replace(",", "."));

                    if (spotreba == 0) {
                        System.out.println("Auto " + spz + " nepočítáno, není zadaná spotřeba nebo je 0");
                        continue;
                    }


                    prumSpotrebaList.add(new PrumSpotreba(interniCislo, spz, spotreba));
                } catch (NumberFormatException e) {
                    System.out.println("Chybný formát spotřeby na řádku: " + radek);
                }
            }
        }
        catch (IOException e) {
        System.out.println("Chyba při čtení souboru: " + e.getMessage());
       }
    }

    public void vypisPrumSpotreby () {
        for (PrumSpotreba p : prumSpotrebaList) {
            System.out.println(p);
             }
    }



    public void nacitaniTonaz(String cesta) {
        try (BufferedReader br = new BufferedReader(new FileReader(cesta))) {
            String radek;

            while ((radek = br.readLine()) != null) {
                String[] sloupce = radek.split(Setting.ODDELOVAC);

                if (sloupce.length < 3) {
                    System.out.println("Řádek přeskočen, tonáž nezadána, prázdné pole : " + radek);
                    continue;
                }

                String interniCislo = sloupce[0].trim();
                String spz = sloupce[1].trim();
                String tonazText = sloupce[2].trim();

                if (tonazText.equalsIgnoreCase("#N/A") || tonazText.isEmpty()) {
                    System.out.println("Auto " + spz + " tonáž nezadána");
                    continue;
                }

                try {
                    double tonazKg = Double.parseDouble(tonazText.replace(",", "."));
                    double tonazTuny = tonazKg / 1000.0;
                    tonazList.add(new Tonaz(interniCislo, spz, tonazTuny));
                } catch (NumberFormatException e) {
                    System.out.println("Chybný formát tonáže na řádku: " + radek);
                }
            }
        } catch (IOException e) {
            System.out.println("Chyba při čtení souboru: " + e.getMessage());
        }
    }

    public void vypisTonaz(){
        for (Tonaz t : tonazList){
            System.out.println(t);
        }
    }

}
