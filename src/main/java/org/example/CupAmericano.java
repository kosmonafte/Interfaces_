package org.example;

public class CupAmericano {
    double coffee;
    double water;

    CupAmericano(){
        this.coffee = 22;
        this.water = 100;
    }

    @Override
    public String toString() {
        return "Горячий американо!";
    }
}
