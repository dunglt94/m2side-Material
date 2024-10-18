package model;

import discount.Discount;

import java.time.LocalDate;

public class CrispyFlour extends Material implements Discount {
    private int quality;

    public CrispyFlour() {}

    public CrispyFlour(int quality) {}

    public CrispyFlour(String id, String name, LocalDate manufacturingDate, int cost, int quality) {
        super(id, name, manufacturingDate, cost);
        this.quality = quality;
    }

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    @Override
    public double getAmount() {
        return super.getCost() * this.quality;
    }

    @Override
    public LocalDate getExpiryDate() {
        return super.getManufacturingDate().plusYears(1);
    }

    @Override
    public double getRealMoney() {
        return (double) (this.getAmount() * 94) / 100;
    }

    @Override
    public String toString() {
        return "id: " + super.getId()  +
                ", name: " + super.getName() +
                "\nmanufacturingDate: " + super.getManufacturingDate() +
                "\nexpiration date: " + this.getExpiryDate() +
                "\ncost: " + super.getCost() +
                ", quality: " + this.quality +
                ", total amount: " + this.getRealMoney();
    }
}
