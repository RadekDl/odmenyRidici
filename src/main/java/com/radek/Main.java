package com.radek;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        ManagerSpotreby managerSpotreby = new ManagerSpotreby();
        managerSpotreby.nacitaniAut("vozovyParkVolvo.csv");
        managerSpotreby.nacitaniRidicu("ridici.csv");
        Scanner scanner =new Scanner(System.in);
        String mesic = vyberMesic(scanner);


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

        managerSpotreby.vypocetOdmenVsechRidicu(mesic);
        managerSpotreby.vypisOdmeny();
        managerSpotreby.vypisOdmenyDoSouboru("odmeny " + mesic + ".csv");    }
    private static String vyberMesic(Scanner scanner) {
        while (true) {
            System.out.println("Vyber měsíc:");
            for (int i = 0; i < Setting.MESICE.length; i++) {
                System.out.println((i + 1) + " - " + Setting.MESICE[i]);
            }
            System.out.print("Zadej číslo (1-12): ");

            String vstup = scanner.nextLine().trim();

            try {
                int cislo = Integer.parseInt(vstup);
                if (cislo >= 1 && cislo <= 12) {
                    return Setting.MESICE[cislo - 1];
                } else {
                    System.out.println("Neplatné číslo, zkus to znovu.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Zadej prosím číslo 1-12.");
            }
        }
    }


}