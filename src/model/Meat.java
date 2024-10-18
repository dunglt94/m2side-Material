package model;

import java.time.LocalDate;

public class Meat extends Material implements Discount {
    private double weight;

    public Meat(){}

    public Meat(double weight){}

    public Meat(String id, String name, String manufacturingDate, int cost, double weight) {
        super(id, name, manufacturingDate, cost);
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public double getAmount() {
        return this.getRealMoney() * this.weight;
    }

    @Override
    public LocalDate getExpiryDate() {
        return super.getManufacturingDate().plusDays(7);
    }

    @Override
    public double getRealMoney() {
        return (double) (super.getCost() * 97) / 100;
    }

    @Override
    public String toString() {
        return "id: " + super.getId()  +
                ", name: " + super.getName() +
                "\nmanufacturingDate: " + super.getManufacturingDate() +
                "\nexpiration date: " + this.getExpiryDate() +
                "\ncost: " + this.getRealMoney() +
                ", weight:" + this.weight +
                ", total amount: " + this.getAmount();
    }
}
