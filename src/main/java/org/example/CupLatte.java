package org.example;

public class CupLatte {
    double water;
    double coffee;
    double milk;

    CupLatte(double milk){
        this.coffee = 22;
        this.water = 30;
        this.milk = milk;
    }

    @Override
    public String toString() {
        return "Горячий латте!";
    }
}
