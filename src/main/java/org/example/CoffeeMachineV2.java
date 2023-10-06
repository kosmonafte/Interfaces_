package org.example;

import java.util.Scanner;

public class CoffeeMachineV2 extends CoffeeMachine implements Menu, Espresso, Americano, Latte, Capusino {
    double milkTank;
    double milk;

    CoffeeMachineV2(){
        super();
        this.milkTank = 1000;
        this.milk = 0;
    }

    CoffeeMachineV2(String model, double coffee, double water, double garbage, double milk){
        super(model, coffee, water, garbage);
        this.milkTank = milk;
    }

    public boolean haveMilk() {
        boolean res;
        if (this.milk > 0) {
            res = true;
        } else {
            res = false;
        }
        return res;
    }

    public double refuelMilk(double milk) {
        double res;
        if (milk <= this.milkTank - this.milk) {
            this.milk += milk;
            res = 0;
        } else {
            res = milk - (this.milkTank - this.milk);
            this.milk = this.milkTank;
        }
        return res;
    }

    @Override
    public void power() {
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
                } else if (choice == 3) {
                    if (this.haveCoffee() && this.haveWater() && this.garbageEmptySpace() && this.haveMilk()) {
                        CupLatte lte = this.getLatte();
                        System.out.println("Приготовленно: " + lte.toString());
                    } else {
                        System.out.println("Приготовление не возможно!");
                    }
                } else if (choice == 4) {
                    if (this.haveCoffee() && this.haveWater() && this.garbageEmptySpace() && this.haveMilk()) {
                        CupLatte lte = this.getLatte();
                        System.out.println("Приготовленно: " + lte.toString());
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

    @Override
    public String mainMenu() {
        String str = new String();
        if (!this.haveWater()) {
            str = "Налейте воду!";
        } else if (!this.haveCoffee()) {
            str = "Засыпьте кофе";
        } else if (!this.garbageEmptySpace()) {
            str = "Очистите контейнер!";
        } else if (!this.haveMilk()) {
            str = "Налейте молоко";
        } else {
            str += this.garbageStatusbar();
            str += "\n" + this.waterStatusBar();
            str += "\n" + this.coffeeStatusBar();
            str += "\n" + this.milkStatusBar();
            str += "\n";
            str += "\n 1. Приготовить эспрессо";
            str += "\n 2. Приготовить американо";
            str += "\n 3. Приготовить лате";
            str += "\n 4. Приготовить капучино";
            str += "\n 8. Очистить бак отработанного кофе";
            str += "\n 9. Выключить кофемашину";
        }
        return str;
    }

    public String milkStatusBar() {
        int progressWater = (int)((this.milk / this.milkTank)*10);
        String str = new String();
        str += "Молоко - [";
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

    @Override
    public CupLatte getLatte() {
        System.out.println("Сколько молока?");
        Scanner scan = new Scanner(System.in);
        int milk = scan.nextInt();
        this.coffee -= 22;
        this.water -= 30;
        this.milk -= milk;
        this.garbage += 30;
        CupLatte lte = new CupLatte(milk);
        return lte;
    }

    @Override
    public CupCapusino getCapusino() {
        System.out.println("Сколько молока?");
        Scanner scan = new Scanner(System.in);
        int milk = scan.nextInt();
        this.coffee -= 22;
        this.water -= 30;
        this.milk -= milk;
        this.garbage += 30;
        CupCapusino cps = new CupCapusino(milk);
        return cps;
    }
}
