package ru.itmo.student.lab3.environment;

public class Food {

    private Temperature temp = Temperature.Cold;
    public Temperature getTemp() {
       return this.temp;
    }

    public void setTemp(Temperature t) {
        this.temp = t;
    }

    public enum Taste {Disgusting, Default}
    public enum CookedDegree {
        Raw,Undercooked,Overcooked,Burned}
    private Food.CookedDegree cookedDegree;
    public CookedDegree getCookedDegree() {
        return cookedDegree;
    }
    public void setCookedDegree(CookedDegree cookedDegree) {
        this.cookedDegree = cookedDegree;
    }


}
