package org.example;

import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        //CoffeeMachine cf = new CoffeeMachine();
        //CoffeeMachineV2 cf = new CoffeeMachineV2();
        CoffeeMachineV3 cf = new CoffeeMachineV3();
        System.out.println(cf.toString());
        cf.refuelCoffee(1000);
        cf.refuelWater(1000);
        cf.refuelMilk(1000);
        cf.refuelGrain(1000);
        while (true) {
            System.out.println("1. Включить кофемашину");
            Scanner scan = new Scanner(System.in);
            int choice = scan.nextInt();
            if (choice == 1) {
                cf.power();
            }
        }
    }
}