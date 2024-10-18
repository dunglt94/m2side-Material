package controller;

import model.Material;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Method {
//    Main
    public static void printMaterialList(Material[] material) {
        for (Material m : material) {
            System.out.println(m);
            System.out.println();
        }
    }

    public static double calTotalMoney(Material[] material) {
        double sum = 0;
        for (Material m : material) {
            sum += m.getAmount();
        }
        return sum;
    }

    public static void sortByCost(Material[] material) {
        for (int i = 0; i < material.length; i++) {
            int index = i;
            for (int j = i + 1; j < material.length; j++) {
                if (material[i].getCost() > material[j].getCost()) {
                    index = j;
                }
            }
            Material temp = material[index];
            material[index] = material[i];
            material[i] = temp;
            System.out.println(material[i].toString());
            System.out.println();
        }
    }
}
