package org.example;

import java.util.Scanner;

public class CoffeeMachine implements Menu, Espresso, Americano {
    String model;
    double coffeeTank;
    double coffee;
    double waterTank;
    double water;
    double garbageTank;
    double garbage;
    boolean power;

    public CoffeeMachine() {
        this.model = "Тестовая модель";
        this.coffeeTank = 1000;
        this.waterTank = 1000;
        this.garbageTank = 1000;
        this.power = false;
        this.coffee = 0;
        this.water = 0;
        this.garbage = 0;
    }

    CoffeeMachine(String model, double coffee, double water, double garbage) {
        this.model = model;
        this.coffeeTank = coffee;
        this.waterTank = water;
        this.garbageTank = garbage;
        this.power = false;
        this.coffee = 0;
        this.water = 0;
        this.garbage = 0;
    }

    public void power(){
        if (!this.power) {
            this.power = true;
            while (this.power) {
                System.out.println(this.mainMenu());
                Scanner scan = new Scanner(System.in);
                int choice = scan.nextInt();
                if (choice == 9) {
                    this.power = false;
                } else if (choice == 1) {
                    if (this.haveCoffee() && this.haveWater() && this.garbageEmptySpace()) {
                        CupEspresso esp = this.getEspresso();
                        System.out.println("Приготовленно: " + esp.toString());
                    } else {
                        System.out.println("Приготовление не возможно!");
                    }
                } else if (choice == 2) {
                    if (this.haveCoffee() && this.haveWater() && this.garbageEmptySpace()) {
                        CupAmericano amr = this.getAmericano();
                        System.out.println("Приготовленно: " + amr.toString());
                    } else {
                        System.out.println("Приготовление не возможно!");
                    }
                } else if (choice == 8) {
                    this.clearGarbage();
                }
            }
        } else {
            this.power = false;
        }
    }

    public double refuelCoffee(double coffee) {
        double res;
        if (coffee <= this.coffeeTank - this.coffee) {
            this.coffee += coffee;
            res = 0;
        } else {
            res = coffee - (this.coffeeTank - this.coffee);
            this.coffee = this.coffeeTank;
        }
        return res;
    }

    public double refuelWater(double water) {
        double res;
        if (water <= this.waterTank - this.water) {
            this.water += water;
            res = 0;
        } else {
            res = water - (this.waterTank - this.water);
            this.water = this.waterTank;
        }
        return res;
    }


    public boolean haveWater(){
        boolean res;
        if (this.water < 30) {
            res = false;
        } else {
            res = true;
        }
        return res;
    }

    public boolean haveCoffee(){
        boolean res;
        if (this.coffee < 30) {
            res = false;
        } else {
            res = true;
        }
        return res;
    }

    public boolean garbageEmptySpace(){
        boolean res;
        if (this.garbageTank - this.garbage > 22) {
            res = true;
        } else {
            res = false;
        }
        return res;
    }

    @Override
    public String toString() {
        return "Кофе машина модель: " + this.model + "\n";
    }

    @Override
    public String mainMenu() {
        String str = new String();
        if (!this.haveWater()) {
            str = "Налейте воду!";
        } else if (!this.haveCoffee()) {
            str = "Засыпьте кофе";
        } else if (!this.garbageEmptySpace()) {
            str = "Очистите контейнер!";
        } else {
            str += this.garbageStatusbar();
            str += "\n" + this.waterStatusBar();
            str += "\n" + this.coffeeStatusBar();
            str += "\n";
            str += "\n 1. Приготовить эспрессо";
            str += "\n 2. Приготовить американо";
            str += "\n 8. Очистить бак отработанного кофе";
            str += "\n 9. Выключить кофемашину";
        }
        return str;
    }

    public String garbageStatusbar() {
        int progress = (int)((this.garbage / this.garbageTank)*10);
        String str = new String();
        str += "Бак для отработанного кофе - [";
        for (int i = 0; i < 10; i++) {
            if (i < progress) {
                str += "=";
            } else {
                str += " ";
            }
        }
        str += "]";
        return str;
    }

    public String waterStatusBar() {
        int progressWater = (int)((this.water / this.waterTank)*10);
        String str = new String();
        str += "Вода - [";
        for (int i = 0; i < 10; i++) {
            if (i < progressWater) {
                str += "=";
            } else {
                str += " ";
            }
        }
        str += "]";
        return str;
    }

    public String coffeeStatusBar() {
        int progressWater = (int)((this.coffee / this.coffeeTank)*10);
        String str = new String();
        str += "Кофе - [";
        for (int i = 0; i < 10; i++) {
            if (i < progressWater) {
                str += "=";
            } else {
                str += " ";
            }
        }
        str += "]";
        return str;
    }

    public void clearGarbage() {
        this.garbage = 0;
    }

    @Override
    public CupEspresso getEspresso() {
        this.coffee -= 22;
        this.water -= 30;
        this.garbage += 30;
        CupEspresso cpEsp = new CupEspresso();
        return cpEsp;
    }

    @Override
    public CupAmericano getAmericano() {
        CupAmericano amr = new CupAmericano();
        this.coffee -= 22;
        this.water -= 100;
        this.garbage += 30;
        return amr;
    }
}
