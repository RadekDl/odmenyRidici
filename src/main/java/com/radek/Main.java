package com.radek;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        ManagerSpotreby managerSpotreby = new ManagerSpotreby();
        managerSpotreby.nacitaniAut("vozovyParkVolvo.csv");
        managerSpotreby.nacitaniRidicu("ridici.csv");
        managerSpotreby.nacitaniSpotreby("spotreba.csv");
        System.out.println("\n Řidiči \n");
        managerSpotreby.vypisRidicu();
        System.out.println("\n vozidla \n");
        managerSpotreby.vypisAut();
        System.out.println("\n pům spotřeba \n");
        managerSpotreby.vypisPrumSpotreby();
        System.out.println("\n tonáž \n");
        managerSpotreby.nacitaniTonaz("tonaz.csv");
        managerSpotreby.vypisTonaz();

    }
}