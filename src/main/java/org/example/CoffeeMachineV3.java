package org.example;

import java.util.Scanner;

public class CoffeeMachineV3 extends CoffeeMachineV2 implements Americano, Latte {
    double grainCoffeeTank;
    double grainCoffee;

    CoffeeMachineV3(){
        super();
        this.grainCoffeeTank = 1000;
        this.grainCoffee = 0;
    }
    CoffeeMachineV3(String model, double coffee, double water, double garbage, double milk, double grain) {
        super(model, coffee, water, garbage, milk);
        this.grainCoffeeTank = grain;
        this.grainCoffee = 0;
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
//                    if (this.haveCoffee() && this.haveWater() && this.garbageEmptySpace()) {
//                        CupEspresso esp = this.getEspresso();
//                        System.out.println("Приготовленно: " + esp.toString());
//                    } else {
//                        System.out.println("Приготовление не возможно!");
//                    }
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
//                    if (this.haveCoffee() && this.haveWater() && this.garbageEmptySpace() && this.haveMilk()) {
//                        CupLatte lte = this.getLatte();
//                        System.out.println("Приготовленно: " + lte.toString());
//                    } else {
//                        System.out.println("Приготовление не возможно!");
//                    }
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
            if (!this.haveGrain()) {
                str = "Отсутствует зерновой кофе";
            } else {
                this.changeCoffee();
                System.out.println("Перемалываем кофе. Готово.\nВведите любую цифру.");
                Scanner scan = new Scanner(System.in);
                int pause = scan.nextInt();
                str += this.subMenu();
            }
        } else if (!this.garbageEmptySpace()) {
            str = "Очистите контейнер!";
        } else if (!this.haveMilk()) {
            str = "Налейте молоко";
        } else {
            str += this.subMenu();
        }
        return str;
    }

    public String subMenu(){
        String str = new String();
        str += this.garbageStatusbar();
        str += "\n" + this.waterStatusBar();
        str += "\n" + this.coffeeStatusBar();
        str += "\n" + this.milkStatusBar();
        str += "\n" + this.grainStatusBar();
        str += "\n";
        //str += "\n 1. Приготовить эспрессо";
        str += "\n 2. Приготовить американо";
        str += "\n 3. Приготовить лате";
        //str += "\n 4. Приготовить капучино";
        str += "\n 8. Очистить бак отработанного кофе";
        str += "\n 9. Выключить кофемашину";
        return str;
    }

    public void changeCoffee(){
        if (this.grainCoffee >= 200) {
            this.grainCoffee -= 200;
            this.coffee += 200;
        } else {
            System.out.println("Недостаточно зернового кофе");
        }
    }

    public String grainStatusBar() {
        int progressWater = (int)((this.grainCoffee / this.grainCoffeeTank)*10);
        String str = new String();
        str += "Зерновой - [";
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

    public boolean haveGrain() {
        boolean res;
        if (this.grainCoffee > 0) {
            res = true;
        } else {
            res = false;
        }
        return res;
    }


    public double refuelGrain(double grain) {
        double res;
        if (grain <= this.grainCoffeeTank - this.grainCoffee) {
            this.grainCoffee += grain;
            res = 0;
        } else {
            res = grain - (this.grainCoffeeTank - this.grainCoffee);
            this.grainCoffee = this.grainCoffeeTank;
        }
        return res;
    }

    @Override
    public CupAmericano getAmericano() {
        CupAmericano amr = new CupAmericano();
//        if (this.coffee < 100) {
//            this.changeCoffee();
//        }
        this.coffee -= 120;
        this.water -= 80;
        this.garbage += 120;
        return amr;
    }
}
