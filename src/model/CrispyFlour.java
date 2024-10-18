package model;

import java.time.LocalDate;

public class CrispyFlour extends Material implements Discount {
    private int quantity;

    public CrispyFlour() {}

    public CrispyFlour(int quantity) {}

    public CrispyFlour(String id, String name, LocalDate manufacturingDate, int cost, int quantity) {
        super(id, name, manufacturingDate, cost);
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public double getAmount() {
        return this.getRealMoney() * this.quantity;
    }

    @Override
    public LocalDate getExpiryDate() {
        return super.getManufacturingDate().plusYears(1);
    }

    @Override
    public double getRealMoney() {
        return (double) (this.getCost() * 94) / 100;
    }

    @Override
    public String toString() {
        return "id: " + super.getId()  +
                ", name: " + super.getName() +
                "\nmanufacturingDate: " + super.getManufacturingDate() +
                "\nexpiration date: " + this.getExpiryDate() +
                "\ncost: " + this.getRealMoney() +
                ", quantity: " + this.quantity +
                ", total amount: " + this.getAmount();
    }
}
