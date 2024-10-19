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
    public static void mainMenu(Material[] material) {
        System.out.println("Manage");
        System.out.println("1. Calculate the total cost of all materials");
        System.out.println("2. Sort by cost ascending");
        System.out.println("3. Add Material");
        System.out.println("4. Edit Material");
        System.out.println("5. Remove Material");
        System.out.println("6. Calculate the total discount of all materials");
        System.out.println("7. Exit");
        switchCaseForMainMenu(material);
    }

    public static void continueOrExit(Material[] material) {
        System.out.println("Continue?");
        System.out.println("1. Yes");
        System.out.println("2. No");
        switch (choice()) {
            case 1:
                mainMenu(material);
                break;
            case 2:
                System.exit(0);
            default:
                System.out.println("Not a valid choice");
                continueOrExit(material);
        }
    }

    public static void switchCaseForMainMenu(Material[] material) {
        switch (choice()) {
            case 1:
                System.out.print("The total cost of all materials: ");
                calculateTotalAmount(material);
                break;
            case 2:
                System.out.println("Pre-sorted:");
                printMaterialList(material);
                System.out.println("Sorted by cost:");
                sortByCost(material);
                break;
            case 3:
                System.out.println("Add Material:");
                addMaterial(material);
                break;
            case 4:
                System.out.println("Edit Material:");
                System.out.print("Enter id of the material: ");
                int materialId = scanner.nextInt();
                editMaterial(material, materialId);
                printMaterialList(material);
                break;
            case 5:
                Material[] newMaterials = new Material[material.length - 1];
                System.out.print("Enter the id of the material want to delete: ");
                removeMaterial(material, newMaterials);
                material = newMaterials;
                printMaterialList(material);

                break;
            case 6:

                break;
            case 7:
                System.exit(0);
            default:
                System.out.println("Not a valid choice");
                mainMenu(material);
        }
        continueOrExit(material);
    }
//  Danh sách các chức năng
    public static int choice() {
        System.out.print("Choice: ");
        return scanner.nextInt();
    }

    public static void printMaterialList(Material[] material) {
        for (Material m : material) {
            System.out.println(m);
            System.out.println();
        }
    }

    public static void calculateTotalAmount(Material[] material) {
        double sum = 0;
        for (Material m : material) {
            sum += m.getAmount();
        }
        NumberFormat numberFormat = NumberFormat.getInstance(Locale.GERMANY);
        String formattedSum = numberFormat.format(sum);
        System.out.println(formattedSum);
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

    public static void addMaterial(Material[] materials) {
        System.out.println("1. Crispy Flour");
        System.out.println("2. Meat");
        System.out.println("3. Back");
        Material newMaterial;
        switch (choice()) {
            case 1:
                newMaterial = new CrispyFlour();
                printMaterialList(createNewMaterial(materials, newMaterial));
                break;
            case 2:
                newMaterial = new Meat();
                printMaterialList(createNewMaterial(materials, newMaterial));
                break;
            case 3:
                mainMenu(materials);
            default:
                System.out.println("Not a valid choice");
                addMaterial(materials);
        }
    }

    public static Material[] createNewMaterial(Material[] materials, Material newMaterial) {
        System.out.print("Enter id: ");
        newMaterial.setId(scanner.next());
        System.out.print("Enter name: ");
        newMaterial.setName(scanner.next());
        scanner.nextLine();
        System.out.print("Enter manufacturing date (YYYY-MM-DD): ");
        String date = (scanner.next());
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
        Material[] newMaterials = new Material[materials.length + 1];
        for (int i = 0; i < materials.length; i++) {
            newMaterials[i] = materials[i];
            newMaterials[materials.length] = newMaterial;
        }
        return newMaterials;
    }

    public static void editMaterial(Material[] material, int materialId) {
        int materialIndex = materialId - 1;
        System.out.println("Editing Material:");
        System.out.println(material[materialIndex]);
        System.out.print("Enter id: ");
        material[materialIndex].setId(scanner.next());
        System.out.print("Enter name: ");
        material[materialIndex].setName(scanner.next());
        scanner.nextLine();
        System.out.print("Enter manufacturing date (YYYY-MM-DD): ");
        String date = (scanner.next());
        material[materialIndex].setManufacturingDate(LocalDate.parse(date));
        System.out.print("Enter cost: ");
        material[materialIndex].setCost(Integer.parseInt(scanner.next()));
        if (material[materialIndex] instanceof CrispyFlour) {
            CrispyFlour crispyFlour = (CrispyFlour) material[materialIndex];
            System.out.print("Enter quantity: ");
            crispyFlour.setQuantity(Integer.parseInt(scanner.next()));
        } else if (material[materialIndex] instanceof Meat) {
            Meat meat = (Meat) material[materialIndex];
            System.out.print("Enter weight: ");
            meat.setWeight(Double.parseDouble(scanner.next()));
        }
    }

    public static void removeMaterial(Material[] material, Material[] newMaterial) {
        int id = scanner.nextInt();
        System.out.println("Remove Material:");
        System.out.println(material[id -1]);
        System.out.println();
        System.out.println("After removed:");

        for (int i = 0; i < material.length; i++) {
            if (i == id - 1) continue;
            newMaterial[i] = material[i];
        }

    }


}
