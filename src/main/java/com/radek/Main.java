package com.radek;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        ManagerSpotreby managerSpotreby = new ManagerSpotreby();
        managerSpotreby.nacitaniAut("vozovyParkVolvo.csv");
        managerSpotreby.nacitaniRidicu("ridici.csv");

//        for (Ridic r : managerSpotreby.getRidicList()) {
//            if (r.getJmeno().contains("louh")) {
//                String s = r.getJmeno();
//                System.out.println("Nalezeno: [" + s + "]");
//                for (int i = 0; i < s.length(); i++) {
//                    System.out.println(i + ": [" + s.charAt(i) + "] = " + (int) s.charAt(i));
//                }
//            }
//        }
        managerSpotreby.nacitaniSpotreby("spotreba.csv");
//        System.out.println("\n Řidiči \n");
//        managerSpotreby.vypisRidicu();
//        System.out.println("\n vozidla \n");
//        managerSpotreby.vypisAut();
//        System.out.println("\n pům spotřeba \n");
//        managerSpotreby.vypisPrumSpotreby();
//        System.out.println("\n tonáž \n");
        managerSpotreby.nacitaniTonaz("tonaz.csv");
//        managerSpotreby.vypisTonaz();

//        Ridic ridic = managerSpotreby.najdiRidiceDleJmena("Dlouhý Roman");
//        System.out.println(ridic);
        managerSpotreby.vypocetOdmenVsechRidicu("září");
        managerSpotreby.vypisOdmeny();
    }


}