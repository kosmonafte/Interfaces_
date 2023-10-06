package org.example;

public class CupCapusino {
    double water;
    double coffee;
    double milk;

    CupCapusino(double milk){
        this.coffee = 22;
        this.water = 30;
        this.milk = milk;
    }

    @Override
    public String toString() {
        return "Горячий капучино!";
    }
}
