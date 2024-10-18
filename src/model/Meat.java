package model;

import discount.Discount;

import java.time.LocalDate;

public class Meat extends Material implements Discount {
    private double weight;

    public Meat(){}

    public Meat(double weight){}

    public Meat(String id, String name, LocalDate manufacturingDate, int cost, double weight) {
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
        return super.getCost() * this.weight;
    }

    @Override
    public LocalDate getExpiryDate() {
        return super.getManufacturingDate().plusDays(7);
    }

    @Override
    public double getRealMoney() {
        return (double) (this.getAmount() * 97) / 100;
    }

    @Override
    public String toString() {
        return "id: " + super.getId()  +
                ", name: " + super.getName() +
                "\nmanufacturingDate: " + super.getManufacturingDate() +
                "\nexpiration date: " + this.getExpiryDate() +
                "\ncost: " + super.getCost() +
                ", weight:" + this.weight +
                ", total amount: " + this.getRealMoney();
    }
}
