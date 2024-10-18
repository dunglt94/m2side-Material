package controller;

import model.Material;

import java.util.Comparator;

public class MaterialComparator implements Comparator<Material> {

    @Override
    public int compare(Material m1, Material m2) {
        if (m1.getCost() > m2.getCost()) return 1;
        else if (m1.getCost() < m2.getCost()) return -1;
        else return 0;
    }
}
