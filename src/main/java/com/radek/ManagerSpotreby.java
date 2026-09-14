package com.radek;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public class ManagerSpotreby {
    private List<Ridic> ridicList = new ArrayList<>();
    private List<Auto> autoList = new ArrayList<>();
    private List<PrumSpotreba> prumSpotrebaList = new ArrayList<>();
    private List<Tonaz> tonazList = new ArrayList<>();
    private List<Odmena> odmenaList = new ArrayList<>();

    public List<Ridic> getRidicList() {
        return ridicList;
    }

    public void pridatAuto(Auto auto) {
        for (Auto a : autoList) {
            if (a.getSpz().equalsIgnoreCase(auto.getSpz())) {
                System.out.println(" SPZ " + a.getSpz() + " již existuje nelze přidat ");
                return;
            }
        }
        autoList.add(auto);
    }

    public boolean odebratAuto(Auto auto) {
        return autoList.remove(auto);
    }

    public Auto najdiAutoDleSpz(String spz) {
        for (Auto a : autoList) {
            if (a.getSpz().equalsIgnoreCase(spz)) {
                return a;
            }

        }
        System.out.println("spz " + spz + " nenalezena ");
        return null;
    }

    public Auto najdiAutoDleIntCisla(String interniCislo) {
        for (Auto a : autoList) {
            if (a.getInterniCislo().equalsIgnoreCase(interniCislo)) {
                return a;
            }
        }
        System.out.println("interní číslo " + interniCislo + " nenalezeno ");
        return null;
    }

    public Ridic najdiRidiceDleJmena(String jmeno) {
        String hledane = jmeno.trim().replace('\u00A0', ' ');
        for (Ridic r : ridicList) {
            String existujici = r.getJmeno().trim().replace('\u00A0', ' ');
            if (existujici.equalsIgnoreCase(hledane)) {
                return r;
            }
        }
        System.out.println("Řidič nenalezen: " + jmeno);
        return null;
    }

    public void pridatRidice(Ridic ridic) {
        for (Ridic r : ridicList) {
            if (r.getJmeno().trim().equalsIgnoreCase(ridic.getJmeno().trim())) {
                System.out.println("jmeno " + r.getJmeno() + " již existuje nelze přidat ");
                return;
            }
        }
        ridicList.add(ridic);
    }

    public void nacitaniRidicu(String cesta) {
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(cesta), StandardCharsets.UTF_8))) {
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

    public void vypisRidicu() {
        for (Ridic r : ridicList) {
            System.out.println("[" + r.getJmeno() + "] délka=" + r.getJmeno().length());
//            System.out.println(r);
        }
    }

    public void vypisAut() {
        for (Auto a : autoList) {
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
        } catch (IOException e) {
            System.out.println("Chyba při čtení souboru: " + e.getMessage());
        }
    }

    public void vypisPrumSpotreby() {
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

    public void vypisTonaz() {
        for (Tonaz t : tonazList) {
            System.out.println(t);
        }
    }

    private double najdiTonazDleAuta(Auto auto) {
        for (Tonaz t : tonazList) {
            if (t.getSpz().equals(auto.getSpz())) {
                return t.getTonaz();
            }
        }
        System.out.println("Tonáž nenalezena pro SPZ: " + auto.getSpz());
        return 0;
    }

    private double najdiSpotrebuDleAuta(Auto auto) {
        for (PrumSpotreba s : prumSpotrebaList) {
            if (s.getSpz().equals(auto.getSpz())) {
                return s.getSpotreba();
            }
        }
        System.out.println("Spotřeba nenalezena pro SPZ: " + auto.getSpz());
        return 0;
    }

    private double[] najdiNormu(String model, double tonaz) {
        String modelLower = model.toLowerCase();
        boolean maTC = modelLower.contains("tc");
        boolean maTandem = modelLower.contains("tandem");

        double[] sada;
        if (maTandem && maTC) {
            sada = new double[]{
                    Setting.NORMA_VOLVO_TANDEM_TC_A, Setting.NORMA_VOLVO_TANDEM_TC_B,
                    Setting.NORMA_VOLVO_TANDEM_TC_C, Setting.NORMA_VOLVO_TANDEM_TC_D,
                    Setting.NORMA_VOLVO_TANDEM_TC_E, Setting.NORMA_VOLVO_TANDEM_TC_F,
                    Setting.NORMA_VOLVO_TANDEM_TC_G, Setting.NORMA_VOLVO_TANDEM_TC_H,
                    Setting.NORMA_VOLVO_TANDEM_TC_I, Setting.NORMA_VOLVO_TANDEM_TC_J
            };
        } else if (maTandem) {
            sada = new double[]{
                    Setting.NORMA_VOLVO_TANDEM_A, Setting.NORMA_VOLVO_TANDEM_B,
                    Setting.NORMA_VOLVO_TANDEM_C, Setting.NORMA_VOLVO_TANDEM_D,
                    Setting.NORMA_VOLVO_TANDEM_E, Setting.NORMA_VOLVO_TANDEM_F,
                    Setting.NORMA_VOLVO_TANDEM_G, Setting.NORMA_VOLVO_TANDEM_H,
                    Setting.NORMA_VOLVO_TANDEM_I, Setting.NORMA_VOLVO_TANDEM_J
            };
        } else if (maTC) {
            sada = new double[]{
                    Setting.NORMA_VOLVO_TC_A, Setting.NORMA_VOLVO_TC_B,
                    Setting.NORMA_VOLVO_TC_C, Setting.NORMA_VOLVO_TC_D,
                    Setting.NORMA_VOLVO_TC_E, Setting.NORMA_VOLVO_TC_F,
                    Setting.NORMA_VOLVO_TC_G, Setting.NORMA_VOLVO_TC_H,
                    Setting.NORMA_VOLVO_TC_I, Setting.NORMA_VOLVO_TC_J
            };
        } else {
            sada = new double[]{
                    Setting.NORMA_VOLVO_A, Setting.NORMA_VOLVO_B,
                    Setting.NORMA_VOLVO_C, Setting.NORMA_VOLVO_D,
                    Setting.NORMA_VOLVO_E, Setting.NORMA_VOLVO_F,
                    Setting.NORMA_VOLVO_G, Setting.NORMA_VOLVO_H,
                    Setting.NORMA_VOLVO_I, Setting.NORMA_VOLVO_J
            };
        }


        int index;
        if (tonaz < Setting.VAHA_NAKLADU_B) {
            index = 0;
        } else if (tonaz < Setting.VAHA_NAKLADU_D) {
            index = 2;
        } else if (tonaz < Setting.VAHA_NAKLADU_F) {
            index = 4;
        } else if (tonaz < Setting.VAHA_NAKLADU_H) {
            index = 6;
        } else {
            index = 8;
        }

        return new double[]{sada[index], sada[index + 1]};
    }

    public double vypocetOdmenyRidice(Ridic ridic, String mesic) {
        // 1. Auto je přímo napojené na řidiče
        Auto auto = ridic.getAuto();
        if (auto == null) {
            System.out.println("Auto nenalezeno pro řidiče: " + ridic.getJmeno());
            return 0;
        }

        // 2. Najít tonáž pro dané auto
        double tonaz = najdiTonazDleAuta(auto);

        // 3. Najít průměrnou spotřebu pro dané auto
        double spotreba = najdiSpotrebuDleAuta(auto);
        if (spotreba == 0) {
            System.out.println("Spotřeba nezadána pro: " + ridic.getJmeno());
            return 0;
        }

        // 4. Podle modelu a tonáže vybrat normu (A/B pár)
        double[] norma = najdiNormu(auto.getModel(), tonaz);
        double normaA = norma[0];
        double normaB = norma[1];

        // 5. Rozdíl mezi normou a skutečnou spotřebou (kladné = úspora, záporné = nad normou)
        double rozdil;
        if (spotreba < normaA) {
            rozdil = spotreba - normaA; // záporné číslo = úspora
        } else if (spotreba > normaB) {
            rozdil = spotreba - normaB; // kladné číslo = nadspotřeba
        } else {
            rozdil = 0; // v normě
        }

        // 6. Porovnat spotřebu s normou -> báze odměny
        double bazeOdmeny = 0;
        if (spotreba < normaA) {
            double usporeno = normaA - spotreba;
            if (usporeno <= 0.5) {
                bazeOdmeny = Setting.ODMENA_TEZKA_SPOTREBA_A; // 500
            } else if (usporeno <= 1.0) {
                bazeOdmeny = Setting.ODMENA_TEZKA_SPOTREBA_B; // 1000
            } else if (usporeno <= 1.5) {
                bazeOdmeny = Setting.ODMENA_TEZKA_SPOTREBA_C; // 1500
            } else {
                bazeOdmeny = Setting.ODMENA_TEZKA_SPOTREBA_D; // 2000
            }
        }
        // pokud spotreba je mezi normaA a normaB (v normě), nebo nad normaB -> baze zůstává 0

        // 7. Podle ujetých km vybrat procento
        double procento;
        double ujeteKm = ridic.getUjeteKm();
        if (ujeteKm <= Setting.UJETE_KM_A) {
            procento = Setting.ODMENA_TEZKA_KM_A; // 0
        } else if (ujeteKm <= Setting.UJETE_KM_B) {
            procento = Setting.ODMENA_TEZKA_KM_B; // 0.5
        } else {
            procento = Setting.ODMENA_TEZKA_KM_C; // 1.0
        }

        // 8. Finální odměna
        double odmena = bazeOdmeny * procento;

        Odmena zaznam = new Odmena(odmena, ridic, mesic, tonaz, spotreba, normaA, normaB, rozdil);
        odmenaList.add(zaznam);

        return odmena;
    }

    public void vypocetOdmenVsechRidicu(String mesic) {
        for (Ridic r : ridicList) {
            vypocetOdmenyRidice(r, mesic);
        }
    }

    public void vypisOdmeny() {
        Collator czechCollator = Collator.getInstance(new Locale("cs", "CZ"));
        odmenaList.sort((o1, o2) -> czechCollator.compare(
                o1.getRidic().getJmeno(),
                o2.getRidic().getJmeno()));

        for (Odmena o : odmenaList) {
            System.out.println(o);
        }

    }

    public void vypisOdmenyDoSouboru(String cesta) {
        Collator czechCollator = Collator.getInstance(new Locale("cs", "CZ"));
        odmenaList.sort((o1, o2) -> czechCollator.compare(
                o1.getRidic().getJmeno(),
                o2.getRidic().getJmeno()));

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(cesta))) {
            writer.write("Jméno;Odměna;Měsíc;Tonáž;Spotřeba;NormaA;NormaB;Rozdíl;UjetéKm");
            writer.newLine();

            for (Odmena o : odmenaList) {
                String radek = o.getRidic().getJmeno() + ";"
                        + format(o.getOdmena()) + ";"
                        + o.getMesic() + ";"
                        + format(o.getTonaz()) + ";"
                        + format(o.getSpotreba()) + ";"
                        + format(o.getNormaA()) + ";"
                        + format(o.getNormaB()) + ";"
                        + format(o.getRozdil()) + ";"
                        + format(o.getRidic().getUjeteKm());
                writer.write(radek);
                writer.newLine();
            }

            System.out.println("Export do souboru " + cesta + " proběhl úspěšně.");
        } catch (IOException e) {
            System.out.println("Chyba při zápisu do souboru: " + e.getMessage());
        }
    }

    private String format(double hodnota) {
        return String.format(new Locale("cs", "CZ"), "%.2f", hodnota);
    }


}
