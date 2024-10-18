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
            sum += m.getRealMoney();
        }
        return sum;
    }

    public static void sortByPrice(Material[] material) {
        Comparator<Material> materialComparator = new MaterialComparator();
        Arrays.sort(material, materialComparator);
    }

}
