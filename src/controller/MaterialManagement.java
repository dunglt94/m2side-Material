package controller;

import model.CrispyFlour;
import model.Material;
import model.Meat;

import java.time.LocalDate;
import java.util.Scanner;

public class MaterialManagement {
    static Scanner scanner = new Scanner(System.in);

    public static void mainMenu() {
        System.out.println("Manage");
        System.out.println("1. Calculate the total cost of all 10 materials");
        System.out.println("2. Sort by cost ascending");
        System.out.println("3. Add Material");
        System.out.println("4. Edit Material");
        System.out.println("5. Remove Material");
        System.out.println("6. Exit");

    }

    public static void continueOrExit() {
        System.out.println("Continue?");
        System.out.println("1. Yes");
        System.out.println("2. No");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                mainMenu();
                break;
            case 2:
                System.exit(0);
            default:
                System.out.println("Not a valid choice");
                continueOrExit();
        }
    }

    public static int choice() {
        System.out.print("Choose: ");
        return scanner.nextInt();
    }

    public static void addMaterial(Material[] materials) {
        System.out.println("Add Material:");
        System.out.println("1. Crispy Flour");
        System.out.println("2. Meat");
        System.out.println("3. Exit");
        Material newMaterial;
        switch (choice()) {
            case 1:
                newMaterial = new CrispyFlour();
                newMats(materials, newMaterial);
                break;
            case 2:
                newMaterial = new Meat();
                newMats(materials, newMaterial);
                break;
            case 3:
                System.exit(0);
            default:
                System.out.println("Not a valid choice");
                addMaterial(materials);
        }
    }

    public static Material[] newMats(Material[] materials, Material newMaterial) {
        System.out.print("Enter id: ");
        newMaterial.setId(scanner.next());
        System.out.print("Enter name: ");
        newMaterial.setName(scanner.next());
        System.out.print("Nhập khoảng cách số ngày từ ngày sản xuất đến ngày hiện tại: ");
        int days = scanner.nextInt();
        newMaterial.setManufacturingDate(LocalDate.now().minusDays(days));
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

        Material[] newMaterials = new Material[materials.length - 1];
        for (int i = 0; i < newMaterials.length; i++) {
            newMaterials[i] = materials[i];
            newMaterials[newMaterials.length - 1] = newMaterial;
        }
        Method.printMaterialList(newMaterials);
        return newMaterials;
    }

    public static void editMaterial(Material[] materials, int index) {
        index--;
        System.out.println("Editing Material:");
        System.out.println(materials[index]);
        System.out.print("Enter id: ");
        materials[index].setId(scanner.next());
        System.out.print("Enter name: ");
        materials[index].setName(scanner.next());
        System.out.print("Nhập khoảng cách số ngày từ ngày sản xuất đến ngày hiện tại: ");
        int days = scanner.nextInt();
        materials[index].setManufacturingDate(LocalDate.now().minusDays(days));
        System.out.print("Enter cost: ");
        materials[index].setCost(Integer.parseInt(scanner.next()));
        if (materials[index] instanceof CrispyFlour) {
            CrispyFlour crispyFlour = (CrispyFlour) materials[index];
            System.out.print("Enter quantity: ");
            crispyFlour.setQuantity(Integer.parseInt(scanner.next()));
        } else if (materials[index] instanceof Meat) {
            Meat meat = (Meat) materials[index];
            System.out.print("Enter weight: ");
            meat.setWeight(Double.parseDouble(scanner.next()));
        }
        Method.printMaterialList(materials);
    }

    public static Material[] removeMaterial(Material[] materials) {
        System.out.print("Enter id of material: ");
        int id = scanner.nextInt();
        System.out.println("Remove Material:");
        System.out.println(materials[id -1]);
        System.out.println();
        System.out.println("After removed:");
        Material[] newMaterials = new Material[materials.length - 1];
        for (int i = 0; i < materials.length; i++) {
            if (i == id - 1) continue;
            newMaterials[i] = materials[i];
        }
        return newMaterials;
    }



}
