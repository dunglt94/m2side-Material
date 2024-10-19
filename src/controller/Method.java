package controller;

import model.CrispyFlour;
import model.Material;
import model.Meat;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Scanner;

public class Method {
    static Scanner scanner = new Scanner(System.in);
//  Các  menu
    public static void mainMenu(Material[] materialList) {
        System.out.println("Manage");
        System.out.println("1. Calculate the total cost of all materials");
        System.out.println("2. Sort by cost ascending");
        System.out.println("3. Add Material");
        System.out.println("4. Edit Material");
        System.out.println("5. Remove Material");
        System.out.println("6. Calculate the total discount of all materials");
        System.out.println("7. Exit");
        switchCaseForMainMenu(materialList);
    }

    public static void continueOrExit(Material[] materialList) {
        System.out.println("Continue?");
        System.out.println("1. Yes");
        System.out.println("2. No");
        switch (choice()) {
            case 1:
                mainMenu(materialList);
                break;
            case 2:
                System.exit(0);
            default:
                System.out.println("Not a valid choice");
                continueOrExit(materialList);
        }
    }

    public static void switchCaseForMainMenu(Material[] materialList) {
        switch (choice()) {
            case 1:
                System.out.print("The total cost of all materials: ");
                calculateTotalAmount(materialList);
                break;
            case 2:
                System.out.println("Pre-sorted:");
                printMaterialList(materialList);
                System.out.println("Sorted by cost:");
                sortByCost(materialList);
                break;
            case 3:
                System.out.println("Add Material:");
                Material[] newMaterialList = new Material[materialList.length + 1];
                chooseMaterialType(materialList, newMaterialList);
                materialList = newMaterialList;
                printMaterialList(materialList);
                break;
            case 4:
                System.out.println("Edit Material:");
                editMaterial(materialList);
                printMaterialList(materialList);
                break;
            case 5:
                Material[] newMaterials = new Material[materialList.length - 1];
                removeMaterial(materialList, newMaterials);
                materialList = newMaterials;
                printMaterialList(materialList);
                break;
            case 6:

                break;
            case 7:
                System.exit(0);
            default:
                System.out.println("Not a valid choice");
                mainMenu(materialList);
        }
        continueOrExit(materialList);
    }
//  Danh sách các chức năng
    public static int choice() {
        System.out.print("Choice: ");
        return scanner.nextInt();
    }

    public static void printMaterialList(Material[] materialList) {
        for (Material m : materialList) {
            System.out.println(m);
            System.out.println();
        }
    }

    public static void calculateTotalAmount(Material[] materialList) {
        double sum = 0;
        for (Material material : materialList) {
            sum += material.getAmount();
        }
        NumberFormat numberFormat = NumberFormat.getInstance(Locale.GERMANY);
        String formattedSum = numberFormat.format(sum);
        System.out.println(formattedSum);
    }

    public static void sortByCost(Material[] materialList) {
        for (int i = 0; i < materialList.length; i++) {
            int index = i;
            for (int j = i + 1; j < materialList.length; j++) {
                if (materialList[i].getCost() > materialList[j].getCost()) {
                    index = j;
                }
            }
            Material temp = materialList[index];
            materialList[index] = materialList[i];
            materialList[i] = temp;
            System.out.println(materialList[i].toString());
            System.out.println();
        }
    }

    public static void chooseMaterialType(Material[] materialList, Material[] newMaterialList) {
        System.out.println("1. Crispy Flour");
        System.out.println("2. Meat");
        System.out.println("3. Back");
        Material newMaterial;
        switch (choice()) {
            case 1:
                newMaterial = new CrispyFlour();
                addMaterial(materialList, newMaterialList, newMaterial);
                break;
            case 2:
                newMaterial = new Meat();
                addMaterial(materialList, newMaterialList, newMaterial);
                break;
            case 3:
                mainMenu(materialList);

                break;
            default:
                System.out.println("Not a valid choice");
                chooseMaterialType(materialList, newMaterialList);
        }
    }

    public static void addMaterial(Material[] materialList,
                                   Material[] newMaterialList,
                                   Material newMaterial) {
        scanner.nextLine();
        System.out.print("Enter id: ");
        newMaterial.setId(scanner.nextLine());
        System.out.print("Enter name: ");
        newMaterial.setName(scanner.nextLine());
        System.out.print("Enter manufacturing date (YYYY-MM-DD): ");
        String date = scanner.next();
        newMaterial.setManufacturingDate(LocalDate.parse(date));
        System.out.print("Enter cost: ");
        newMaterial.setCost(Integer.parseInt(scanner.next()));
        if (newMaterial instanceof CrispyFlour) {
            CrispyFlour crispyFlour = (CrispyFlour) newMaterial;
            System.out.print("Enter quantity: ");
            crispyFlour.setQuantity(Integer.parseInt(scanner.next()));
        } else if (newMaterial instanceof Meat) {
            Meat meat = (Meat) newMaterial;
            System.out.print("Enter weight: ");
            meat.setWeight(Double.parseDouble(scanner.next()));
        }
        for (int i = 0; i < materialList.length; i++) {
            newMaterialList[i] = materialList[i];
        }
        newMaterialList[materialList.length] = newMaterial;
    }

    public static void editMaterial(Material[] materialList) {
        System.out.print("Enter id of the materialList: ");
        int materialId = scanner.nextInt();
        int materialIndex = materialId - 1;
        System.out.println("Editing Material:");
        System.out.println(materialList[materialIndex]);
        System.out.print("Enter id: ");
        materialList[materialIndex].setId(scanner.next());
        System.out.print("Enter name: ");
        materialList[materialIndex].setName(scanner.next());
        scanner.nextLine();
        System.out.print("Enter manufacturing date (YYYY-MM-DD): ");
        String date = (scanner.next());
        materialList[materialIndex].setManufacturingDate(LocalDate.parse(date));
        System.out.print("Enter cost: ");
        materialList[materialIndex].setCost(Integer.parseInt(scanner.next()));
        if (materialList[materialIndex] instanceof CrispyFlour) {
            CrispyFlour crispyFlour = (CrispyFlour) materialList[materialIndex];
            System.out.print("Enter quantity: ");
            crispyFlour.setQuantity(Integer.parseInt(scanner.next()));
        } else if (materialList[materialIndex] instanceof Meat) {
            Meat meat = (Meat) materialList[materialIndex];
            System.out.print("Enter weight: ");
            meat.setWeight(Double.parseDouble(scanner.next()));
        }
    }

    public static void removeMaterial(Material[] materialList, Material[] newMaterialList) {
        System.out.print("Enter the id of the material want to delete: ");
        int materialId = scanner.nextInt();
        System.out.println("Remove Material:");
        System.out.println(materialList[materialId -1]);
        System.out.println();
        System.out.println("After removed:");

        for (int i = 0; i < newMaterialList.length; i++) {
            newMaterialList[i] = materialList[i];
            if (i >= materialId - 1) newMaterialList[i] = materialList[i + 1];;
        }
    }
}
