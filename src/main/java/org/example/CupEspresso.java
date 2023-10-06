package org.example;

public class CupEspresso {
    double coffee;
    double water;

    CupEspresso(){
        this.coffee = 22;
        this.water = 30;
    }

    @Override
    public String toString() {
        return "Горячий эспрессо!";
    }
}
